(ns reactivity.routes
  (:use ring.middleware.stacktrace)
  (:import [java.net URL])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.response :as response]
            [compojure.core :refer [defroutes GET POST DELETE ANY context]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [clojure.java.io :as io]
            [reactivity.api.rest.user :as userapi]
            [reactivity.api.rest.channel :as channelapi]
            ))

; There are routes to interact with the database, including:
; - getting status by channel
; - posting status to a channel
; - creating a channel
; - creating a user
(defroutes reactivity-routes
  (context "/api/v1" []
    (GET "/user/:user" [user] {:user (first (userapi/get-user user))})
    (POST "/user" request (userapi/create-user (:body request)))
    (GET "/channel/:channel-id" [channel-id] (channelapi/get-channel channel-id))
    (POST "/channel" request (channelapi/create-channel (:body request)))
         ; (userapi/create-user))
    ;(GET "/channel/:channel" [channel] (str "<h1>" channel "</h1>"))
    ;(GET "/channel/:channel/users" [channel] (users-by-channel channel))
    ;(POST "/channel" [] (create-channel))
    (POST "/status" [] (str "hello")))
  (route/not-found "Page not found"))

           
(def app
  (-> (handler/api reactivity-routes)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)))

(defn -main []
  (run-jetty #'app {:port 3000}))

