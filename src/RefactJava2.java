// Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0
public class RefactJava2 {

    public interface YearMonthInterface {
        // Unchanged
        public int getYear();
        // Unchanged
        public int getMonth();
        // New! @return yyyyMm or YearMonth.of(year, month).getYyyyMm()
        public int getYyyyMm();
    }

    public static final class YearMonth implements YearMonthInterface {
        // Unchanged
        private final int year;
        // Unchanged
        private final int month;
        
        // Unchanged
        private YearMonth(int y, int m) { year = y; month = m; }

        // Unchanged
        public static YearMonth of(int y, int m) {
          if (m > 12) {
              // convert to zero-based months for math
              m--;
              // Carry any extra months over to the year
              y = y + (m / 12);
              // Adjust month to be within one year
              m = m % 12;
              // convert back to one-based months
              m++;
          } else if (m < 1) {
              // Carry any extra months over to the year, but the first year
              // in this case is still year-1
              y = y + (m / 12) - 1;
              // Adjust negative month to be within one year.
              // To get the positive month, subtract it from 12
              m = 12 + (m % 12);
          }
          return new YearMonth(y, m);
        }

        // New!
        public static YearMonth of(int YyyyMm) {
            return new YearMonth(YyyyMm / 100, YyyyMm % 100);
        }

        // New!
        @Override
        public int getYyyyMm() {
            return (year * 100) + month;
        }

        // Unchanged
        @Override
        public int getYear() { return year; }
        
        // Unchanged
        @Override
        public int getMonth() { return month; }
        
        public static YearMonth addMonths(YearMonthInterface ym,
                                          int addedMonths) {
            return of(ym.getYear(), ym.getMonth() + addedMonths);
        }
        
        // Unchanged
        @Override
        public String toString() {
            return new StringBuilder().append(year).append("-")
                                      .append(month).toString();
        }
    }

    public static class MonthlyA implements YearMonthInterface {
        // Unchanged
        private final  String otherField1;
        // Unchanged
        private final int year;
        // Unchanged
        private final int month;

        // Unchanged
        private MonthlyA(String s, int y, int m) {
            otherField1 = s; year = y; month = m;
        }

        // Unchanged
        public static MonthlyA of(String s, int y, int m) {
            return new MonthlyA(s, y, m);
        }

        // Unchanged
        public String getOtherField1() { return otherField1; }

        // Unchanged
        @Override
        public int getYear() { return year; }

        // Unchanged
        @Override
        public int getMonth() { return month; }

        // New!
        @Override
        public int getYyyyMm() {
            return YearMonth.of(year, month).getYyyyMm();
        }
    } // end of class MonthlyA

    // New!
    // In Java, we have to manually add support for the old data format to the new class.
    public static class MonthlyB implements YearMonthInterface {
        private final double otherField2;
        private final int yyyyMm;

        private MonthlyB(double d, int yyM) {
            otherField2 = d; yyyyMm = yyM;
        }

        public static MonthlyB of(double d, int yyM) {
            return new MonthlyB(d, yyM);
        }

        public double getOtherField2() { return otherField2; }

        @Override
        public int getYear() { return yyyyMm / 100; }

        @Override
        public int getMonth() { return (yyyyMm % 100); }

        @Override
        public int getYyyyMm() { return yyyyMm; }
    } // end class MonthlyB


    // Helper method for brevity
    public static void print(Object o) { System.out.println(String.valueOf(o)); }

    public static void main(String... args) {
         // Test original
       print(YearMonth.addMonths(YearMonth.of(201307), 2));
        // 2013-9
        print(YearMonth.addMonths(YearMonth.of(201212), 1));
        // 2013-1
        print(YearMonth.addMonths(YearMonth.of(201301), -1));
        // 2012-12
        
        // Test MonthlyA
        print(YearMonth.addMonths(MonthlyA.of("One", 2013, 7), 2));
        // 2013-9
        print(YearMonth.addMonths(MonthlyA.of("One", 2012, 12), 1));
        // 2013-1
        print(YearMonth.addMonths(MonthlyA.of("One", 2013, 1), -1));
        // 2012-12
        
        // Test MonthlyB
        print(YearMonth.addMonths(MonthlyB.of(1.1, 201307), 2));
        // 2013-9
        print(YearMonth.addMonths(MonthlyB.of(1.1, 201212), 1));
        // 2013-1
        print(YearMonth.addMonths(MonthlyB.of(1.1, 201301), -1));
        // 2012-12
    } // end main()
} // end class RefactJava2
