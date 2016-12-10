(ns advent2016.one-test
  (:require [clojure.test :refer :all]
            [advent2016.one :refer :all]))

(deftest parse-instruction-test
  (testing "parse-instruction"
    (is (= (parse-instruction "R2") [:r 2]))
    (is (= (parse-instruction "R48") [:r 48]))
    (is (= (parse-instruction "R192") [:r 192]))))

(deftest split-instructions-test
  (testing "split-instructions"
    (is (= (split-instructions "R2, L3") [[:r 2] [:l 3]]))
    (is (= (split-instructions "R5, L5, R5, R3") [[:r 5] [:l 5] [:r 5] [:r 3]]))))

(deftest turn-test
  (testing "turn"
    (is (= (turn :n :r) :e))
    (is (= (turn :w :l) :s))))

(deftest travel-test
  (testing "travel"
    (is (= (travel [0 0 :n] [:r 2]) [2 0 :e]))
    (is (= (travel [2 0 :e] [:l 3]) [2 3 :n]))))

(deftest do-travel-test
  (testing "do-travel"
    (is (= (do-travel [0 0 :n] "R2, L3") [2 3 :n]))
    (is (= (do-travel [0 0 :n] "R2, R2, R2") [0 -2 :w]))
    (is (= (do-travel [0 0 :n] "R5, L5, R5, R3") [10 2 :s]))
    (is (= (do-travel [0 0 :n] "R2, L5, L4, L5, R4") [-6 0 :w]))))

(deftest distance-test
  (testing "distance"
    (is (= (distance [2 3]) 5))
    (is (= (distance [0 -2]) 2))
    (is (= (distance [10 2]) 12))))

(deftest example1
  (testing "R2, L3"
    (is (= (solve1 "R2, L3") 5))))

(deftest example2
  (testing "R2, R2, R2"
    (is (= (solve1 "R2, R2, R2") 2))))

(deftest example3
  (testing "R5, L5, R5, R3"
    (is (= (solve1 "R5, L5, R5, R3") 12))))

(def input "R2, L5, L4, L5, R4, R1, L4, R5, R3, R1, L1, L1, R4, L4, L1, R4, L4, R4, L3, R5, R4, R1, R3, L1, L1, R1, L2, R5, L4, L3, R1, L2, L2, R192, L3, R5, R48, R5, L2, R76, R4, R2, R1, L1, L5, L1, R185, L5, L1, R5, L4, R1, R3, L4, L3, R1, L5, R4, L4, R4, R5, L3, L1, L2, L4, L3, L4, R2, R2, L3, L5, R2, R5, L1, R1, L3, L5, L3, R4, L4, R3, L1, R5, L3, R2, R4, R2, L1, R3, L1, L3, L5, R4, R5, R2, R2, L5, L3, L1, L1, L5, L2, L3, R3, R3, L3, L4, L5, R2, L1, R1, R3, R4, L2, R1, L1, R3, R3, L4, L2, R5, R5, L1, R4, L5, L5, R1, L5, R4, R2, L1, L4, R1, L1, L1, L5, R3, R4, L2, R1, R2, R1, R1, R3, L5, R1, R4")
(deftest myinput
  (testing "generated input"
    (println "distance traveled: " (solve1 input))))

(deftest solve2-test
  (testing "solve2"
    (println "solving2:")
    (println "Total distance (not answer)" (solve2 input))))

(deftest numbetween?-test
  (testing "numbetween?"
    (is (true? (numbetween? 0 5 3)))
    (is (true? (numbetween? 0 5 0)))
    (is (true? (numbetween? 0 5 5)))
    (is (true? (numbetween? -123 5 0)))
    (is (true? (numbetween? -123 5 -100)))
    (is (false? (numbetween? -123 5 100)))))

(deftest between?-test
  (testing "between?"
    (is (true? (between? [0 0] [0 0] [0 0])))
    (is (true? (between? [0 0] [0 3] [0 2])))
    (is (false? (between? [0 0] [0 3] [1 2])))
    (is (false? (between? [1 2] [3 4] [5 6])))
    (is (true? (between? [-123 14] [14 14] [-100 14])))
    (is (true? (between? [-123 14] [14 14] [-123 14])))
    (is (false? (between? [-123 14] [14 14] [-124 14])))))

(deftest has-visited?-test
  (testing "has-visited?"
    (is (true? (has-visited? [0 1] [[0 0] [0 2] [3 2] [1 2] [1 -2]])))
    (is (false? (has-visited? [0 3] [[0 0] [0 2] [3 2] [3 1] [-2 1]])))
    (is (true? (has-visited? [4 0] [[0 0] [8 0] [8 -4] [4 -4] [4 4]])))
    (is (false? (has-visited? [5 -3] [[0 0] [8 0] [8 -4] [4 -4] [4 4]])))))
