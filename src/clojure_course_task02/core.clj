(ns clojure-course-task02.core
  (:gen-class))

;Coudn't make it faster using parallel things, mostly because file-seq wastes 70% of the time and I don't understand yet how to compute lazy seq in parallel.
;Attempt to recursively create futures from each subfolder was clumsy and even slower.
(defn find-files [file-name path]
  "Searching for a file using his name as a regexp."
  (let [regexp (re-pattern file-name)]
  (->>  (clojure.java.io/file path)
        (file-seq)
        (filter #(re-matches regexp (.getName %)))
        (map (memfn getName)))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))