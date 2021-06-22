import javafx.beans.property.ReadOnlySetProperty;

import java.sql.*;

public class PreparedStatementDemo {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/MySQL?useUnicode=true&serverTimezone=UTC&useSSL=true";


    static  final String USER = "Yhtyyar";
    static final String PASSWORD = "Ilyas-2009";

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        System.out.println("Registering JDBC driver...");

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Creating connection...");

        try {
            connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {

            String SQL = "SELECT * FROM jdbc_mysql_project.developers;";
            preparedStatement = connection.prepareStatement(SQL);

            System.out.println("Initial developers table content:");
            ResultSet resultSet = preparedStatement.executeQuery(SQL);

            while (resultSet.next()) {

                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String specialty = resultSet.getString("SPECIALTY");
                int salary = resultSet.getInt("SALARY");

                System.out.println("ID:" + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: " + salary);

                System.out.println("\n=================================================\n");
            }

            SQL = "UPDATE jdbc_mysql_project.developers  SET SALARY = 3000 WHERE SPECIALTY = 'java';";
            System.out.println("Creating statement...");
            System.out.println("Executing SQL query...");

            preparedStatement = connection.prepareStatement(SQL);

            System.out.println("Rows impacted: " + preparedStatement.executeUpdate());

            System.out.println("Final developers table content: ");
            SQL =  "SELECT * FROM jdbc_mysql_project.developers;";
            resultSet = preparedStatement.executeQuery(SQL);

            while (resultSet.next()) {

                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String specialty = resultSet.getString("SPECIALTY");
                int salary = resultSet.getInt("SALARY");

                System.out.println("ID:" + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: " + salary);

                System.out.println("\n=================================================\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        System.out.println("Thank you.");

    }
}
