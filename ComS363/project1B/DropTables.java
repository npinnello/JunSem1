import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTables {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Project1b";
        String username = "coms363";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            // Drop tables if they exist
            statement.executeUpdate("DROP TABLE IF EXISTS register");
            statement.executeUpdate("DROP TABLE IF EXISTS major");
            statement.executeUpdate("DROP TABLE IF EXISTS minor");
            statement.executeUpdate("DROP TABLE IF EXISTS courses");
            statement.executeUpdate("DROP TABLE IF EXISTS degrees");
            statement.executeUpdate("DROP TABLE IF EXISTS students");
            statement.executeUpdate("DROP TABLE IF EXISTS departments");

            System.out.println("Tables dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
