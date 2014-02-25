;; Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0

;; New!

(defn ymToOld [ym]
      (let [yyyyMm (:yyyyMm ym)]
           { :year (quot yyyyMm 100),
             :month (rem yyyyMm 100) }))

;; New!
(defn ymToNew [ym] { :yyyyMm (+ (* (:year ym) 100) (:month ym)) })

;; New! start of this function, but rest is unchanged
(defn addMonths [ym, addedMonths]
      (if (contains? ym :yyyyMm)
          (-> (ymToOld ym)
              (addMonths addedMonths)
              ymToNew)
          ;; Unchanged
          (let [year (:year ym), newMonth (+ (:month ym) addedMonths)]
               (cond (> newMonth 12)
                        (let [m (- newMonth 1)]
                             { :year (+ year (quot m 12)),
                               :month (+ (rem m 12) 1) })
                     (< newMonth 1)
                        { :year (dec (+ year (quot newMonth 12))),
                          :month (+ 12 (rem newMonth 12)) }
                     :else { :year year, :month newMonth }))))

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
;; {:year 2013, :month 9}
(addMonths {:otherField1 "One", :year 2012, :month 12} 1)
;; {:year 2013, :month 1}
(addMonths {:otherField1 "One", :year 2013, :month 1} -1)
;; {:year 2012, :month 12}

;; Test New Implementing Class
(addMonths {:otherField2 1.1, :yyyyMm 201307} 2)
;; {:yyyyMm 201309}
(addMonths {:otherField2 1.1, :yyyyMm 201212} 1)
;; {:yyyyMm 201301}
(addMonths {:otherField2 1.1, :yyyyMm 201301} -1)
;; {:yyyyMm 201212}
