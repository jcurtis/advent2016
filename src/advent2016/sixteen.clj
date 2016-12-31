(ns advent2016.sixteen)

(defn flip
  [x]
  (map #(if (= % \0) \1 \0) x))

(defn expand
  [a]
  (apply str a 0 (flip (reverse a))))

(defn checksum
  [data]
  (apply str (map #(if (= (first %) (second %)) \1 \0) (partition 2 data))))

(defn valid-checksum
  [data]
  (loop [x (checksum data)]
    (if (even? (count x))
      (recur (checksum x))
      x)))

(defn fill
  [init length]
  (loop [data init]
    (if (<= length (count data))
      (apply str (take length data))
      (recur (expand data)))))

(defn solve [x l] (valid-checksum (fill (str x) l)))
