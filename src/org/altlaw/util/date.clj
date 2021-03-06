(ns org.altlaw.util.date
  (:use [clojure.contrib.test-is :only (with-test is)])
  (:import (java.text SimpleDateFormat FieldPosition ParsePosition)
           (java.util Date)))

(def #^{:private true} *iso-date-format*
     (SimpleDateFormat. "yyyy-MM-dd"))

(def #^{:private true} *long-date-format*
     (SimpleDateFormat. "MMMM d, yyyy"))

(def #^{:private true} *datetime-filename-format*
     (SimpleDateFormat. "yyyyMMddHHmmss"))

(defn parse-iso-date [string]
  (when (seq string)
    (.parse *iso-date-format* string
            (ParsePosition. 0))))

(with-test
    (defn format-long-date
      "Converts a String date from YYYY-MM-DD format to 
      'January 1, 2009' format."
      [string]
      (when (seq string)
        (str (.format *long-date-format*
                      (parse-iso-date string)
                      (StringBuffer.)
                      (FieldPosition. 0)))))
  (is (= "January 1, 2009" (format-long-date "2009-01-01")))
  (is (= nil (format-long-date ""))))

(defn filename-timestamp []
  (.format *datetime-filename-format* (Date.)))

