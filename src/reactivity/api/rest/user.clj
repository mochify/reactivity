(ns reactivity.api.rest.user
  (:require [yesql.core :refer [defqueries]]
            [crypto.password.bcrypt :as bcrypt]))

(defqueries "reactivity/models/queries.sql")

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/mochify"
              :user "birryree"})

(defn create-user [request]
  (let [email (get-in request [:email])
        first-name (get-in request [:firstName])
        last-name (get-in request [:lastName])
        plain-password (get-in request [:password])
        username (get-in request [:username])
        db-ret (create-person! db-spec email username first-name
                               last-name (bcrypt/encrypt plain-password))]
    (cond (< db-ret 1) false
          (= db-ret 1) true
          )))
       ;(prn email first-name last-name plain-password)))

(defn get-user [username]
  (get-person-by-username db-spec username))
