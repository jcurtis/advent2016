(ns advent2016.three
  (:require [clojure.string :as str]
            [advent2016.util :as util]))

(defn isvalid
  [sides]
  (do
    ; (println sides)
    (let [a (first sides) b (second sides) c (last sides)]
      (and
        (> (+ a b) c)
        (> (+ a c) b)
        (> (+ c b) a)))))

(defn tokenize-line
  [line]
  (map util/parse-int (str/split (str/trim line) #"\s+")))

(defn tokenize
  [input]
  (map tokenize-line (str/split-lines input)))

(defn analyze
  [input]
  (map isvalid (tokenize input)))

(defn count-analyze
  [input]
  (count (filter true? (analyze input))))

(defn split-into-threes
  [lines]
  (if (> (count lines) 3)
    (take 3)
    (split-into-threes)))

;
; (reduce)
;
; (defn split-block
;   [input]
;   (let [lines (str/split-lines input)]
;     (dotime [i 3]
;       (lines i))))
;
