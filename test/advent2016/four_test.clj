(ns advent2016.four-test
  (:require [clojure.test :refer :all]
            [advent2016.four :refer :all]))

(def input1 "aaaaa-bbb-z-y-x-123[abxyz]")
(def input2 "a-b-c-d-e-f-g-h-987[abcde]")
(def input3 "not-a-real-room-404[oarel]")
(def input4 "totally-real-room-200[decoy]")

(deftest occurrence-test
  (testing "occurence"
    (is (= (occurrence "string" \s) 1))
    (is (= (occurrence "totallyrealroom" \o) 3))))

(deftest count-letters-test
  (testing "count-letters"
    (println (count-letters "totallyrealroom"))))

(deftest tokenize-test
  (testing "tokenize"
    (println "tokenize" input1 ":")
    (println (tokenize input1))))

(deftest real-test
  (testing "real?"
    (is (true? (real? input1)))
    (is (true? (real? input2)))
    (is (true? (real? input3)))
    (is (false? (real? input4)))))
