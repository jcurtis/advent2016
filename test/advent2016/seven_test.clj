(ns advent2016.seven-test
  (:require [clojure.test :refer :all]
            [advent2016.seven :refer :all]
            [clojure.string :as str]))

(def input (slurp "src/advent2016/input/seven.txt"))

(deftest abba?-test
  (testing "abba?"
    (is (true? (abba? "abba")))
    (is (false? (abba? "abcd")))
    (is (true? (abba? "ioxxoj")))
    (is (true? (abba? "asdfakljsdfouwtbbteflkasjdfpaosdfalsdkfasdkfhasdf")))
    (is (true? (abba? "asdfakljsdfouweflkasjdfpaosdfalsdkfasdkfhasdftbbt")))
    (is (false? (abba? "aaaa")))))

(deftest tls?-test
  (testing "tls?"
    (is (true? (tls? "abba[mnop]qrst")))
    (is (false? (tls? "abcdl[zxcbddbrre]xyyx")))
    (is (false? (tls? "aaaa[qwer]tyui")))
    (is (true? (tls? "ioxxoj[asdfgh]zxcvbn")))
    (is (true? (tls? "ioxxoj[asdfgh]zxcv[potato]bn")))
    (is (true? (tls? "uxpvoytxfazjjhi[qogwhtzmwxvjwxreuz]zduoybbzxigwggwu[lamifchqqwbphhsqnf]qrjdjwtnhsjqftnqsk[bsqinwypsnnvougrs]wfmhtjkysqffllakru")))
    (is (false? (tls? "uxpvoytxfazjjhi[qogwhtzmwxvjwxreuz]zduoybbzxigwggwu[lamifcwqqwbphhsqnf]qrjdjwtnhsjqftnqsk[bsqinwypsnnvougrs]wfmhtjkysqffllakru")))))

(deftest solve1-test
  (testing "solve 7 part 1"
    (println "solve1:" (solve1 input))))
