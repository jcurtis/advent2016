(ns advent2016.util
  (:import
        [java.security MessageDigest]
        [java.math BigInteger]))

(defn printpipe
  [data]
  (do
    (println data)
    data))

(defn parse-int [s]
  (Integer/parseInt (str s)))

(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))
