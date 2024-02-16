import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query4 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Project1b";
        String username = "coms363";
        String password = "password";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Define the SQL query to find the major of the youngest student
            String sql = "SELECT m.name AS major_name " +
                    "FROM major m " +
                    "INNER JOIN students s ON m.snum = s.snum " +
                    "WHERE s.dob = (SELECT MIN(dob) FROM students)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Print the results
                System.out.println("Major of the Youngest Student:");
                while (resultSet.next()) {
                    String majorName = resultSet.getString("major_name");
                    System.out.println(majorName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
