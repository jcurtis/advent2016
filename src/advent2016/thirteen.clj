(ns advent2016.thirteen
  (:require [clojure.core.matrix :refer :all]))

(def prec 50)

(defn calc
  [x y o]
  (let [X (bigint x)
        Y (bigint y)
        O (bigint o)]
    (+
      (* X X)
      (* 3 X)
      (* 2 X Y)
      Y
      (* Y Y)
      O)))

(defn binary [x] (Integer/toString x 2))

(defn count-ones [bin]
  (count (filter #(= \1 %) (seq bin))))

(defn space?
  [x y o]
  (even? (count-ones (binary (calc x y o)))))

(defn cubicle-rows
  ([y o] (cubicle-rows 0 y o))
  ([x y o]
   (lazy-seq
     (cons
       (space? x y o)
       (cubicle-rows (inc x) y o)))))

(defn cubicle
  ([o] (cubicle 0 o))
  ([y o] (lazy-seq (cons (take prec (cubicle-rows y o)) (cubicle (inc y) o)))))

(defn pretty-space
  [s]
  (if (true? s) \. \#))

(defn positive-numbers
  ([] (positive-numbers 0))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))

(defn pretty-print
  [grid]
  (do
    ; (println (cons \X (take prec (positive-numbers))))
    (take prec
        (map (fn [row]
              (do
                (println (apply str (take prec (map pretty-space row))))
                row))
         grid))
    nil))
