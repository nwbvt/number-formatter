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

(deftest test-decimals
  (testing "That decimals work"
    (is (= "5/10" (num-format 0.5M)))
    (is (= "Six and 2/10" (num-format 6.2M)))
    (is (= "Ten and 02/100" (num-format 10.02M)))
    (is (= "Three hundred twenty-two and 16/100" (num-format 322.16M)))
    (is (= "Eighteen and 123456789/1000000000" (num-format 18.123456789M)))))

(deftest test-negatives
  (testing "That negative numbers work"
    (is (= "Negative three hundred" (num-format -300)))
    (is (= "Negative 5/10" (num-format -0.5M)))
    (is (= "Negative six and 2/10" (num-format -6.2M)))))

(deftest test-example
  (testing "That the given example works (since it would be kinda embarrassing if it did not)"
    (is (= "Two thousand five hundred twenty-three and 04/100" (num-format 2523.04M)))))
