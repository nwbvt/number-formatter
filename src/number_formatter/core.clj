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

(defn- handle-hundreds
  "Handle numbers in the hundreds"
  [n]
  (let [hundreds (int (/ n 100))
        remaining (mod n 100)]
    (str (num-format hundreds) " hundred" (if (> remaining 0) (str " " (num-format remaining))))))

(defn- handle-thousands
  "Handle numbers in the thousands"
  [n]
  (let [thousands (int (/ n 1000))
        remaining (mod n 1000)]
    (str (num-format thousands) " thousand" (if (> remaining 0) (str " " (num-format remaining))))))

(defn num-format
  "Formats the number input into a string representation"
  [n]
  (string/capitalize
    (cond
      (contains? constants n) (constants n)
      (< n 20) (handle-teen n)
      (< n 100) (handle-tens n)
      (< n 1000) (handle-hundreds n)
      (< n 1000000) (handle-thousands n)
      :else n)))

(defn -main
  "Main execution of the program"
  [input]
  (println (num-format (Double/parseDouble input))))
