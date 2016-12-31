(ns advent2016.fourteen
  (:require [advent2016.util :as util]))

(defn gen-hash-seq
  ([salt] (gen-hash-seq salt 0))
  ([salt i]
   (lazy-seq
    (cons
      (util/md5 (str salt i))
      (gen-hash-seq salt (inc i))))))

(defn has-triple? [hash] (re-find #"([a-z0-9])\1\1" hash))
(defn has-quintuple? [hash char] (re-find (re-pattern (apply str (repeat 5 char))) hash))

(defn one-time-pads
  [salt & {:keys [generator] :or {generator gen-hash-seq}}]
  (let [hash-seq (generator salt)]
    (keep-indexed
      (fn [idx hash]
        (let [[_ char] (has-triple? hash)]
          (and char
            (let [quints (keep-indexed #(if (has-quintuple? %2 char) [(+ %1 idx) %2] nil) (take 1000 (drop (inc idx) hash-seq)))]
              (if (empty? quints) nil {:idx idx :hash hash :quints quints})))))
      hash-seq)))

(defn solve1
  [salt]
  (nth (one-time-pads salt) 63))

(defn stretch
  [salt i]
  (nth (iterate util/md5 (str salt i)) 2017))

(defn gen-stretched-seq
  ([salt] (gen-stretched-seq salt 0))
  ([salt i]
   (lazy-seq
    (cons
      (stretch salt i)
      (gen-stretched-seq salt (inc i))))))

(defn solve2
  [salt]
  (nth (one-time-pads salt :generator gen-stretched-seq) 63))
