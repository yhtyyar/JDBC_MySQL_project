import java.sql.*;

public class DevelopersJdbcDemo {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/MySQL?useUnicode=true&serverTimezone=UTC&useSSL=true";


    static  final String USER = "Yhtyyar";
    static final String PASSWORD = "Ilyas-2009";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int [] two = new int[2];

        for (int i = 0; i < 2; i++) {
            two[i]  = i;

        }

        System.out.println(two);





        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering JDBC driver...");

        Class.forName(JDBC_DRIVER);

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String sql;
        sql = "SELECT *  FROM jdbc_mysql_project.developers;";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\nDevelopers:");

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            String specialty = resultSet.getString("SPECIALTY");
            int salary = resultSet.getInt("SALARY");


            System.out.println("\n===================================\n");

            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
        }

        System.out.println("Closing connection and realising resources...");
        resultSet.close();
        statement.close();
        connection.close();

    }
}
