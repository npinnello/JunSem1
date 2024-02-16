import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query3 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Project1b";
        String username = "coms363";
        String password = "password";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Define the SQL query to find degrees with more male students than female students
            String sql = "SELECT d.name AS degree_name " +
                    "FROM degrees d " +
                    "WHERE " +
                    "(SELECT COUNT(*) FROM major m INNER JOIN students s ON m.snum = s.snum WHERE m.name = d.name AND m.level = d.level AND s.gender = 'M') " +
                    ">" +
                    "(SELECT COUNT(*) FROM minor mi INNER JOIN students s ON mi.snum = s.snum WHERE mi.name = d.name AND mi.level = d.level AND s.gender = 'F')";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Print the results
                System.out.println("Degrees with More Male Students:");
                while (resultSet.next()) {
                    String degreeName = resultSet.getString("degree_name");
                    System.out.println(degreeName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
