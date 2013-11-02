(ns number-formatter.core
  (:gen-class :main true)
  (:require [clojure.string :as string]))

(def constants {0 "zero"
                1 "one"
                2 "two"
                3 "three"
                4 "four"
                5 "five"
                6 "six"
                7 "seven"
                8 "eight"
                9 "nine"
                10 "ten"
                11 "eleven"
                12 "twelve"
                13 "thirteen"
                15 "fifteen"
                18 "eighteen"
                20 "twenty"
                30 "thirty"
                40 "fourty"
                50 "fifty"
                60 "sixty"
                70 "seventy"
                80 "eighty"
                90 "ninety"})

(declare num-format)

(defn- handle-teen
  "Teens are a bit wierd"
  [n]
  (assert (> n 10))
  (assert (< n 20))
  (str (num-format (- n 10)) "teen"))

(defn- handle-tens
  "Handle numbers between 21 to 99"
  [n]
  (assert (> n 20))
  (assert (< n 100))
  (let [tens (* (int (/ n 10)) 10)
        ones (int (mod n 10))]
    (str (num-format tens) "-" (num-format ones))))

(defn- handle-group
  "Handle some group of numbers, such as hundreds, thousands, millions, etc"
  [n split label]
  (let [upper (int (/ n split))
        remaining (mod n (bigint split))]
    (str (num-format upper ) " " label (if (> remaining 0) (str " " (num-format remaining))))))

(defn- handle-negative
  "Handle negative numbers"
  [n]
  (assert (< n 0))
  (str "Negative " (num-format (* -1 n))))

(defn- has-fractional?
  "Does this number have a fractional part?"
  [n]
  (and
    (= java.math.BigDecimal (type n))
    (not (== n (.toBigInteger n)))))

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
  (let [int-part (.intValue n)
        decimal-part (- n int-part)]
    (str (if (> int-part 0)
           (str (num-format (.toBigInteger n)) " and "))
         (handle-decimal decimal-part))))

(defn num-format
  "Formats the number input into a string representation"
  [n]
  (string/capitalize
    (cond
      (contains? constants n) (constants n)
      (neg? n) (handle-negative n)
      (has-fractional? n) (handle-non-integer n)
      (< n 20) (handle-teen n)
      (< n 100) (handle-tens n)
      (< n 1000) (handle-group n 100 "hundred")
      (< n 1E6) (handle-group n 1000 "thousand")
      (< n 1E9) (handle-group n 1E6M "million")
      (< n 1E12) (handle-group n 1E9M "billion")
      (< n 1E15) (handle-group n 1E12M "trillion")
      (< n 1E18) (handle-group n 1E15M "quadrillion")
      (< n 1E21) (handle-group n 1E18M "quintillion")
      (< n 1E24) (handle-group n 1E21M "sextillion")
      (< n 1E27) (handle-group n 1E24M "septillion")
      (< n 1E30) (handle-group n 1E27M "octillion")
      (< n 1E33) (handle-group n 1E30M "nonillion")
      (< n 1E36) (handle-group n 1E33M "decillion")
      (< n 1E39) (handle-group n 1E36M "undecillion")
      (< n 1E42) (handle-group n 1E39M "duodecillion")
      (< n 1E45) (handle-group n 1E42M "tredecillion")
      :else n)))

(defn -main
  "Main execution of the program"
  [input]
  (println (num-format (BigDecimal. input))))
