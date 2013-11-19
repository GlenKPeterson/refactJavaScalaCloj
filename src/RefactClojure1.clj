;; Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0
(defn addMonths [ym, addedMonths]
      (let [newMonth (+ (:month ym) addedMonths)]
           (cond (> newMonth 12)
                    ;; convert to zero-based months for math
                    (let [m (- newMonth 1)]
                         ;; Carry any extra months over to the year
                         (assoc ym :year (+ (:year ym) (quot m 12)),
                                   :month (+ (rem m 12) 1)))
                 (< newMonth 1)
                    ;; Carry any extra months over to the year, but the
                    ;; first year in this case is still year-1
                    (let [y (dec (+ (:year ym) (quot newMonth 12))),
                          ;; Adjust negative month to be within one year.
                          ;; To get the positive month, subtract it from 12
                          m (+ 12 (rem newMonth 12))]
                       (assoc ym :year y :month m))
                 :else (assoc ym :month newMonth))))

;; Test function
(addMonths {:year 2013, :month 7} 2)
;; {:year 2013, :month 9}
(addMonths {:year 2012, :month 12} 1)
;; {:year 2013, :month 1}
(addMonths {:year 2013, :month 1} -1)
;; {:year 2012, :month 12}

;; Implementing Class
(addMonths {:otherField1 "One", :year 2013, :month 7} 2)
;; {:otherField1 "One", :year 2013, :month 9}
(addMonths {:otherField1 "One", :year 2012, :month 12} 1)
;; {:otherField1 "One", :year 2013, :month 1}
(addMonths {:otherField1 "One", :year 2013, :month 1} -1)
;; {:otherField1 "One", :year 2012, :month 12}
