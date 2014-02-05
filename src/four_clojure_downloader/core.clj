(ns four-clojure-downloader.core
  (:gen-class)
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json])
  (:require [clojure.test :refer :all]))

(def url "http://www.4clojure.com/api/problem/")

(def file-dir "REPLACE ME: WHERE PROBLEMS GO")

(defn retrieve-problem
  [problem-id]
  (client/get  (str url problem-id)  {:as :json}))


(defn problem-to-map
  [json]
  (select-keys (:body json) '(:title :tests :description)))


(defn to-lt-test
  [test-case]
  (str "(with-lt-output\n"
       "  (is " test-case "\n"
       "))"))


(defn write-to-test-file
  [string test-id]
  (spit (str file-dir test-id ".clj")
        string))

(defn to-str
  [problem]
  (let [{:keys [description tests title]} problem]
    (with-out-str
      (println "(ns foreclojureall")
      (println "  (:require [clojure.test :refer :all]))")
      (println)
      (println "(load \"lt_report\")")
      (println)
      (println ";; " title)
      (println ";; " description)
      (println)
      (println "(def __ \"TODO\")")
      (println)
      (doseq [t tests]
        (println (to-lt-test t))
        (println)))))


(to-str (problem-to-map (retrieve-problem 2)))
(problem-to-str 4)

(def problem-to-str
  (comp to-str problem-to-map retrieve-problem))

(defn write-test
  [id]
  (let [file-contents (problem-to-str id)]
    (write-to-test-file file-contents id)))


;; (doseq [i (range 15 25)]
;;   (write-test i))

;; metaprogramming
;; (defn to-lt-test
;;   [test-case]
;;   (let [test-case (read-string test-case)]
;;   (list (quote with-lt-output)
;;         (list (quote is)
;;               test-case))))

