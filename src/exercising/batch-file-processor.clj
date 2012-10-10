(ns batch-file-processor
  (:require [clojure.java.io :as io]))
;A batch file tool that that concurrently process (by specified logic) every text file from in-folder   
;and generate to out-folder

(def folders {:in "resources/in-folder" :out "resources/out-folder"})

(defn process-file [f]
  (slurp f))

(defn process-folder [f] 
  (.mkdir f))

(defn get-out-folder [in-folder]
  )

(let [in-foler (io/file (str (get folders :base) (get folders :in)))
      out-foler (io/file (str (get folders :base) (get folders :out)))]
  (doseq [f (file-seq (clojure.java.io/file in-folder))]
	  (if (.isFile f)
	    (process-file f)
	    (process-folder f))))

;(-> (get folders :in) io/file .listFiles) 