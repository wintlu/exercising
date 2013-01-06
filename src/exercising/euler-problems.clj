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

;(largest-palindrome-product)

;problem 5
(defn primes-to [x]
  (loop [primes []
         raw (range 2 (inc x))]
    (if (empty? raw)
      primes
      (recur (conj primes (first raw))
             (remove #(zero? (rem % (first raw))) raw)))))

(defn gcd [a b]
  (if (zero? b) 
    a
    (if (> a b)
      (gcd (-' a b) b)
      (gcd a (-' b a)))))

(defn lcd [a b]
  (/ (*' a b) (gcd a b)))

(defn problem5 []
  (reduce lcd (range 2 14)))
