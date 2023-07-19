(defproject file-converter "0.1.0-SNAPSHOT"
  :description "Converts files into different formats"
  :url "http://example.com/FIXME"
  :license {:name "MIT"
            :url "https://choosealicense.com/licenses/mit"
            :comment "MIT License"
            :year 2023
            :key "mit"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/data.json "2.4.0"]]
  :main ^:skip-aot file-converter.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
