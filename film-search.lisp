(in-package :cl-user)
(defpackage :film-search
  (:nicknames :fls)
  (:use :cl
        :yason)
  (:export :*movie-json*
           :index-movies))

(in-package :film-search)

(defvar *movie-json* (merge-pathnames *default-pathname-defaults* "movie-data.json"))
(defvar *index-path* (merge-pathnames *default-pathname-defaults* "index"))

(defun index-movies ()
  (let ((index (make-instance 'montezuma:index
                              :path *index-path*
                              :analyzer (make-instance 'montezuma:whitespace-analyzer)))
        (movies (with-open-file (file *movie-json*)
                  (yason:parse file))))
    (format t "Started indexing...")
    (loop for movie in movies
       do (index-movie index movie))
    (format t "Finished index~%Start optimizing...")
    (montezuma:optimize index)
    (format t "Finished.")))

(defun index-movie (index movie)
  (let ((doc (make-instance 'montezuma:document)))
    (montezuma:add-field doc (montezuma:make-field "title" (gethash "Title" movie)))
    (montezuma:make-field "actors" (gethash "Actors" movie) :stored nil)
    (montezuma:make-field "plot" (gethash "Plot" movie) :stored nil)
    (montezuma:add-document-to-index index doc)))
