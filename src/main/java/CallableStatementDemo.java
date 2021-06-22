import java.sql.*;

public class CallableStatementDemo {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/MySQL?useUnicode=true&serverTimezone=UTC&useSSL=true";


    static final String USER = "Yhtyyar";
    static final String PASSWORD = "Ilyas-2009";

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        System.out.println("Registering JDBC driver...");
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Creating connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Creating callable statement...");

        try {

            String SQl = "{call jdbc_mysql_project.get_developer_name (?,Types.VARCHAR)}";
            callableStatement = connection.prepareCall(SQl);

            int developerID = 1;
            callableStatement.setInt(1,developerID);

            System.out.println("Executing procedure...");
            callableStatement.execute();

            String developerName = callableStatement.getString(2);
            System.out.println("Developer INFO");
            System.out.println("ID: " + developerID);
            System.out.println("Name: " + developerName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        System.out.println("Thank you");

    }
}
