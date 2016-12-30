(ns advent2016.five
  (:require
        [advent2016.util :as util]))

(defn is-valid
  [hash]
  (= (.substring hash 0 5) "00000"))

(defn next-hash
  [input x]
  (loop [n x]
    (let [hash (util/md5 (str input n))]
      (if (is-valid hash)
        {:count n :hash hash}
        (recur (inc n))))))

(defn solve1
  [input]
  (loop [n 0
         counter 0
         acc ""]
    (if (>= n 8)
      acc
      (let [next (next-hash input counter)]
        (do
          (println next)
          (recur (inc n) (inc (:count next)) (str acc (.charAt (:hash next) 5))))))))

; (solve1 "abc") ;=> 18f47a30
; (solve1 "cxdnnyjw")

(defn solve2
  [input]
  (loop [counter 0
         acc [nil nil nil nil nil nil nil nil]]
    (if (not-any? nil? acc)
      (apply str acc)
      (let [{count :count hashed :hash} (next-hash input counter)]
        (do
          (println "hash" hashed "count" count)
          (println "acc" acc)
          (recur
            (inc count)
            (let [sixth (and (re-matches #"[0-7]" (str (.charAt hashed 5))) (util/parse-int (.charAt hashed 5)))
                  seventh (.charAt hashed 6)]
              (do
                (println "sixth" sixth "seventh" seventh)
                (if (and (not (nil? sixth)) (nil? (acc sixth)))
                  (assoc acc sixth seventh)
                  acc)))))))))

; (solve2 "abc") ;=> 05ace8e3
; (solve2 "cxdnnyjw")
