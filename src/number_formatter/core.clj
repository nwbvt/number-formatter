(ns number-formatter.core
  (:require [clojure.string :as string]))

(def constants {1 "one"
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
                15 "fifteen"})

(defn- handle-teen
  "Teens are a bit wierd"
  [n]
  (str (constants (- n 10)) "teen"))

(defn num-format
  "Formats the number input into a string representation"
  [n]
  (string/capitalize
    (cond
      (contains? constants n) (constants n)
      (<= n 19) (handle-teen n)
      :else n)))

(defn -main
  "Main execution of the program"
  [input]
  (println (num-format (Double/parseDouble input))))
