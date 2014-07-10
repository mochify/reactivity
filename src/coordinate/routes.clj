(ns coordinate.routes
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

(defresource fooresource [txt]
  :available-media-types ["text/plain"]
  :handle-ok (fn [_] (format "The text is %s" txt)))

(defresource babelresource []
  :available-media-types ["text/plain", "text/html",
                          "application/json", "application/clojure"]
  :handle-ok
    #(let [media-type (get-in % [:representation :media-type])]
       (condp = media-type
         "text/plain" "You wanted plain"
         "text/html" "<html><h1>You asked for html?</h1></html>"
         {:message "You requested media type" :media-type media-type}))
  :handle-not-acceptable "Invalid media type")

(defonce entries (ref {}))

(defn build-entry-url [request id]
  (URL. (format "%s://%s:%s%s/%s"
                (name (:scheme request))
                (:server-name request)
                (:server-port request)
                (:uri request)
                (str id))))

;; convert the body to a reader. Useful for testing in the repl
;; where setting the body to a string is much simpler.
(defn body-as-string [ctx]
    (if-let [body (get-in ctx [:request :body])]
          (condp instance? body
                  java.lang.String body
                  (slurp (io/reader body)))))

;; For PUT and POST parse the body as json and store in the context
;; under the given key.
(defn parse-json [context key]
    (when (#{:put :post} (get-in context [:request :request-method]))
          (try
                  (if-let [body (body-as-string context)]
                            (let [data (json/read-str body)]
                                        [false {key data}])
                            {:message "No body"})
                  (catch Exception e
                            (.printStackTrace e)
                            {:message (format "IOException: %s" (.getMessage e))}))))

;; For PUT and POST check if the content type is json.
(defn check-content-type [ctx content-types]
    (if (#{:put :post} (get-in ctx [:request :request-method]))
          (or
                 (some #{(get-in ctx [:request :headers "content-type"])}
                                  content-types)
                 [false {:message "Unsupported Content-Type"}])
          true))

(defresource list-resource
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :known-content-type? #(check-content-type % ["application/json"])
  :malformed? #(parse-json % ::data)
  :post! #(let [id (str (inc (rand-int 100000)))]
            (dosync (alter entries assoc id (::data %)))
            {::id id})
  :post-redirect? true
  :location #(build-entry-url (get % :request) (get % ::id))
  :handle-ok #(map (fn [id] (str (build-entry-url (get % :request) id)))
                   (keys @entries)))

(defresource entry-resource [id]
    :allowed-methods [:get :put :delete]
    :known-content-type? #(check-content-type % ["application/json"])
    :exists? (fn [_]
                  (let [e (get @entries id)]
                    (if-not (nil? e)
                        {::entry e})))
    :existed? (fn [_] (nil? (get @entries id ::sentinel)))
    :available-media-types ["application/json"]
    :handle-ok ::entry
    :delete! (fn [_] (dosync (alter entries assoc id nil)))
    :malformed? #(parse-json % ::data)
    :can-put-to-missing? false
    :put! #(dosync (alter entries assoc id (::data %)))
    :new? (fn [_] (nil? (get @entries id ::sentinel))))

(defroutes coordinate-app
  (ANY "/" [] (resource))
  (ANY "/babel" [] (babelresource))
  (ANY "/foo" [] (resource :available-media-types ["text/html"]
                           :handle-ok "<html>Hello, everything's good.</html>"))
  (ANY "/foo/:txt" [txt] (fooresource txt))
  (ANY ["/collection/:id" :id #".*"] [id] (entry-resource id))
  (ANY "/collection" [] list-resource)
  (context "/api" []
    (POST "/update/:team" [team] (str team))
    (GET "/update/:team" [team] (str "<h1>" team "</h1>")))
  (ANY "*" [] "<h1>404!</h1>"))

;(def app
;  (handler/site app-routes))

(def handler
  (-> coordinate-app (wrap-params)))

(run-jetty #'handler {:port 3000})

