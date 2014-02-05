(defproject four-clojure-downloader "0.1.0-SNAPSHOT"
  :description "Download problems from 4clojure.org and put them into a Midge test driven format"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-http "0.7.8"]
                 [org.clojure/data.json "0.2.4"]
                 [org.clojure/data.zip "0.1.1"]
                 [org.clojure/tools.reader "0.8.3"]]
  :main ^:skip-aot four-clojure-downloader.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
