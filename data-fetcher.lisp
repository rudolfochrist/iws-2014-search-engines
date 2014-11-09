
(in-package :cl-user)
(defpackage :data-fetcher
  (:use :cl :drakma :yason))

(in-package :data-fetcher)

(defvar *output-path* (merge-pathnames *default-pathname-defaults* "movie-data.json"))

(defvar *movie-ids-path* (merge-pathnames *default-pathname-defaults* "movie-ids.txt"))

(defvar *omdb-url* "http://www.imdbapi.com/?" "The Open Movie Database API URL")
