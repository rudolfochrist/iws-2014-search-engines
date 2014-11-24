(in-package :cl-user)
(defpackage :film-search
  (:nicknames :fls)
  (:use :cl
        :yason)
  (:export :*movie-json*
           :*movie-index*
           :index-movies
           :load-index
           :search-movie))

(in-package :film-search)

(defvar *movie-json* (merge-pathnames *default-pathname-defaults* "movie-data.json"))
(defvar *index-path* (merge-pathnames *default-pathname-defaults* "movieindex"))

(defvar *movie-index* nil)

(defun index-movies ()
  (break)
  (let ((index (make-instance 'montezuma:index
                              :path *index-path*
                              :create-p t
                              :min-merge-docs 5000))
        (movies (with-open-file (file *movie-json*)
                  (yason:parse file))))
    (format t "Started indexing...")
    (loop for movie in movies
       do (index-movie index movie))
    (format t "Finished index~%Start optimizing...")
    (montezuma:optimize index)
    (format t "Finished.")
    (montezuma:close index)))

(defun index-movie (index movie)
  (let ((doc (make-instance 'montezuma:document)))
    (montezuma:add-field doc (montezuma:make-field "title" (gethash "Title" movie)))
    (montezuma:add-field doc (montezuma:make-field "actors" (gethash "Actors" movie) :stored nil))
(montezuma:add-field doc (montezuma:make-field "plot" (gethash "Plot" movie) :stored nil))
    (montezuma:add-document-to-index index doc)))

(defun load-index ()
  (setf *movie-index* (make-instance 'montezuma:index
                                     :path *index-path*
                                     :create-p nil
                                     :create-if-missing-p nil
                                     :default-field "*"
                                     :fields '("title" "actors" "plot"))))

(defun search-movie (query)
  (unless *movie-index*
    (load-index))
  (let ((results nil))
    (montezuma:search-each *movie-index* query #'(lambda (doc score)
                                                   (declare (ignore score))
                                                   (pushnew doc results :test #'equal)))
    (print-results results)))

(defun print-results (results)
  (format t "Found: ~a movies~%" (length results))
  (dolist (doc-id results)
    (let ((doc (montezuma:get-document *movie-index* doc-id)))
      (format t "- ~a~%" (montezuma:document-value doc "title")))))
