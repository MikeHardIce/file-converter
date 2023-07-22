(ns file-converter.core
  (:require [clojure.data.json :as json]
            [clojure.string :as s])
  (:gen-class))

(defmulti convert (fn [from->to content] from->to))

(defmethod convert :default
 [_ _])

(defn -main
  ""
  [& args]
  (let [[from->to file1 file2] args
        converted (convert from->to (slurp file1))]
    (if (and (seq file2) (seq converted))
      (spit file2 converted)
      (println converted))))

(defn intent-yaml
  [content level & {:as options}]
  (let [{:keys [include-first-line? offset-kids fill-with] :or {include-first-line? false 
                                                                offset-kids 0
                                                                fill-with "  "}} options
        lines (s/split-lines content)]
    (str (when include-first-line?
           (apply str (repeat level fill-with)))
         (s/join (str \newline (apply str (repeat (+ level offset-kids) fill-with))) lines))))

(defprotocol To-Yaml
  (to-yaml [content] ))

(declare ^:dynamic *skip-newline*)

(extend-protocol To-Yaml
  
  Long
  (to-yaml [content]
    (str " " content))
  
  String
  (to-yaml [content]
    (let [lines (s/split-lines content)]
      (str (if (> (count lines) 1)
             (str " |" \newline)
             " ")
           content)))
  
  clojure.lang.PersistentArrayMap
  (to-yaml [content]
    (let [skip-nl (if (bound? #'*skip-newline*) *skip-newline* nil)]
      (str (when (not skip-nl)
             \newline)
           (s/join \newline (for [[k v] content]
                              (intent-yaml (str k ":" (to-yaml v)) 1))))))
  
  clojure.lang.PersistentVector
  (to-yaml [content]
           (str \newline
                (s/join \newline (for [item content]
                                   (intent-yaml (str "- " (binding [*skip-newline* true]
                                                            (to-yaml item)))
                                                0 :include-first-line? true :offset-kids 1))))))

(defmethod convert "json-yaml"
  [_ content]
  (let [content (json/read-str content)
        yaml (to-yaml content)]
    (if (s/starts-with? yaml (str \newline))
      (s/replace-first yaml \newline "")
      yaml)))