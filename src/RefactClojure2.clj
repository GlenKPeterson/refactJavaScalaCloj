;; Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0

;; New!
(defn ymToOld [ym] (dissoc (assoc ym :year (quot (:yyyyMm ym) 100)
                                     :month (rem (:yyyyMm ym) 100))
                           :yyyyMm))

;; New!
(defn ymToNew [ym] (dissoc (assoc ym :yyyyMm (+ (* (:year ym) 100)
                                                (:month ym)))
                           :year :month))

;; New! start of this function, but rest is unchanged
(defn addMonths [ym, addedMonths]
      (if (contains? ym :yyyyMm)
          (-> (ymToOld ym)
              (addMonths addedMonths)
              ymToNew)
          (let [newMonth (+ (:month ym) addedMonths)]
               ;; Unchanged
               (cond (> newMonth 12)
                        (let [m (- newMonth 1)]
                             (assoc ym :year (+ (:year ym) (quot m 12)),
                                       :month (+ (rem m 12) 1)))
                     (< newMonth 1)
                        (let [y (dec (+ (:year ym) (quot newMonth 12))),
                              m (+ 12 (rem newMonth 12))]
                           (assoc ym :year y :month m))
                     :else (assoc ym :month newMonth)))))

;; Test original data
(addMonths {:year 2013, :month 7} 2)
;; {:year 2013, :month 9}
(addMonths {:year 2012, :month 12} 1)
;; {:year 2013, :month 1}
(addMonths {:year 2013, :month 1} -1)
;; {:year 2012, :month 12}

;; Test new data
(addMonths {:yyyyMm 201307} 2)
;; {:yyyyMm 201309}
(addMonths {:yyyyMm 201212} 1)
;; {:yyyyMm 201301}
(addMonths {:yyyyMm 201301} -1)
;; {:yyyyMm 201212}

;; Test Old Implementing Class
(addMonths {:otherField1 "One", :year 2013, :month 7} 2)
;; {:otherField1 "One", :year 2013, :month 9}
(addMonths {:otherField1 "One", :year 2012, :month 12} 1)
;; {:otherField1 "One", :year 2013, :month 1}
(addMonths {:otherField1 "One", :year 2013, :month 1} -1)
;; {:otherField1 "One", :year 2012, :month 12}

;; Test New Implementing Class
(addMonths {:otherField2 1.1, :yyyyMm 201307} 2)
;; {:otherField2 1.1, :yyyyMm 201309}
(addMonths {:otherField2 1.1, :yyyyMm 201212} 1)
;; {:otherField2 1.1, :yyyyMm 201301}
(addMonths {:otherField2 1.1, :yyyyMm 201301} -1)
;; {:otherField2 1.1, :yyyyMm 201212}
