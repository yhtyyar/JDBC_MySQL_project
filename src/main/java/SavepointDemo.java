import java.sql.*;

public class SavepointDemo {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/MySQL?useUnicode=true&serverTimezone=UTC&useSSL=true";


    static final String USER = "Yhtyyar";
    static final String PASSWORD = "Ilyas-2009";

    public static void main(String [] args) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;

        Class.forName(JDBC_DRIVER);

        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        connection.setAutoCommit(false);

        statement = connection.createStatement();

        String SQL = "SELECT * FROM jdbc_mysql_project.developers;";

        ResultSet resultSet = statement.executeQuery(SQL);

        System.out.println("Retrieving data from database...");
        System.out.println("\nDevelopers:");

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            String specialty = resultSet.getString("SPECIALTY");
            int salary = resultSet.getInt("SALARY");

            System.out.println("\n=================================================\n");
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
        }


        System.out.println("\n==================================================\n");
        System.out.println("Creating savepoint...");
        Savepoint savepointOne = connection.setSavepoint("SavePointOne");

        try {

            SQL = "INSERT INTO jdbc_mysql_project.developers VALUE (6, 'Jhon', 'C#', 2200), " +
                                                                  "(7, 'Ron', 'Ruby', 1900)";
            statement.executeUpdate(SQL);

            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException. Executing rollback to savepoint...");
            connection.rollback(savepointOne);
        }

        SQL = "SELECT * FROM jdbc_mysql_project.developers;";
        resultSet = statement.executeQuery(SQL);
        System.out.println("Retrieving data from database...");

        while (resultSet.next()) {

            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            String specialty = resultSet.getString("SPECIALTY");
            int salary = resultSet.getInt("SALARY");

            System.out.println("\n=================================================\n");
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);

            System.out.println("\n===================================================\n");
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        statement.close();
        connection.close();

    }
}
