import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query1 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Project1b";
        String username = "coms363";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Define the SQL query to calculate average grades for courses
            String sql = "SELECT c.number AS course_number, c.name AS course_name, AVG(r.grade) AS average_grade " +
                    "FROM courses c " +
                    "INNER JOIN register r ON c.number = r.course_number " +
                    "GROUP BY c.number, c.name";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Print the results
                while (resultSet.next()) {
                    int courseNumber = resultSet.getInt("course_number");
                    String courseName = resultSet.getString("course_name");
                    double averageGrade = resultSet.getDouble("average_grade");

                    System.out.println("Course Number: " + courseNumber);
                    System.out.println("Course Name: " + courseName);
                    System.out.println("Average Grade: " + averageGrade);
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
