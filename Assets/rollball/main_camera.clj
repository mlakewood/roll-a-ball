(ns rollball.main-camera
  (:require [arcadia.core :as a]
            [arcadia.linear :as l]
            [rollball.player :as rplayer]
            [rollball.common :refer [ensure-state! ensure-hook!]]
            )
  (:import [UnityEngine Input GameObject Vector3])
)

(defn cam-offset
  [cam target]
  (l/v3- (.. cam transform position)
         (.. target transform position)))

(defn camera-late-update
  [go]
  (let [^GameObject target (ensure-state! go ::cam-target (first (a/objects-named "player")))
        ^Vector3 offset (ensure-state! go ::cam-offset (cam-offset go target))]
      (set! (.. go transform position)
          (l/v3+ offset
                  (.. target transform position)))
   )
  )

(defn create-camera []
  (let [player (first (a/objects-named "player"))
        camera (first (a/objects-named "Main Camera"))
        cam-xform (a/ensure-cmpt camera UnityEngine.Transform)]
     (a/set-state! camera ::cam-target player)
     (a/set-state! camera ::cam-offset 
         (l/v3- (.. camera transform position) (.. player transform position)))
     (ensure-hook! camera :late-update #'camera-late-update)
     ))
