(ns gol.core
  (:require [quil.core :as q :include-macros true]
            [quil.middlewares.fun-mode :refer [fun-mode]]))

(defn neighbours [[x y]]
  (for [dx [-1 0 1] dy (if (= 0 dx) [-1 1] [-1 0 1])]
    [(+ dx x) (+ dy y)]))

(defn next-gen [cells]
  (set (for [[loc n] (frequencies (mapcat neighbours cells))
             :when (or (= n 3) (and (= n 2) (cells loc)))] loc)))

(defn setup! []
  (q/frame-rate 12) (q/stroke 255) (q/stroke-weight 2) (q/fill 150)
  {:cam -80
   :cells #{[2 7] [4 6] [4 7] [6 3] [6 4] [6 5] [8 2] [8 3] [8 4] [9 3]}})

(defn draw! [{:keys [cam cells]}]
  (q/background 255)
  (doseq [[x y] cells :let [d 10]]
    (q/rect (+ cam (* x d)) (+ cam (* y d)) d d)))

(q/defsketch grid :size [250 250]
  :setup setup! :draw draw! :middleware [fun-mode]
  :update #(-> % (update :cam + 0.75) (update :cells next-gen)))