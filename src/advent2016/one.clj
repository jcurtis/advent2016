(ns advent2016.one
  (:require [clojure.string :as str]
            [advent2016.util :as util]))

(defn parse-instruction
  [inst]
  [(case (first inst)
    \R :r
    \L :l) (util/parse-int (last inst))])

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

(defn calc
  [input]
  (distance (do-travel [0 0 :n] input)))
