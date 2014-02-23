// Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0
object RefactScala1 {
  trait YearMonthTrait {
    def year:Int
    def month:Int
  }

  case class YearMonth(override val year:Int,
                       override val month:Int) extends YearMonthTrait

  object YearMonth {
    def addMonths(ym:YearMonthTrait, addedMonths:Int):YearMonth = {
      val newMonth = ym.month + addedMonths
      if (newMonth > 12) {
        val m = newMonth - 1
        new YearMonth(ym.year + (m / 12), (m % 12) + 1)
      } else if (newMonth < 1) {
        val y = ym.year + (newMonth / 12) - 1
        val m = 12 + (newMonth % 12)
        new YearMonth(y, m)
      } else {
        new YearMonth(ym.year, newMonth)
      }
    }
  }  
  
  case class MonthlyA(otherField1:String,
                      override val year:Int,
                      override val month:Int) extends YearMonthTrait
  
  // Test Base
  YearMonth.addMonths(YearMonth(2013, 7), 2)
  // YearMonth(2013,9)
  YearMonth.addMonths(YearMonth(2012, 12), 1)
  // YearMonth(2013,1)
  YearMonth.addMonths(YearMonth(2013, 1), -1)
  // YearMonth(2012,12)
  
  // Test MonthlyA
  YearMonth.addMonths(MonthlyA("One", 2013, 7), 2)
  // YearMonth(2013,9)
  YearMonth.addMonths(MonthlyA("One", 2012, 12), 1)
  // YearMonth(2013,1)
  YearMonth.addMonths(MonthlyA("One", 2013, 1), -1)
  // YearMonth(2012,12)
  
} // end class RefactScala1
