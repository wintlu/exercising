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

;problem8
(def problem8-data "
73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450")

(defn to-positive-digits []
  (let [positive-strings (remove #(or (empty? %) (< (count %) 5))
                                 (clojure.string/split 
																    (clojure.string/join 
																      (clojure.string/split-lines problem8-data)) #"0" ))]
    (map (fn [positive-string] 
           (map #(int (- (int %) (int \0))) positive-string)) 
         positive-strings)))

;;(to-int-list) 75296345
(defn biggest-product [coll]
  (loop [cur-product (reduce * (take 5 coll))
         max-product cur-product
         rest-coll coll]
      (if (not (first (drop 5 rest-coll)))
        max-product
        (let [next-product (* (first (drop 5 rest-coll)) (/ cur-product (first rest-coll)))]
          (recur next-product
               (max next-product max-product)
               (rest rest-coll))
        ))))
    
(defn problem8 []
    (apply max (map #(biggest-product %) (to-positive-digits))))
