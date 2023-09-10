(ns file-converter.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as s]
            [file-converter.core :refer :all]
            [clojure.java.io :as f]))

(defn get-with-extension 
  [extension files]
  (first (filter #(s/ends-with? (.getName %) extension) files)))

(defn test-> [from-format to-format]
  (let [label (str from-format "-" to-format)
        folder (f/file (str "resources/test-cases/" label))]
    (doseq [file-pair (group-by #(-> % .getName 
                                     (s/replace from-format "")
                                     (s/replace to-format "")) (rest (file-seq folder)))]
      (let [[test-case files] file-pair
            from (slurp (get-with-extension from-format files))
            to (slurp (get-with-extension to-format files))]
        (testing test-case
          (is (= to (convert label from))))))))

(deftest json->yaml-empty
  (testing "empty json"
    (is (= (convert "json-yaml" "{}")
           ""))))

(deftest json->yaml
  (test-> "json" "yaml"))

(deftest edn->yaml
  (test-> "edn" "yaml"))
#_(deftest json->yaml-cases
  (let [folder (f/file "resources/test-cases/json-yaml")]
    (doseq [file-pair (group-by #(-> % .getName (s/replace #"(\.json|\.yaml)" "")) (rest (file-seq folder)))]
      (let [[test-case files] file-pair
            json (slurp (get-with-extension "json" files))
            yaml (slurp (get-with-extension "yaml" files))]
            (testing test-case
              (is (= yaml (convert "json-yaml" json))))))))
