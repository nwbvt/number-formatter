(ns number-formatter.core)

(defn num-format
  "Formats the number input into a string representation"
  [n]
  (str n))

(defn -main
  "Main execution of the program"
  [input]
  (println (num-format (Double/parseDouble input))))
