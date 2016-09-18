(ns rollball.core
  (:require [arcadia.core :refer [objects-named]]
            [rollball.player :as p]
            [rollball.main-camera :as cam]
            [rollball.pickups :as pick]
            [rollball.field :as field]
    ))

(defn start [go]
  (let [field (field/build-environment)
        player (p/define-player-controller)
        main-camera (cam/create-camera)
        pickups (pick/create-pickups)
        ]
  pickups
  ))

(defn get-player []
  (first (objects-named "player")))

(defn get-camera []
  (first (objects-named "Main Camera")))