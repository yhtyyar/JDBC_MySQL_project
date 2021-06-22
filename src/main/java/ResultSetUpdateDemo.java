import java.sql.*;

public class ResultSetUpdateDemo {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/MySQL?useUnicode=true&serverTimezone=UTC&useSSL=true";


    static final String USER = "Yhtyyar";
    static final String PASSWORD = "Ilyas-2009";

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Creating statement...");

        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            String SQL = "SELECT * FROM jdbc_mysql_project.developers;";
            ResultSet resultSet = statement.executeQuery(SQL);

            System.out.println("Initial list of records:");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String specialty = resultSet.getString("SPECIALTY");
                int salary = resultSet.getInt("SALARY");

                System.out.println("Last record in result set:");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: " + salary);
                System.out.println("\n===================================================\n");
            }

            System.out.println("Increasing all developer's salary (+ $1,000)...");
            resultSet.first();

            while (resultSet.next()) {
                int newSalary = resultSet.getInt("SALARY") + 1000;
                resultSet.updateInt("SALARY", newSalary);
                resultSet.updateRow();
            }

            resultSet.first();

            System.out.println("Final list of records: ");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String specialty = resultSet.getString("SPECIALTY");
                int salary = resultSet.getInt("SALARY");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: $" + salary);
                System.out.println("\n===========================================\n");
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

    }
}
