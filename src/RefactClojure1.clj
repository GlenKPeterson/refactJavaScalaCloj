;; Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0
(defn addMonths [ym, addedMonths]
      (let [year (:year ym), newMonth (+ (:month ym) addedMonths)]
           (cond (> newMonth 12)
                    (let [m (- newMonth 1)]
                         { :year (+ year (quot m 12)),
                           :month (+ (rem m 12) 1) })
                 (< newMonth 1)
                    { :year (dec (+ year (quot newMonth 12))),
                      :month (+ 12 (rem newMonth 12)) }
                 :else { :year year, :month newMonth })))

;; Test function
(addMonths {:year 2013, :month 7} 2)
;; {:year 2013, :month 9}
(addMonths {:year 2012, :month 12} 1)
;; {:year 2013, :month 1}
(addMonths {:year 2013, :month 1} -1)
;; {:year 2012, :month 12}

;; Implementing Class
(addMonths {:otherField1 "One", :year 2013, :month 7} 2)
;; {:year 2013, :month 9}
(addMonths {:otherField1 "One", :year 2012, :month 12} 1)
;; {:year 2013, :month 1}
(addMonths {:otherField1 "One", :year 2013, :month 1} -1)
;; {:year 2012, :month 12}
