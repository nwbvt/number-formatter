(ns number-formatter.core
  (:gen-class :main true)
  (:require [clojure.string :as string]))

(def constants {0 "zero", 1 "one", 2 "two", 3 "three", 4 "four", 5 "five", 6 "six", 7 "seven", 8 "eight",
                9 "nine", 10 "ten", 11 "eleven", 12 "twelve", 13 "thirteen", 14 "fourteen", 15 "fifteen",
                16 "sixteen", 17 "seventeen", 18 "eighteen", 19 "nineteen", 20 "twenty", 30 "thirty",
                40 "fourty", 50 "fifty", 60 "sixty", 70 "seventy", 80 "eighty", 90 "ninety"})

(declare num-format)

(defn- handle-tens
  "Handle numbers between 21 to 99"
  [n]
  (assert (> n 20))
  (assert (< n 100))
  (let [tens (bigint (* (int (/ n 10)) 10))
        ones (bigint (mod n 10))]
    (str (num-format tens) "-" (num-format ones))))

(defn- handle-group
  "Handle some group of numbers, such as hundreds, thousands, millions, etc"
  [n split label]
  (let [upper (bigint (/ n split))
        remaining (mod n (bigint split))]
    (str (num-format upper ) " " label (if (> remaining 0) (str " " (num-format remaining))))))

(defn- handle-negative
  "Handle negative numbers"
  [n]
  (assert (< n 0))
  (str "Negative " (num-format (* -1 n))))

(defn- has-decimal?
  "Does this number have a fractional part?"
  [n]
  (and
    (= java.math.BigDecimal (type n))
    (not (== n (bigint n)))))

(defn- handle-decimal
  "Handle a decimal part of a number"
  [n]
  (let [digits (* -1 (Math/log10 (.ulp n)))
        numer (long (.movePointRight n digits))
        denom (long (.movePointRight 1M digits))
        format-string (str "%0" (int digits) "d/%d")]
    (format format-string numer denom)))

(defn- handle-non-integer
  "Handle numbers with fractional parts"
  [n]
  (let [int-part (.toBigInteger n)
        decimal-part (- n int-part)]
    (str (if (> int-part 0)
           (str (num-format (bigint n)) " and "))
         (handle-decimal decimal-part))))

(defn num-format
  "Formats the number input into a string representation"
  [n]
  (string/capitalize
    (cond
      (neg? n) (handle-negative n)
      (has-decimal? n) (handle-non-integer n)
      (contains? constants (.toBigInteger n)) (constants (.toBigInteger n))
      (< n 100) (handle-tens n)
      (< n 1000) (handle-group n 100 "hundred")
      (< n 1E6M) (handle-group n 1000 "thousand")
      (< n 1E9M) (handle-group n 1E6M "million")
      (< n 1E12M) (handle-group n 1E9M "billion")
      (< n 1E15M) (handle-group n 1E12M "trillion")
      (< n 1E18M) (handle-group n 1E15M "quadrillion")
      (< n 1E21M) (handle-group n 1E18M "quintillion")
      (< n 1E24M) (handle-group n 1E21M "sextillion")
      (< n 1E27M) (handle-group n 1E24M "septillion")
      (< n 1E30M) (handle-group n 1E27M "octillion")
      (< n 1E33M) (handle-group n 1E30M "nonillion")
      (< n 1E36M) (handle-group n 1E33M "decillion")
      (< n 1E39M) (handle-group n 1E36M "undecillion")
      (< n 1E42M) (handle-group n 1E39M "duodecillion")
      (< n 1E45M) (handle-group n 1E42M "tredecillion")
      (< n 1E48M) (handle-group n 1E45M "quattuordecillion")
      (< n 1E51M) (handle-group n 1E48M "quindecillion")
      (< n 1E54M) (handle-group n 1E51M "sexdecillion")
      (< n 1E57M) (handle-group n 1E54M "septdecillion")
      (< n 1E60M) (handle-group n 1E57M "octodecillion")
      (< n 1E63M) (handle-group n 1E60M "vigintillion")
      :else n)))

(defn -main
  "Main execution of the program"
  [input]
  (println (num-format (BigDecimal. input))))
