(ns clojure-spike1.destination_enricher
  (:gen-class))

(defn destination [trip destination-field segment-filter]

    (let [
          segments (:segments trip)
          segment-of-type (first (filter segment-filter segments))
          last-leg (last (:legs segment-of-type))
          destination (destination-field last-leg)]
      destination))

(defn trip-destination [trip]
  (letfn
    [(of-type? [segment-types segment] ((set segment-types) (:segmentType segment))) ]
          (or
            (destination trip :startPoint (partial of-type? ["HOTEL"]))
            (destination trip :endPoint (partial of-type? ["FLIGHT" "RAIL"]))
            (destination trip :endPoint (partial of-type? ["TRANSFER"]))
            (destination trip :endPoint (constantly true) ))))
