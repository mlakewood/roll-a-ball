(ns rollball.pickups
  (:require [arcadia.core :as a]
            [arcadia.linear :as l]
            [rollball.common :as c]
            )
  (:import [UnityEngine Input GameObject Vector3 Quaternion Time Color])
)

(defn rotate [go]
  (.. go transform
    (Rotate (l/v3* (l/v3 15 30 45) Time/deltaTime)))
)

(defn rand-range [lowest highest]
  (+ (rand-int (+ lowest 1)) highest))

(defn set-material [go]
  (set! (.color (.material (.GetComponent go UnityEngine.Renderer))) (Color/yellow)))

(defn create-pickup [pos name]
  (let [pickup (a/create-primitive :cube)
        rotation (Quaternion. 0 45 45 45)]
    (set! (.name pickup) name)
    (set! (.position (.transform pickup)) pos )
    (set! (.localScale (.transform pickup)) (Vector3. 0.5 0.5 0.5) )
    (set! (.tag pickup) "Pick Up")
    (set! (.isTrigger (.GetComponent pickup UnityEngine.BoxCollider)) true)
    (.. pickup transform (Rotate (l/v3 45 45 45)))
    (set-material pickup)
    (c/ensure-hook! pickup :update #'rotate)
    pickup))

(defn create-pickup-coords [x-min x-max z-min z-max number]
  (map (fn [num] {:position (l/v3 (rand-range x-min x-max)
                                   0.5
                                   (rand-range z-min z-max))
                  :name (str "pickup" num)}) (range 0 number)))



(defn create-pickups []
  (let [coords (create-pickup-coords -15 5 -15 5 9)]
    (run! #(create-pickup (:position %) (:name %)) coords)
    ))
