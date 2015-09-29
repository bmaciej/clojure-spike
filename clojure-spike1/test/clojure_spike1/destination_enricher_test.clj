(ns clojure-spike1.destination_enricher_test
  (:require [clojure.test :refer :all]
            [clojure-spike1.destination_enricher :refer :all]))

(def hotel-trip
  {:segments [
    {
     :segmentType "FLIGHT"
     :legs [{:endPoint {:city "Flight-City" :countryCode "ES"}}]
    }
    {
     :segmentType "HOTEL"
     :legs [{:startPoint{:city "Hotel-City1" :countryCode "IE"}} {:startPoint{:city "Hotel-City2" :countryCode "IE"}}]
     }]
   }
  )

(def flight-trip
  {:segments [
    {
     :segmentType "FLIGHT"
     :legs [
      {:endPoint
        {:city "Flight-City" :countryCode "ES"}
       }]
     }]
   }
  )

(def rail-flight
  {:segments [
    {
     :segmentType "RAIL"
     :legs [
      {:endPoint
        {:city "Rail-City" :countryCode "ES"}
       }]
     }
    {
     :segmentType "FLIGHT"
     :legs [
      {:endPoint
        {:city "Flight-City" :countryCode "ES"}
       }]
     }
    ]
   }
  )

(def flight-rail
  {:segments [
    {
     :segmentType "FLIGHT"
     :legs [
      {:endPoint
        {:city "Flight-City" :countryCode "ES"}
       }]
     }
    {
     :segmentType "RAIL"
     :legs [
      {:endPoint
        {:city "Rail-City" :countryCode "ES"}
       }]
     }
    ]
   }
  )

(def transfer
  {:segments [
    {
     :segmentType "TRANSFER"
     :legs [
      {:endPoint
        {:city "Transfer-City" :countryCode "ES"}
       }]
     }
    ]
   }
  )

(def default {:segments [{:legs [ {:endPoint {:city "Default-City" :countryCode "ES"}}]}]})

(deftest enrichment-tests
  (testing "empty-trip"
    (is (= (trip-destination {}) nil)))
  (testing "hotel-segment"
    (is (= (trip-destination hotel-trip) {:city "Hotel-City2" :countryCode "IE"})))
  (testing "flight-segment"
    (is (= (trip-destination flight-trip) {:city "Flight-City" :countryCode "ES"})))
  (testing "rail-flight"
    (is (= (trip-destination rail-flight) {:city "Rail-City" :countryCode "ES"})))
  (testing "flight-rail"
    (is (= (trip-destination flight-rail) {:city "Flight-City" :countryCode "ES"})))
  (testing "transfer"
    (is (= (trip-destination transfer) {:city "Transfer-City" :countryCode "ES"})))
  (testing "default"
    (is (= (trip-destination default) {:city "Default-City" :countryCode "ES"})))
  )

(run-tests 'clojure-spike1.destination_enricher_test)

