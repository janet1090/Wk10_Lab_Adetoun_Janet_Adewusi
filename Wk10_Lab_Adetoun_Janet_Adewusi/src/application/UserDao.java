package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    public int saveEmploymentForm(EmploymentForm form) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);

            String query = "INSERT INTO employment_application "
                    + "(full_name, current_address, contact_number, email_address, "
                    + "highest_education, gender, date_available, desired_position, "
                    + "desired_salary, legally_authorized, has_relatives, explanation, "
                    + "applicant_signature) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);

            int counter = 1;
            statement.setString(counter++, form.getFullName());
            statement.setString(counter++, form.getCurrentAddress());
            statement.setString(counter++, form.getContactNumber());
            statement.setString(counter++, form.getEmailAddress());
            statement.setString(counter++, form.getHighestEducation());
            statement.setString(counter++, form.getGender());
            statement.setString(counter++, form.getDateAvailable());
            statement.setString(counter++, form.getDesiredPosition());
            statement.setString(counter++, form.getDesiredSalary());
            statement.setString(counter++, form.getLegallyAuthorized());
            statement.setString(counter++, form.getHasRelatives());
            statement.setString(counter++, form.getExplanation());
            statement.setString(counter++, form.getApplicantSignature());

            statement.executeUpdate();
            connection.commit();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException exception) {
            if (connection != null) {
                connection.rollback();
            }
            throw exception;

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return 0;
    }
}