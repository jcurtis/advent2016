(ns advent2016.four
  (:require [clojure.string :as str]
            [advent2016.util :as util]))

(defn occurrence
  [string letter]
  (count (filter #(= letter %) (seq string))))

; {2 [a r t], 1 [e m y], 3 [l o]}
(defn count-letters
  [string]
  (sort-by key
    (group-by
      (fn [char]
        (occurrence string char))
      (set string))))
  ; (reduce (fn [acc val] (hash-map)) {} ()))

(defn tokenize
  [string]
  (let [tokens
        (re-find #"(([a-z]+\-)+)(\d+)\[(\w+)\]" string)]
    [
      (str/replace (tokens 1) #"\-" "")
      (util/parse-int (tokens 3))
      (tokens 4)]))

(defn sort-count-map
  [count-map]
  (map
    (fn [[key val]]
      (assoc {} key (sort val)))
    count-map))

(defn real?
  [string]
  (let [[name sector checksum] (tokenize string)]
    (let [parsed
          (apply str
            (map (fn [pair]
                    (apply str (val (first pair))))
              (reverse (sort-count-map (count-letters name)))))]
      (do
        ; (println "parsed" (.substring parsed 0 5))
        (if (= (.substring parsed 0 5) checksum)
          sector
          0)))))

(defn solve1
  [input]
  (reduce +
    (map real? (str/split-lines input))))

(def letters "abcdefghijklmnopqrstuvwxyz")
(defn decrypt-letter
  [letter offset]
  (let [idx (str/index-of letters letter)]
    (if (= idx nil)
      letter
      (.charAt letters (mod (+ idx offset) 26)))))

(defn tokenize2
  [string]
  (let [tokens
          (re-find #"(([a-z]+\-)+)(\d+)" string)]
    [
      (str/trim (str/replace (tokens 1) #"\-" " "))
      (util/parse-int (tokens 3))]))

(defn decrypt
  [string]
  (let [[name offset] (tokenize2 string)]
    (apply str
      (map
        (fn [letter]
          (decrypt-letter letter offset))
        (str/split name #"")))))

(decrypt "qzmt-zixmtkozy-ivhz-343")

(defn solve2
  [input]
  (map
    (fn [line]
      (let [decrypted (decrypt line)]
        (do
          (println line)
          (println decrypted)
          decrypted)))
    (str/split-lines input)))
