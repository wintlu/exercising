(ns exercising.bigdata)
(import java.util.Random)

(def test-data [6 80 77 5 97 58 39 91 84 0 43 13 94 39 27 14 33 84 98 22 96 41 33 83 92 41 80 47 99 62 36 62 76 39 42 62 12 81 96 33 89 5 61 20 76 52 7 80 15 43 19 48 15 22 3 79 21 40 73 26 27 25 1 63 91 93 28 33 21 18 74 33 49 78 66 53 84 76 27 56 38 48 93 81 1 95 62 3 77 91 13 36 8 99 64 73 93 13 30 56])
;find the top biggest 10 in 100 data
(defn random-item [coll] 
  (nth coll (.nextInt (java.util.Random.) (count coll))))
(defn split-to-small-big [coll]
  (let [selected-item (random-item coll)]
    [(filter #(<= % selected-item) coll)
		 (filter #(> % selected-item) coll)]))

(defn find-small-n [L, n]
  (if (> n (count L))
    L
    (let [[small big] (split-to-small-big L)
          small-count (count small)]
      (if (= n small-count)
        small
        (if (< n small-count)
          (find-small-n small n)
          (lazy-cat small (find-small-n big (- n small-count))))))))

(find-small-n test-data 10)