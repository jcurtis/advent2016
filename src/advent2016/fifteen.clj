(ns advent2016.fifteen)

; Disc #1 has 5 positions; at time=0, it is at position 4.
; Disc #2 has 2 positions; at time=0, it is at position 1.
(def testinput [{:disk 1 :totalpos 5 :pos 4}
                {:disk 2 :totalpos 2 :pos 1}])

; Disc #1 has 7 positions; at time=0, it is at position 0.
; Disc #2 has 13 positions; at time=0, it is at position 0.
; Disc #3 has 3 positions; at time=0, it is at position 2.
; Disc #4 has 5 positions; at time=0, it is at position 2.
; Disc #5 has 17 positions; at time=0, it is at position 0.
; Disc #6 has 19 positions; at time=0, it is at position 7.
(def input [{:disk 1 :totalpos 7 :pos 0}
            {:disk 2 :totalpos 13 :pos 0}
            {:disk 3 :totalpos 3 :pos 2}
            {:disk 4 :totalpos 5 :pos 2}
            {:disk 5 :totalpos 17 :pos 0}
            {:disk 6 :totalpos 19 :pos 7}])

(defn capsule?
  [input start]
  (let [caps (filter
              (fn [{disk :disk totalpos :totalpos pos :pos}]
                ; (do (println "disk" disk "totalpos" totalpos "pos" pos)
                ;   (println "time" (+ start disk) "at pos" (mod (+ pos disk start) totalpos))
                  (if (= 0 (mod (+ pos disk start) totalpos))
                    start
                    nil))
              input)]
    (if (= (count input) (count caps))
      start
      nil)))

(defn capsule-seq
  ([input] (capsule-seq input 0))
  ([input start]
   (lazy-seq
     (cons
       (capsule? input start)
       (capsule-seq input (inc start))))))

(defn solve1
  [input]
  (some #(when (integer? %) %) (capsule-seq input)))

(def input2 (cons {:disk 7 :totalpos 11 :pos 0} input))

(defn solve2 [] (solve1 input2))
