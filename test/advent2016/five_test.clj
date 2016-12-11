(ns advent2016.five-test
  (:require [clojure.test :refer :all]
            [advent2016.five :refer :all]))

(deftest valid-hashes
  (testing "valid-hashes"
    (is (true? (is-valid (hash-at "abc" 3231929))))
    (is (true? (is-valid (hash-at "abc" 5017308))))
    (is (false? (is-valid (hash-at "abc" 3231928))))
    (is (false? (is-valid (hash-at "abc" 0))))))
