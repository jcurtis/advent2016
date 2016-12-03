(ns advent2016.two-test
  (:require [clojure.test :refer :all]
            [advent2016.one :refer :all]))

(def input1 "ULL
RRDDD
LURDL
UUUUD")

(deftest example1
  (testing input1)
  (is (= (calc input1) 5)))
