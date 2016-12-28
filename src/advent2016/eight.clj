(ns advent2016.eight
  (:require [advent2016.util :refer :all]
            [clojure.core.matrix :refer :all]
            [clojure.string :as str]))

; (def screen (atom (repeat 6 (repeat 50 nil))))
(defn create-screen [a b]
  (repeat a (repeat b \.)))

(def empty-screen (create-screen 6 50))

(defn print-screen
  [screen]
  (do (vec (map #(println %) screen))
    nil))

(defn take-rest [n coll]
  (take-last (- (count coll) n) coll))

(defn rect
  [a b screen]
  (concat
    (map (fn [line]
          (concat
            (repeat a \#)
            (take-rest a line)))
      (take b screen))
    (take-rest b screen)))

(defn rotate-row [y n screen]
  (assoc (vec screen) y
    (let [line (nth screen y)]
      (concat
        (take-last n line)
        (take (- (count line) n) line)))))

(defn rotate-column [x n screen]
  (transpose (rotate-row x n (transpose screen))))

(def inst-re #"((rect) (\d+)x(\d+))|((rotate row) y=(\d+) by (\d+))|((rotate column) x=(\d+) by (\d+))")
(defn parse-instruction
  [line]
  (let [m (re-matches inst-re line)]
    (cond
      (= "rect" (m 2))
      (partial rect (parse-int (m 3)) (parse-int (m 4)))
      (= "rotate row" (m 6))
      (partial rotate-row (parse-int (m 7)) (parse-int (m 8)))
      (= "rotate column" (m 10))
      (partial rotate-column (parse-int (m 11)) (parse-int (m 12)))
      :else nil)))

(defn process
  [input screen]
  (reduce
    (fn [acc line] ((parse-instruction line) acc))
    screen
    (str/split-lines input)))

(defn voltage
  [screen]
  (count (filter #(= \# %) (flatten screen))))

(defn solve1
  [input]
  (voltage (process input empty-screen)))
