(defproject cljs-gol "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [quil "2.3.0"]]

  :plugins [[lein-cljsbuild "1.1.1"]]

  :hooks [leiningen.cljsbuild]

  :cljsbuild {:builds [{:source-paths ["src"]
                        :compiler {:output-to "js/main.js"
                                   :output-dir "out"
                                   :main "gol.core"
                                   :optimizations :none
                                   :pretty-print true}}]})
