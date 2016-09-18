(ns rollball.field
  (:require [arcadia.core :as a]
            [arcadia.linear :as l]
            [rollball.common :as c]
            )
  (:import [UnityEngine Input GameObject Vector3 Quaternion Time Color])
)

(def environment {
  :ground {:name "ground"
           :position (l/v3 0 0 0)
           :scale (l/v3 2 1 2)
           :color (Color. (c/normc 32.0) (c/normc 64.0) (c/normc 128.0) 1.0)
          }
  :west-wall {:name "west-wall"
              :position (l/v3 -10 0 0)
              :scale (l/v3 0.5 2 20.5)
              }
  :east-wall {:name "east-wall"
              :position (l/v3 10 0 0)
              :scale (l/v3 0.5 2 20.5)
              }
  :south-wall {:name "south-wall"
              :position (l/v3 0 0 -10)
              :scale (l/v3 20.5 2 0.5)
              }
  :north-wall {:name "north-wall"
              :position (l/v3 0 0 10)
              :scale (l/v3 20.5 2 0.5)
              }
  })


(defn build-wall [values]
  (let [wall (a/create-primitive :cube)]
    (set! (.name wall) (:name values))
    (set! (.position (.transform wall)) (:position values))
    (set! (.localScale (.transform wall)) (:scale values))
  wall))

(defn set-material [go]
  (set! (.color (.material (.GetComponent go UnityEngine.Renderer))) 
    (Color. (c/normc 32.0) (c/normc 64.0) (c/normc 128.0) 1.0)))

(defn create-ground []
  (let [ground (a/create-primitive :plane)]
    (set! (.name ground) "ground")
    (set! (.position (.transform ground)) (l/v3 0 0 0))
    (set! (.localScale (.transform ground)) (l/v3 2 1 2))
    (set-material ground)
    ground))

(defn build-environment []
  (create-ground)
  (doall 
    (map #(build-wall (% environment)) [:west-wall :east-wall :south-wall :north-wall])))