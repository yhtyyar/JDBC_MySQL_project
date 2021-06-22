import java.sql.*;

public class DataTypesConversionDemo {

    public static void main(String[] args) {

        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();

        System.out.println("Current date and time (Java): " + javaDate.toString());
        System.out.println("Current time (Java): " + javaDate.getTime());

        Timestamp sqlTimeStamp = new Timestamp(javaTime);
        Date sqlDate = new Date(javaTime);
        Time sqlTime = new Time(javaTime);

        System.out.println("\n====================================================\n");
        System.out.println("Current timeStamp (SQL): " + sqlTimeStamp.toString());
        System.out.println("Current date (SQL): " + sqlDate.toString());
        System.out.println("Current time (SQL): " + sqlTime.toString());
    }
}
