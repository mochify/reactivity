(defproject coordinate "0.1.0"
    :description "Codename: Teamly."
    :url "http://mochify.github.com/coordinate"
    :license {:name "Mochify License"}
    :dependencies [[org.clojure/clojure "1.6.0"]
                   [cheshire            "5.3.1"]
                   [clj-time            "0.7.0"]
                   [environ             "0.5.0"]
                   [http-kit            "2.1.16"]
                   [http-kit.fake       "0.2.1"]
                   [ring                "1.3.0"]
                   [compojure           "1.1.8"]
                   [hiccup              "1.0.5"]
                   [junit/junit         "4.11"]
                   [org.xerial/sqlite-jdbc "3.7.2"]
                   [com.taoensso/carmine "2.6.2"]
                   [org.clojure/java.jdbc "0.3.3"]
                   [liberator "0.11.0"]]
    :plugins [[lein-ring "0.8.10"]
              [lein-environ "0.5.0"]]
    :ring {:handler coordinate.routes/handler}
    :profiles
        {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                             [ring-mock "0.1.5"]
                             [criterium "0.4.3"]]}})
