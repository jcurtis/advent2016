(ns advent2016.six
  (:require [clojure.string :as str]))

(defn frequent-letter
  [letters]
  (key (first (reverse (sort-by val (frequencies letters))))))

(defn solve
  [input freq-func]
  (apply str
    (let [lines (str/split-lines input)]
      (loop [x 0 result []]
        (if (< x (count (first lines)))
            (recur (inc x) (conj result (freq-func (map #(.charAt % x) lines))))
            result)))))

(defn solve1
  [input]
  (solve input frequent-letter))

(defn less-frequent-letter
  [letters]
  (key (first (sort-by val (frequencies letters)))))

(defn solve2
  [input]
  (solve input less-frequent-letter))
