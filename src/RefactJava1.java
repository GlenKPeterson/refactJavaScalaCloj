// Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0
public class RefactJava1 {

    public interface YearMonthInterface {
        public int getYear();
        public int getMonth();
    }

    public static final class YearMonth implements YearMonthInterface {
        private final int year;
        private final int month;
        
        private YearMonth(int y, int m) { year = y; month = m; }

        public static YearMonth of(int y, int m) {
          if (m > 12) {
              m--;
              y = y + (m / 12);
              m = (m % 12) + 1;
          } else if (m < 1) {
              y = y + (m / 12) - 1;
              m = 12 + (m % 12);
          }
          return new YearMonth(y, m);
        }
        
        @Override
        public int getYear() { return year; }
        
        @Override
        public int getMonth() { return month; }
        
        public static YearMonth addMonths(YearMonthInterface ym,
                                          int addedMonths) {
            return of(ym.getYear(), ym.getMonth() + addedMonths);
        }
        
        @Override
        public String toString() {
            return new StringBuilder().append(year).append("-")
                                      .append(month).toString();
        }
    }

    public static class MonthlyA implements YearMonthInterface {
        private final  String otherField1;
        private final int year;
        private final int month;

        private MonthlyA(String s, int y, int m) {
            otherField1 = s; year = y; month = m;
        }

        public static MonthlyA of(String s, int y, int m) {
            return new MonthlyA(s, y, m);
        }

        public String getOtherField1() { return otherField1; }

        @Override
        public int getYear() { return year; }

        @Override
        public int getMonth() { return month; }
    }

    // Helper method for brevity
    public static void print(Object o) { System.out.println(String.valueOf(o)); }

    public static void main(String... args) {
         // Test original
        print(YearMonth.addMonths(YearMonth.of(2013, 7), 2));
        // 2013-9
        print(YearMonth.addMonths(YearMonth.of(2012, 12), 1));
        // 2013-1
        print(YearMonth.addMonths(YearMonth.of(2013, 1), -1));
        // 2012-12
        
        // Test new 
        print(YearMonth.addMonths(MonthlyA.of("One", 2013, 7), 2));
        // 2013-9
        print(YearMonth.addMonths(MonthlyA.of("One", 2012, 12), 1));
        // 2013-1
        print(YearMonth.addMonths(MonthlyA.of("One", 2013, 1), -1));
        // 2012-12
    }
}
