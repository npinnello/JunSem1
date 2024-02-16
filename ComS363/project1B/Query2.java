import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query2 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Project1b";
        String username = "coms363";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Define the SQL query to count female students who major or minor in LAS-managed degrees
            String sql = "SELECT COUNT(DISTINCT s.snum) AS female_las_students_count " +
                    "FROM students s " +
                    "INNER JOIN major m ON s.snum = m.snum " +
                    "INNER JOIN degrees d ON m.name = d.name AND m.level = d.level AND d.department_code IN (SELECT code FROM departments WHERE college = 'LAS') " +
                    "WHERE s.gender = 'F' " +
                    "UNION " +
                    "SELECT COUNT(DISTINCT s.snum) AS female_las_students_count " +
                    "FROM students s " +
                    "INNER JOIN minor mi ON s.snum = mi.snum " +
                    "INNER JOIN degrees d ON mi.name = d.name AND mi.level = d.level AND d.department_code IN (SELECT code FROM departments WHERE college = 'LAS') " +
                    "WHERE s.gender = 'F'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Print the results
                while (resultSet.next()) {
                    int femaleLASStudentsCount = resultSet.getInt("female_las_students_count");

                    System.out.println("Count of Female LAS Students: " + femaleLASStudentsCount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
