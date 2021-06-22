import java.sql.*;

public  class ResultSetNavigationDemo {

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

            System.out.println("Moving cursor to the last position...");
            resultSet.last();

            System.out.println("Getting record...");
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            String specialty = resultSet.getString("SPECIALTY");
            int salary = resultSet.getInt("SALARY");

            System.out.println("Last record in result set:");
            System.out.println("ID: " +id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n====================================================\n");

            System.out.println("Moving cursor to previous row...");
            resultSet.previous();

            System.out.println("Getting record...");
            id = resultSet.getInt("ID");
            name = resultSet.getString("NAME");
            specialty = resultSet.getString("SPECIALTY");
            salary = resultSet.getInt("SALARY");

            System.out.println("Previous record: ");
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n====================================================\n");

            System.out.println("Moving cursor to the first row...");
            resultSet.first();

            System.out.println("Getting record...");
            id = resultSet.getInt("ID");
            name = resultSet.getString("NAME");
            specialty = resultSet.getString("SPECIALTY");
            salary = resultSet.getInt("SALARY");

            System.out.println("First record...");
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n===========================================\n");

            System.out.println("Adding record...");
            SQL = "INSERT INTO jdbc_mysql_project.developers VALUES (5, 'Mike', 'PHP',1500)";
            statement.executeUpdate(SQL);

            SQL = "SELECT * FROM jdbc_mysql_project.developers";
            resultSet = statement.executeQuery(SQL);
            resultSet.last();

            System.out.println("Getting record...");
            id = resultSet.getInt("ID");
            name = resultSet.getString("NAME");
            specialty = resultSet.getString("SPECIALTY");
            salary = resultSet.getInt("SALARY");

            System.out.println("Last record...");
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n===========================================\n");

            System.out.println("Full list of records:");
            SQL = "SELECT * FROM jdbc_mysql_project.developers";
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                id = resultSet.getInt("ID");
                name = resultSet.getString("NAME");
                specialty = resultSet.getString("SPECIALTY");
                salary = resultSet.getInt("SALARY");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: $" + salary);
                System.out.println("\n===========================================\n");
            }

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
