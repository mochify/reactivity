(ns reactivity.api.rest.channel
  (:require [yesql.core :refer [defqueries]]
            [crypto.password.bcrypt :as bcrypt]))

(defqueries "reactivity/models/queries.sql")

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/mochify"
              :user "birryree"})

(defn create-channel [req]
  (let [name (get-in req [:name])] 
    (prn req)))

(defn get-channel [channel-name]
  (prn channel-name)
  channel-name)
