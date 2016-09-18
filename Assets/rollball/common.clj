(ns rollball.common
  (:require [arcadia.core :as a]))

(defn ensure-state!
  [go kw v]
  (or (a/state go kw)
      (a/set-state! go kw v)))

(defn ensure-hook!
  [go kw v]
  (let [hook (first (a/hooks go kw))]
      (if hook
        hook
        (a/hook+ go kw v))))

(defn ensure-game-object! [name type]
  (let [player (first (a/objects-named name))]
    (if player
      player
      (a/create-primitive type))
    ))

(defn normc [value]
  (/ value 255.0))
