import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyRecords {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Project1b";
        String username = "coms363";
        String password = "password";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            // Task 1: Change the name of the student with ssn = 746897816 to Scott
            String updateStudentNameSQL = "UPDATE students SET name = 'Scott' WHERE ssn = 746897816";
            statement.executeUpdate(updateStudentNameSQL);

            // Task 2: Change the major of the student with ssn = 746897816 to Computer Science, Master
            String updateStudentMajorSQL = "UPDATE major SET name = 'Computer Science', level = 'MS' " +
                    "WHERE snum = (SELECT snum FROM students WHERE ssn = 746897816)";
            statement.executeUpdate(updateStudentMajorSQL);

            // Task 3: Delete all registration records that were in “Spring2021”
            String deleteSpring2021RegistrationsSQL = "DELETE FROM register WHERE regtime = 'Spring2021'";
            statement.executeUpdate(deleteSpring2021RegistrationsSQL);

            System.out.println("Database operations completed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
