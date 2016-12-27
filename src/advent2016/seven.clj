(ns advent2016.seven
  (:require [clojure.string :as str]))

(defn abba?
  [input]
  (let [letters (str/split input #"")]
    (loop [x 3]
      (if (< x (count letters))
        (let [a (letters (- x 3))
              b (letters (- x 2))
              c (letters (- x 1))
              d (letters x)]
          (if (and (= a d) (= b c) (not= a b))
            true
            (recur (inc x))))
        false))))

(defn clean-input
  [input]
  (.replace (.replace input "[" "") "]" ""))

(defn find-hyper
  [input]
  (map #(clean-input (first %)) (re-seq #"\[([a-z]+)\]" input)))

(defn find-standard
  [input]
  (map #(clean-input (first %)) (re-seq #"([a-z]+\[)|(\][a-z]+\[)|(\][a-z]+)" input)))

(defn tls?
  [input]
  (and
    ; standard one has to be true
    (some abba? (find-standard input))
    ; hyper all have to be false
    (not (some abba? (find-hyper input)))))

(defn solve1
  [input]
  (count (filter true? (map tls? (str/split-lines input)))))

(defn find-aba
  [input]
  (map first (re-seq #"([a-z])[a-z]\1" input)))

(defn invert
  [aba]
  (str (second aba) (first aba) (second aba)))

(defn not-empty? [x] (not (empty? x)))

(defn filter-abas
  [list]
  (flatten (filter not-empty? (map find-aba list))))

(defn ssl?
  [input]
  (let [abas (filter-abas (find-standard input))
        hyper (find-hyper input)]
    (do
      (println "abas" abas)
      (filter (fn [aba]
               (some #(str/index-of (invert aba) %) hyper))
        abas))))
