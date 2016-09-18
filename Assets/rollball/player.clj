(ns rollball.player
  (:require [arcadia.core :as a]
            [arcadia.linear :as l]
            [rollball.common :refer [ensure-hook! ensure-state! ensure-game-object!]]
            )
  (:import [UnityEngine Input GameObject Vector3]))



(defn set-count-text [go]
  (let [count (a/state go :count)
        count-text (if (> count 9)
                        "You Win!"
                        (str "Count: " count))]
  count-text)
)

(defn player-start [go]
   (a/set-state! go :win-text "")
   (a/set-state! go :count 0)
   (a/set-state! go :count-text (set-count-text go))
  )

(defn player-fixed-update [go]
  (let [move-horizontal (Input/GetAxis "Horizontal")
        move-vertical (Input/GetAxis "Vertical")
        movement (Vector3. move-horizontal, 0.0, move-vertical)
        speed (a/state go :speed)
        rb (a/cmpt go UnityEngine.Rigidbody)
        force (if rb
                (.AddForce rb (l/v3* movement speed))
                nil)
        ]
   )
)

(defn player-on-trigger-enter [go other]
  (if (.CompareTag other "Pick Up")
    (let [other_go (.gameObject other)
          _ (.SetActive other_go false)
          cur-count (a/state go :count)
          _ (a/set-state! go :count (+ cur-count 1))
          _ (set-count-text go)])
  )
)

(defn define-player-controller []
  (let [player (ensure-game-object! "player" :sphere)]
    (ensure-state! player :speed 10)
    (a/ensure-cmpt player UnityEngine.Rigidbody)
    (set! (.name player) "player")
    (set! (.position (.transform player)) (Vector3. 0 0.5 0) )
    (ensure-hook! player :start #'player-start)
    (ensure-hook! player :fixed-update #'player-fixed-update)
    (ensure-hook! player :on-trigger-enter #'player-on-trigger-enter)
    player)
)
