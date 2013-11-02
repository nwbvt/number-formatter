(ns number-formatter.core-test
  (:require [clojure.test :refer :all]
            [number-formatter.core :refer :all]))

(deftest test-formatter
  (testing "Test that the formatter returns as expected"
    (is (= "One" (num-format 1)))
    (is (= "Eleven" (num-format 11)))
    (is (= "Fourteen" (num-format 14)))
    (is (= "Fifteen" (num-format 15)))
    (is (= "Sixteen" (num-format 16)))
;    (is (= "Two hundred twelve" (num-format 212)))
;    (is (= "Three thousand four hundred fifty six" (num-format 3456)))
           ))
