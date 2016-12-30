(ns advent2016.twelve
    (:require [clojure.string :as str]
              [advent2016.util :as util]))

(def registers (atom {:a 0 :b 0 :c 1 :d 0 :n 0 :count 0}))
(defn reset-registers [] (reset! registers {:a 0 :b 0 :c 0 :d 0 :n 0 :count 0}))

(defn to-val
  [x]
  (cond
    (keyword? x) (@registers x)
    (integer? x) x
    (re-matches #"[a-d]" x) (@registers (keyword x))
    (re-matches #"-?\d+" x) (util/parse-int x)
    :else x))

(defn cpy
  [x y]
  (swap! registers
    (fn [reg]
      (update (assoc reg (keyword y) (to-val x)) :n inc))))

(defn updatex
  [f x]
  (swap! registers
    (fn [reg]
      (update (update reg (keyword x) f) :n inc))))

(def incx (partial updatex inc))
(def decx (partial updatex dec))

(defn jnz
  [x y]
  (let [condition (to-val x)
        val (to-val y)]
      (swap! registers
        (fn [reg]
          (if (= 0 (to-val condition))
            (update reg :n inc)
            (update reg :n #(+ val %)))))))

(def input (slurp "src/advent2016/input/twelve.txt"))

(def regex #"(cpy (.+) ([a-d]))|(inc ([a-d]))|(dec ([a-d]))|(jnz (.+) (-?\d+))")
(defn parse-ins
  [line]
  (let [ins (re-matches regex line)]
      (cond
        (ins 1) #(cpy (ins 2) (ins 3))
        (ins 4) #(incx (ins 5))
        (ins 6) #(decx (ins 7))
        (ins 8) #(jnz (ins 9) (ins 10))
        :else nil)))

(defn parse-instructions
  [in]
  (map parse-ins (str/split-lines input)))

(defn solve1
  [in]
  (let [inst (parse-instructions in)]
    (do (while
          (and
            (< (@registers :count) 100000000) ; use :c to prevent infinite loop
            (< (@registers :n) (count inst)))
          (do
            ; (println
            ((nth inst (@registers :n)))
            (swap! registers #(update % :count inc))))
        (println @registers))))
