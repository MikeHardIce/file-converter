(ns file-converter.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as s]
            [file-converter.core :refer :all]
            [clojure.java.io :as f]))

(defn get-with-extension 
  [extension files]
  (first (filter #(s/ends-with? (.getName %) extension) files)))

(deftest json->yaml-empty
  (testing "empty json"
    (is (= (convert "json-yaml" "{}")
           ""))))

(deftest json->yaml-cases
  (let [folder (f/file "resources/test-cases/json-yaml")]
    (doseq [file-pair (group-by #(-> % .getName (s/replace #"(\.json|\.yaml)" "")) (rest (file-seq folder)))]
      (let [[test-case files] file-pair
            json (slurp (get-with-extension "json" files))
            yaml (slurp (get-with-extension "yaml" files))]
            (testing test-case
              (is (= yaml (convert "json-yaml" json))))))))
