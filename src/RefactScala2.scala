// Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0
object RefactScala2 {
  trait YearMonthTrait {
    def year:Int
    def month:Int
    // New!
    def getYyyyMm:Int = (year * 100) + month
  }

  // Unchanged
  case class YearMonth(override val year:Int,
                       override val month:Int) extends YearMonthTrait

  object YearMonth {
    // Unchanged
    def addMonths(ym:YearMonthTrait, addedMonths:Int):YearMonth = {
      val newMonth = ym.month + addedMonths
      if (newMonth > 12) {
        // convert to zero-based months for math
        val m = newMonth - 1
        // Carry any extra months over to the year
        new YearMonth(ym.year + (m / 12), (m % 12) + 1)
      } else if (newMonth < 1) {
        // Carry any extra months over to the year, but the
        // first year in this case is still year-1
        val y = ym.year + (newMonth / 12) - 1
        // Adjust negative month to be within one year.
        // To get the positive month, subtract it from 12
        val m = 12 + (newMonth % 12)
        new YearMonth(y, m)
      } else {
        new YearMonth(ym.year, newMonth)
      }
    }
    
    // New!
    // Add yyyyMm factory method to the YearMonth companion object
    // This is like the extra "of" method we just added to the Java version.
    def apply(yyyyMm:Int) = new YearMonth((yyyyMm / 100), (yyyyMm % 100))
  }  
  
  // Unchanged
  case class MonthlyA(otherField1:String,
                      override val year:Int,
                      override val month:Int) extends YearMonthTrait
  
  // New!
  trait YearMonthNew extends YearMonthTrait {
    def yyyyMm:Int
    def year:Int = yyyyMm / 100
    def month:Int = (yyyyMm % 100)
  }

  // New!
  case class MonthlyB(otherField1:Double,
                      override val yyyyMm:Int) extends YearMonthNew
  
  // Test Base
  YearMonth.addMonths(YearMonth(201307), 2)
  // YearMonth(2013,9)
  YearMonth.addMonths(YearMonth(2012012), 1)
  // YearMonth(2013,1)
  YearMonth.addMonths(YearMonth(201301), -1)
  // YearMonth(2012,12)
  
  // Test MonthlyA
  YearMonth.addMonths(MonthlyA("One", 201307), 2)
  // YearMonth(2013,9)
  YearMonth.addMonths(MonthlyA("One", 201212), 1)
  // YearMonth(2013,1)
  YearMonth.addMonths(MonthlyA("One", 201301), -1)
  // YearMonth(2012,12)

  // Scala
  YearMonth.addMonths(MonthlyB(1.1, 201307), 2)
  // YearMonth(2013,9)
  YearMonth.addMonths(MonthlyB(1.1, 201212), 1)
  // YearMonth(2013,1)
  YearMonth.addMonths(MonthlyB(1.1, 201301), -1)
  // YearMonth(2012,12)

} // end class RefactScala2
