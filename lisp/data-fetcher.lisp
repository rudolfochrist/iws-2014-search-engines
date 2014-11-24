
(in-package :cl-user)
(defpackage :data-fetcher
  (:nicknames :df)
  (:use :cl :drakma)
  (:export :fetch-data
           :write-data))

(in-package :data-fetcher)

(defvar *output-path* (merge-pathnames *default-pathname-defaults* "movie-data.json"))

(defvar *movie-ids-path* (merge-pathnames *default-pathname-defaults* "movie-ids.txt"))

(defvar *omdb-url* "http://www.imdbapi.com/?" "The Open Movie Database API URL")

(defun fetch-data ()
  (let ((data nil))
    (with-open-file (file *movie-ids-path*)
      (loop for line = (read-line file nil 'eof)
            until (eq line 'eof)
            do (multiple-value-bind (json)
                   (http-request (format nil "~Ai=~A&r=json" *omdb-url* line))
                 (push json data))))
    data))

(defun write-data ()
  (let ((data (fetch-data)))
    (with-open-file (file *output-path* :direction :output :if-does-not-exist :create)
      (format file "[~{~a~^,~}]" data))))
