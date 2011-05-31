(ns mungo.app_servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use mungo.core)
  (:use [appengine-magic.servlet :only [make-servlet-service-method]]))


(defn -service [this request response]
  ((make-servlet-service-method mungo-app) this request response))
