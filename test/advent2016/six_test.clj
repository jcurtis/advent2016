(ns advent2016.six-test
  (:require [clojure.test :refer :all]
            [advent2016.six :refer :all]
            [clojure.string :as str]))

(def test-input (slurp "src/advent2016/input/six_test.txt"))
(def input (slurp "src/advent2016/input/six.txt"))

(deftest solve1-test
  (testing "solve 6 part 1"
    (is (= "easter" (solve1 test-input)))
    (println "solve1" (solve1 input))))

(deftest frequent-letter-test
  (testing "frequest-letter"
    (is (= \e (frequent-letter [\a \b \e \c \e \a \e])))
    (is (= \z (frequent-letter [\z])))))

(deftest solve2-test
  (testing "solve 6 part 2"
    (is (= "advent" (solve2 test-input)))
    (println "solve2" (solve2 input))))
