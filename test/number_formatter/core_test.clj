(ns number-formatter.core-test
  (:require [clojure.test :refer :all]
            [number-formatter.core :refer :all]))

(deftest test-positive-integers
  (testing "Test that the formatter returns as expected for positive integers"
    (is (= "Zero" (num-format 0)))
    (is (= "One" (num-format 1)))
    (is (= "Eleven" (num-format 11)))
    (is (= "Fourteen" (num-format 14)))
    (is (= "Fifteen" (num-format 15)))
    (is (= "Sixteen" (num-format 16)))
    (is (= "Twenty" (num-format 20)))
    (is (= "Twenty" (num-format 20)))
    (is (= "Twenty-two" (num-format 22)))
    (is (= "Thirty-five" (num-format 35)))
    (is (= "One hundred twelve" (num-format 112)))
    (is (= "Two hundred twenty" (num-format 220)))
    (is (= "Four hundred" (num-format 400)))
    (is (= "Five hundred three" (num-format 503)))
    (is (= "Eight hundred fourty-five" (num-format 845)))
    (is (= "Three thousand four hundred fifty-six" (num-format 3456)))
    (is (= "Twenty-two thousand nine hundred twelve" (num-format 22912)))
    (is (= "Fifty thousand" (num-format 50000)))
    (is (= "Six hundred thousand fourty-two" (num-format 600042)))
    (is (= "Seven hundred fourty-five thousand two hundred sixteen" (num-format 745216)))
    (is (= "Nine million" (num-format 9000000)))
    (is (= "Twelve million three hundred twenty-two thousand six hundred fourteen" (num-format 12322614)))
    (is (= "Eight hundred seventy-six million five hundred twenty-three thousand four hundred twelve" (num-format 876523412)))
    (is (= "Eleven billion" (num-format 11000000000)))
    (is (= "Sixteen trillion" (num-format 16000000000000)))))

(deftest test-negatives
  (testing "Test that negative numbers work"
    (is (= "Negative three hundred" (num-format -300)))))
