(:ns advent2016.two
  (:require [clojure.string :as str]))

(defn calc-line
  [acc line]
  line)

(defn calc
  [input]
  (reduce (calc-line) 5 (str/split-lines input)))
