(ns four-clojure-downloader.lt-report
  (:gen-class)
  (:require [clojure.test :refer :all]))

(def ^:dynamic *result*)
(defmulti ^:dynamic lt-report :type)

(defmethod lt-report :pass [_]
  (set! *result* :Woohoo!))

(defmethod lt-report :fail
  [report]
  (set! *result*
        (filter
         (comp not nil? val)
         (select-keys report '(:expected :actual :message)))))

(defmethod lt-report :default
  [other]
  (set! *result* other))

(defmacro with-lt-output
  [& body]
  `(let [s# (new java.io.StringWriter)]
    (binding [report lt-report
              *test-out* s#
              *result* (list)]
     ~@body
     *result*)))
