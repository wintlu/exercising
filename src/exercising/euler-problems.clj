;http://projecteuler.net/problems
(ns exercising.euler-problems)

;problem 1
(defn multiples-of-3-and-5 
  "If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
  Find the sum of all the multiples of 3 or 5 below 1000."
  [x]
  (reduce + (for [i (range 1 x) :when (or (zero? (rem i 3)) (zero? (rem i 5)))] i)))


;problem 2
(defn even-fibonacci-numbers
  "find the sum of the even-valued numbers in a fib seqeunce whoses value does not exceed a given number"
  [x]
  (loop [a 1 b 2 sum 0]
    (if (>= b x)
      sum
      (recur b 
             (+ a b) 
             (+ sum (cond
                      (even? b) b
                      :else 0))))))

(defn problem2 []
  (even-fibonacci-numbers 4000000)) 

;problem 3


;problem 4
(defn symmetric-number? [x]
  (loop [str-x (str x)]
    (if (empty? str-x)
      true
      (if (= (first str-x) (last str-x))
	      (recur (rest (butlast str-x)))
	      false
	      ))))

(defn product-of-digits [lower upper]
  (let [nums (range upper lower -1)]
    (apply sorted-set-by > (for [n1 nums n2 nums] (* n1 n2))))) 

(defn problem4 []
  (let [possible-nums (product-of-digits 100 1000)]
    (first (filter symmetric-number? possible-nums))))

;problem 5
(defn gcd [a b]
  (if (pos? b)
    (gcd b (rem a b))
    a))

(defn lcd [a b]
  (let [x (/ (* a b) (gcd a b))]
    x))

(defn problem5 []
  (reduce lcd (range 2 20)))

;problem6
(defn sum-of-square [x]
  (reduce + (map #(* % %) (range (inc x)))))

(defn square-of-sum [x]
  (#(* % %) (reduce + (range (inc x)))))

(defn problem6 []
  (- (square-of-sum 100) (sum-of-square 100)))

;problem7
(defn sieve [s]
  (cons (first s)
        (lazy-seq (sieve (filter #(not= 0 (mod % (first s))) (rest s))))))
(defn problem7 []
  (last (take 10001 (sieve (iterate inc 2)))))
