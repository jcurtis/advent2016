(ns advent2016.four
  (:require [clojure.string :as str]
            [advent2016.util :as util]))

(defn occurrence
  [string letter]
  (count (filter #(= letter %) (seq string))))

(defn count-letters
  [string]
  (group-by
    (fn [char]
      (occurrence string char))
    (set string)))
  ; (reduce (fn [acc val] (hash-map)) {} ()))

(defn tokenize
  [string]
  (let [tokens
        (re-find #"(([a-z]+\-)+)(\d+)\[(\w+)\]" string)]
    [
      (str/replace (tokens 1) #"\-" "")
      (util/parse-int (tokens 3))
      (tokens 4)]))

(defn real?
  [string]
  true)
