(ns number-formatter.core
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
  (str (num-format (- n 10)) "teen"))

(defn- handle-tens
  "Handle numbers between 21 to 99"
  [n]
  (let [tens (* (int (/ n 10)) 10)
        ones (mod n 10)]
    (str (num-format tens) "-" (num-format ones))))

(defn- handle-group
  "Handle some group of numbers, such as hundreds, thousands, millions, etc"
  [n split label]
  (let [upper (int (/ n split))
        remaining (mod n split)]
    (str (num-format upper ) " " label (if (> remaining 0) (str " " (num-format remaining))))))

(defn- handle-negative
  "Handle negative numbers"
  [n]
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
        numer (.movePointRight n digits)
        denom (.movePointRight 1M digits)]
    (str numer "/" denom)))

(defn- handle-non-integer
  "Handle numbers with fractional parts"
  [n]
  (let [int-part (.intValue n)
        decimal-part (- n int-part)]
    (str (if (> int-part 0)
           (str (num-format (.longValue n)) " and "))
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
      (< n 1000000) (handle-group n 1000 "thousand")
      (< n 1000000000) (handle-group n 1000000 "million")
      (< n 1000000000000) (handle-group n 1000000000 "billion")
      (< n 1000000000000000) (handle-group n 1000000000000 "trillion")
      :else n)))

(defn -main
  "Main execution of the program"
  [input]
  (println (num-format (BigDecimal. input))))
