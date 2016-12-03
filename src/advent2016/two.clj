(ns advent2016.two
  (:require [clojure.string :as str]))

(defn up
  [pos]
  (case pos
    1 1
    2 2
    3 3
    4 1
    5 2
    6 3
    7 4
    8 5
    9 6))

(defn down
  [pos]
  (case pos
    1 4
    2 5
    3 6
    4 7
    5 8
    6 9
    7 7
    8 8
    9 9))

(defn right
  [pos]
  (case pos
    1 2
    2 3
    3 3
    4 5
    5 6
    6 6
    7 8
    8 9
    9 9))

(defn left
  [pos]
  (case pos
    1 1
    2 1
    3 2
    4 4
    5 4
    6 5
    7 7
    8 7
    9 8))

(defn up2
  [pos]
  (case pos
    1 1
    2 2
    3 1
    4 4
    5 5
    6 2
    7 3
    8 4
    9 9
    "A" 6
    "B" 7
    "C" 8
    "D" "B"))

(defn down2
  [pos]
  (case pos
    1 3
    2 6
    3 7
    4 8
    5 5
    6 "A"
    7 "B"
    8 "C"
    9 9
    "A" "A"
    "B" "D"
    "C" "C"
    "D" "D"))

(defn right2
  [pos]
  (case pos
    1 1
    2 3
    3 4
    4 4
    5 6
    6 7
    7 8
    8 9
    9 9
    "A" "B"
    "B" "C"
    "C" "C"
    "D" "D"))

(defn left2
  [pos]
  (case pos
    1 1
    2 2
    3 2
    4 3
    5 5
    6 5
    7 6
    8 7
    9 8
    "A" "A"
    "B" "A"
    "C" "B"
    "D" "D"))

(defn handle_direction
  [part2]
  (fn
    [pos dir]
    (let [res
          (if part2
            (do
              ; (println "doing part2")
              (case dir
                "U" (up2 pos)
                "D" (down2 pos)
                "R" (right2 pos)
                "L" (left2 pos)))
            (do
              ; (println "doing part 1")
              (case dir
                "U" (up pos)
                "D" (down pos)
                "R" (right pos)
                "L" (left pos))))]
        (do
          res))))
          ; (println (str pos " does " dir " ending up at " res))))))


(defn do-line
  [pos line part2]
  (let [res
        (reduce (handle_direction part2) pos (str/split line #""))]
    (do
      res)))
      ; (print (str "starting at " pos " doing " line " gives " res "\n")))))

(defn combine
  [part2]
  (fn [code line]
    (conj code (do-line (last code) line part2))))

(defn do-input
  [input part2]
  (reduce (combine part2) [5] (str/split-lines input)))
