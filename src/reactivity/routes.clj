(ns reactivity.routes
  (:use ring.middleware.stacktrace)
  (:import [java.net URL])
  (:require [liberator.core :refer [resource defresource]] 
            [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.response :as response]
            [compojure.core :refer [defroutes GET POST ANY context]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]
            [clojure.java.io :as io]
            [clojure.data.json :as json]))


(defroutes reactivity-app
  (context "/api" []
    (GET "/user/:user" [user] (str "<h1>" user "</h1>"))
    (GET "/channel/:channel" [channel] (str "<h1>" channel "</h1>"))
    (GET "/channel/:channel/users" [channel] (users-by-channel channel))
    (POST "/status" [] (post-status))))
           
(def handler
  (-> reactivity-app (wrap-params)))
(run-jetty #'handler {:port 3000})

