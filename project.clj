(defproject reactivity "0.1.0"
    :description "Codename: Teamly."
    :url "http://mochify.github.com/reactivity"
    :license {:name "Mochify License"}
    :dependencies [[org.clojure/clojure "1.6.0"]
                   [cheshire            "5.3.1"]
                   [clj-time            "0.8.0"]
                   [environ             "1.0.0"]
                   [http-kit            "2.1.16"]
                   [http-kit.fake       "0.2.1"]
                   [ring                "1.3.1"]
                   [ring/ring-json      "0.3.1"]
                   [compojure           "1.1.9"]
                   [hiccup              "1.0.5"]
                   [expectations        "2.0.9"]
                   [com.taoensso/carmine "2.7.0" :exclusions [org.clojure/clojure]]
                   [yesql               "0.4.0"]
                   [crypto-password     "0.1.3"]
                   [org.postgresql/postgresql "9.3-1102-jdbc41"]
                   [liberator           "0.12.1"]]
    :plugins [[lein-ring "0.8.11"]
              [lein-environ "1.0.0"]]
    :ring {:handler reactivity.routes/reactivity-routes}
    :profiles
        {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                             [ring-mock "0.1.5"]
                             [criterium "0.4.3"]]}})
