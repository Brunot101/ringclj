(ns test1.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.java.jdbc :as sql]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))


 (def db-config {
              :subprotocol "mysql"
              :subname "//127.0.0.1:3306/teste?verifyServerCertificate=false&useSSL=true"
              :user "root"
              :password ""})

(defn get-all []
(  sql/query db-config ["select * from emprestimos"])
)

(defroutes app-routes
  (GET "/" [] (get-all) )
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
