(ns advent2016.one
  (:require [clojure.string :as str]))

(defn tokenize
  [string]
  (str/split string #", "))

(defn calc
  [input]
  (tokenize input))
