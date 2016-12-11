(ns advent2016.six
  (:require [clojure.string :as str]))

(defn frequent-letter
  [letters]
  (key (first (reverse (sort-by val (frequencies letters))))))

(defn solve1
  [input]
  (apply str
    (let [lines (str/split-lines input)]
      (loop [x 0 result []]
        (if (< x (count (first lines)))
            (recur (inc x) (conj result (frequent-letter (map #(.charAt % x) lines))))
            result)))))
