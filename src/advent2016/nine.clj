(ns advent2016.nine
  (:require [clojure.string :as str]
            [advent2016.util :as util]))


(defn rest-str
  [input marker length]
  (let [substr (+ (str/index-of input marker) length (count marker))]
    (if (< substr (count input))
      (.substring input substr)
      "")))

(defn str-mult
  [a b]
  (* (util/parse-int a) (util/parse-int b)))

(defn remove-whitespace
  [string]
  (str/replace string #"\s+|\n+" ""))

(defn next-marker
  [input]
  (re-find #"\((\d+)x(\d+)\)" input))

(defn solve1
  [compressed]
  (loop [counter 0
         input (remove-whitespace compressed)]
      (let [marker (next-marker input)]
        (do (println "counter" counter "rest input size" (count input) "marker" marker)
          (if (= 0 (count marker))
            (+ counter (count input))
            (let [[m l r] marker]
                (recur
                  (+ (str/index-of input m) counter (str-mult l r))
                  (rest-str input m (util/parse-int l)))))))))


(def input (slurp "src/advent2016/input/nine.txt"))
; (solve1 input)
