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

;find the answer
;(even-fibonacci-numbers 4000000)

;problem 3
(defn largest-prime-factor-of [x]
  
  )


(Math/sqrt 16)