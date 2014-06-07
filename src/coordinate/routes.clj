(ns coordinate.routes
  (:use ring.middleware.stacktrace)
  (:require [liberator.core :refer [resource defresource]] 
            [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.response :as response]
            [compojure.core :refer [defroutes GET POST ANY context]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]))

(defroutes coordinate-app
  (ANY "/" [] (resource))
  (ANY "/foo" [] (resource :available-media-types ["text/html"]
                           :handle-ok "<html>Hello, everything's good.</html>"))
  (context "/api" []
    (POST "/update/:team" [team] (str team))
    (GET "/update/:team" [team] (str "<h1>" team "</h1>"))))

;(def app
;  (handler/site app-routes))

(def handler
  (-> coordinate-app (wrap-params)))

(run-jetty #'handler {:port 8080})
