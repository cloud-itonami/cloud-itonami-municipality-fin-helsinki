(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest helsinki-has-culture-basis
  (let [sb (facts/spec-basis "helsinki")]
    (is (= 10 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "helsinki" (:culture/municipality %)) sb))
    (is (every? #(= "FIN" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "espoo")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["helsinki" "espoo"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["espoo"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 3 (count (facts/by-kind "helsinki" :dish))))
  (is (= ["helsinki.beverage.lonkero"]
         (mapv :culture/id (facts/by-kind "helsinki" :beverage))))
  (is (= 1 (count (facts/by-kind "helsinki" :craft))))
  (is (empty? (facts/by-kind "espoo" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
