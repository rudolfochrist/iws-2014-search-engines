(in-package :cl-user)
(defpackage :film-search-asd
  (:use :cl :asdf))

(in-package :film-search-asd)

(defsystem film-search
  :version "0.0.1"
  :author "Sebastian Christ <rudolfo.christ@gmail.com"
  :description "Absurd IMDB clone"
  :serial t
  :depends-on (:montezuma)
  :components ((:file "film-search")))
