import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Project1b";
        String username = "coms363";
        String password = "password"; 

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            // Create Students Table
            String createStudentsTableSQL = "CREATE TABLE students ("
                    + "snum INT UNIQUE,"
                    + "ssn INT PRIMARY KEY,"
                    + "name VARCHAR(10),"
                    + "gender VARCHAR(1),"
                    + "dob DATETIME,"
                    + "c_addr VARCHAR(20),"
                    + "c_phone VARCHAR(10),"
                    + "p_addr VARCHAR(20),"
                    + "p_phone VARCHAR(10)"
                    + ")";
            statement.executeUpdate(createStudentsTableSQL);

            // Create Departments Table
            String createDepartmentsTableSQL = "CREATE TABLE departments ("
                    + "code INT PRIMARY KEY,"
                    + "name VARCHAR(50) UNIQUE,"
                    + "phone VARCHAR(10),"
                    + "college VARCHAR(20)"
                    + ")";
            statement.executeUpdate(createDepartmentsTableSQL);

            // Create Degrees Table
            String createDegreesTableSQL = "CREATE TABLE degrees ("
                    + "name VARCHAR(50),"
                    + "level VARCHAR(5),"
                    + "department_code INT,"
                    + "PRIMARY KEY (name, level),"
                    + "FOREIGN KEY (department_code) REFERENCES departments(code)"
                    + ")";
            statement.executeUpdate(createDegreesTableSQL);

            // Create Courses Table
            String createCoursesTableSQL = "CREATE TABLE courses ("
                    + "number INT PRIMARY KEY,"
                    + "name VARCHAR(50),"
                    + "description VARCHAR(50),"
                    + "credithours INT,"
                    + "level VARCHAR(20),"
                    + "department_code INT,"
                    + "FOREIGN KEY (department_code) REFERENCES departments(code)"
                    + ")";
            statement.executeUpdate(createCoursesTableSQL);

            // Create Register Table
            String createRegisterTableSQL = "CREATE TABLE register ("
                    + "snum INT,"
                    + "course_number INT,"
                    + "regtime VARCHAR(20),"
                    + "grade INT,"
                    + "PRIMARY KEY (snum, course_number),"
                    + "FOREIGN KEY (snum) REFERENCES students(snum),"
                    + "FOREIGN KEY (course_number) REFERENCES courses(number)"
                    + ")";
            statement.executeUpdate(createRegisterTableSQL);

            // Create Major Table
            String createMajorTableSQL = "CREATE TABLE major ("
                    + "snum INT,"
                    + "name VARCHAR(50),"
                    + "level VARCHAR(5),"
                    + "PRIMARY KEY (snum, name, level),"
                    + "FOREIGN KEY (snum) REFERENCES students(snum),"
                    + "FOREIGN KEY (name, level) REFERENCES degrees(name, level)"
                    + ")";
            statement.executeUpdate(createMajorTableSQL);

            // Create Minor Table
            String createMinorTableSQL = "CREATE TABLE minor ("
                    + "snum INT,"
                    + "name VARCHAR(50),"
                    + "level VARCHAR(5),"
                    + "PRIMARY KEY (snum, name, level),"
                    + "FOREIGN KEY (snum) REFERENCES students(snum),"
                    + "FOREIGN KEY (name, level) REFERENCES degrees(name, level)"
                    + ")";
            statement.executeUpdate(createMinorTableSQL);

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
