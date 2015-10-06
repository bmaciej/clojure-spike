(defn as-rack [rack-id]
  {
   :rack-id rack-id
   :state   :EMPTY
   :bike-id nil
   })

(def racks
  (let [as-atom-rack (comp atom as-rack)
        rack-ids (range 40)
        racks (map as-atom-rack rack-ids)]
    (vec racks)))

(defn put-bike [rack bike-id]
  (assoc rack :state :FULL :bike-id bike-id))

(defn put-bike! [rack-id bike-id]
  (let [rack (get racks rack-id)]
    (swap! rack put-bike bike-id)))

(println @(get racks 2))
(put-bike! 2 40)
(println @(get racks 2))
