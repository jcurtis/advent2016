(ns advent2016.five
  (:import
        [java.security MessageDigest]
        [java.math BigInteger]))

(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defn hash-at
  [string index]
  (md5 (str string index)))

(defn is-valid
  [hash]
  (= (.substring hash 0 5) "00000"))

; can't figure out the lazy sequence thing
