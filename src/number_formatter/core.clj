(ns number-formatter.core
  (:require [clojure.string :as string]))

(defn- handle-constant
  "Handle numbers small enough that they can't be broken down further"
  [n]
  (case n
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
    13 "thirteen"))

(defn- handle-teen
  "Teens are a bit wierd"
  [n]
  (str (handle-constant (- n 10)) "teen"))

(defn num-format
  "Formats the number input into a string representation"
  [n]
  (string/capitalize
    (cond
      (<= n 13) (handle-constant n)
      (<= n 19) (handle-teen n)
      :else n)))

(defn -main
  "Main execution of the program"
  [input]
  (println (num-format (Double/parseDouble input))))
