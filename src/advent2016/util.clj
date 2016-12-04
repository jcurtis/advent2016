(ns advent2016.util)

(defn printpipe
  [data]
  (do
    (println data)
    data))

(defn parse-int [s]
  (Integer/parseInt (str s)))
