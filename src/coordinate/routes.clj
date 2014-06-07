(ns coordinate.routes
  (:use compojure.core
          coordinate.views
          ring.adapter.jetty
          ring.middleware.reload
          ring.middleware.stacktrace)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.response :as response]))

(defroutes app-routes
  (GET "/" [] "hi"))

(def app
  (handler/site app-routes))
