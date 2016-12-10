(ns advent2016.one
  (:require [clojure.string :as str]
            [advent2016.util :as util]
            [clojure.tools.trace :refer :all]
            [clojure.pprint :refer :all]))

(defn parse-instruction
  [inst]
  [(case (first inst)
    \R :r
    \L :l) (util/parse-int (last (re-find #"[A-Z](\d+)" inst)))])

(defn split-instructions
  [string]
  (map parse-instruction (str/split string #", ")))

(defn turn
  [facing dir]
  (case facing
    :n (if (= dir :l) :w :e)
    :e (if (= dir :l) :n :s)
    :s (if (= dir :l) :e :w)
    :w (if (= dir :l) :s :n)))

; [x y d] [t w]
(defn travel
  [pos inst]
  (let [
        x (pos 0)
        y (pos 1)
        d (pos 2)
        t (turn d (first inst))
        w (last inst)]
      (do
        ; (println x y d t w inst)
        (case t
          :n [x (+ y w) t]
          :e [(+ x w) y t]
          :s [x (- y w) t]
          :w [(- x w) y t]))))

(defn do-travel
  [start instructions]
  (reduce travel start (split-instructions instructions)))

(defn distance
  [coord]
  (let [x (coord 0) y (coord 1)]
    (+ (Math/abs x) (Math/abs y))))

(defn solve1
  [input]
  (distance (do-travel [0 0 :n] input)))

(defn has-visited
  [newval his]
  (> (.indexOf his newval) -1))

(defn numbetween?
  [a b c]
  (let [s (min a b) l (max a b)]
    (and (>= c s) (<= c l))))
; is c between a and b inclusively
(defn between?
  [[x1 y1] [x2 y2] [xc yc]]
  (if (= x1 x2 xc)
    (numbetween? y1 y2 yc)
    (if (= y1 y2 yc)
      (numbetween? x1 x2 xc)
      false)))

(defn has-visited?
  [newval history]
  (true?
    (reduce
      (fn [acc entry]
        (if (= acc [])
          (conj acc entry)
          (if (between? (last acc) entry newval)
            (reduced true)
            (conj acc entry))))
      []
      history)))

(defn simplify
  [[x y d]]
  [x y])

(def history (atom (vector [0 0])))
(def found (atom false))
(defn history-travel
  [pos inst]
  (let [trav (travel pos inst)]
    (do
      (if (and (not @found) (has-visited? (simplify trav) @history))
        (do
          (println "Has visited? (has-visited" (simplify trav) "@history)")
          (println "Returned to" (simplify trav))
          (println "Distrance traveled" (distance trav))
          ; (pprint @history)
          (swap! found not)))
      (swap! history conj (simplify trav))
      trav)))

(defn solve2
  [input]
  (distance (reduce history-travel [0 0 :n] (split-instructions input))))
