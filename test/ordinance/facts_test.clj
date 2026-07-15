(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest helsinki-has-spec-basis
  (let [sb (facts/spec-basis "helsinki")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://www.hel.fi/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "stockholm")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["helsinki" "stockholm"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["stockholm"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["helsinki.administrative-regulations-rules-of-operation"]
         (mapv :ordinance/id (facts/by-topic "helsinki" :governance))))
  (is (empty? (facts/by-topic "helsinki" :labor)))
  (is (empty? (facts/by-topic "stockholm" :environment))))
