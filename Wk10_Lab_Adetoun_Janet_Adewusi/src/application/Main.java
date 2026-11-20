package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        UserDao userDao = new UserDao();

        primaryStage.setTitle("Employment Application");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label formTitle = new Label("Employment Application");
        formTitle.setMaxWidth(Double.MAX_VALUE);
        formTitle.setAlignment(Pos.CENTER);
        formTitle.getStyleClass().add("form-title");

        grid.add(formTitle, 0, 0, 4, 1);

        Label personalLabel = new Label("Personal Information");
        personalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        personalLabel.getStyleClass().add("section-title");
        grid.add(personalLabel, 0, 1, 4, 1);

        Label fullNameLabel = new Label("Full Name:");
        TextField fullNameTextField = new TextField();
        grid.add(fullNameLabel, 0, 2);
        grid.add(fullNameTextField, 1, 2, 3, 1);

        Label addressLabel = new Label("Current Address:");
        TextField addressTextField = new TextField();
        grid.add(addressLabel, 0, 3);
        grid.add(addressTextField, 1, 3, 3, 1);

        Label contactLabel = new Label("Contact Number:");
        TextField contactTextField = new TextField();
        grid.add(contactLabel, 0, 4);
        grid.add(contactTextField, 1, 4);

        Label emailLabel = new Label("Email Address:");
        TextField emailTextField = new TextField();
        grid.add(emailLabel, 2, 4);
        grid.add(emailTextField, 3, 4);

        Label educationLabel = new Label("Highest Education:");
        TextField educationTextField = new TextField();
        grid.add(educationLabel, 0, 5);
        grid.add(educationTextField, 1, 5);

        Label genderLabel = new Label("Gender:");
        RadioButton maleRadioButton = new RadioButton("Male");
        RadioButton femaleRadioButton = new RadioButton("Female");
        RadioButton otherRadioButton = new RadioButton("Other");

        ToggleGroup genderGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(genderGroup);
        femaleRadioButton.setToggleGroup(genderGroup);
        otherRadioButton.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(
                8, maleRadioButton, femaleRadioButton, otherRadioButton);
        grid.add(genderLabel, 2, 5);
        grid.add(genderBox, 3, 5);

        Label eligibilityLabel = new Label("Employment Eligibility");
        eligibilityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        eligibilityLabel.getStyleClass().add("section-title");
        grid.add(eligibilityLabel, 0, 6, 4, 1);

        Label dateLabel = new Label("Date Available:");
        DatePicker datePicker = new DatePicker();
        grid.add(dateLabel, 0, 7);
        grid.add(datePicker, 1, 7);

        Label positionLabel = new Label("Desired Position:");
        TextField positionTextField = new TextField();
        grid.add(positionLabel, 2, 7);
        grid.add(positionTextField, 3, 7);

        Label salaryLabel = new Label("Desired Salary:");
        TextField salaryTextField = new TextField();
        grid.add(salaryLabel, 0, 8);
        grid.add(salaryTextField, 1, 8, 3, 1);

        Label authorizedLabel =
                new Label("Legally authorized to work here?");
        RadioButton authorizedYesRadioButton = new RadioButton("Yes");
        RadioButton authorizedNoRadioButton = new RadioButton("No");

        ToggleGroup authorizedGroup = new ToggleGroup();
        authorizedYesRadioButton.setToggleGroup(authorizedGroup);
        authorizedNoRadioButton.setToggleGroup(authorizedGroup);

        HBox authorizedBox =
                new HBox(10, authorizedYesRadioButton, authorizedNoRadioButton);
        grid.add(authorizedLabel, 0, 9, 2, 1);
        grid.add(authorizedBox, 2, 9, 2, 1);

        Label relativesLabel =
                new Label("Relatives work for the company?");
        RadioButton relativesYesRadioButton = new RadioButton("Yes");
        RadioButton relativesNoRadioButton = new RadioButton("No");

        ToggleGroup relativesGroup = new ToggleGroup();
        relativesYesRadioButton.setToggleGroup(relativesGroup);
        relativesNoRadioButton.setToggleGroup(relativesGroup);

        HBox relativesBox =
                new HBox(10, relativesYesRadioButton, relativesNoRadioButton);
        grid.add(relativesLabel, 0, 10, 2, 1);
        grid.add(relativesBox, 2, 10, 2, 1);

        Label explanationLabel = new Label("If yes, please explain:");
        TextArea explanationTextArea = new TextArea();
        explanationTextArea.setPrefRowCount(2);
        explanationTextArea.setWrapText(true);
        grid.add(explanationLabel, 0, 11);
        grid.add(explanationTextArea, 1, 11, 3, 1);

        Label signatureLabel = new Label("Applicant Signature:");
        TextField signatureTextField = new TextField();
        grid.add(signatureLabel, 0, 12);
        grid.add(signatureTextField, 1, 12, 3, 1);

        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("submit-button");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().add(submitButton);
        grid.add(buttonBox, 1, 13, 3, 1);

        submitButton.setOnAction(actionEvent -> {
            String fullName = fullNameTextField.getText().trim();
            String address = addressTextField.getText().trim();
            String contactNumber = contactTextField.getText().trim();
            String emailAddress = emailTextField.getText().trim();
            String highestEducation = educationTextField.getText().trim();
            String desiredPosition = positionTextField.getText().trim();
            String desiredSalary = salaryTextField.getText().trim();
            String applicantSignature = signatureTextField.getText().trim();

            if (StringPool.BLANK.equals(fullName)
                    || StringPool.BLANK.equals(address)
                    || StringPool.BLANK.equals(contactNumber)
                    || StringPool.BLANK.equals(emailAddress)
                    || StringPool.BLANK.equals(highestEducation)
                    || StringPool.BLANK.equals(desiredPosition)
                    || StringPool.BLANK.equals(desiredSalary)
                    || StringPool.BLANK.equals(applicantSignature)
                    || datePicker.getValue() == null
                    || genderGroup.getSelectedToggle() == null
                    || authorizedGroup.getSelectedToggle() == null
                    || relativesGroup.getSelectedToggle() == null) {

                showAlert("Error", "Please complete all required fields.",
                        AlertType.ERROR);
                return;
            }

            EmploymentForm form = new EmploymentForm();
            form.setFullName(fullName);
            form.setCurrentAddress(address);
            form.setContactNumber(contactNumber);
            form.setEmailAddress(emailAddress);
            form.setHighestEducation(highestEducation);
            form.setGender(getSelectedText(genderGroup));
            form.setDateAvailable(datePicker.getValue().toString());
            form.setDesiredPosition(desiredPosition);
            form.setDesiredSalary(desiredSalary);
            form.setLegallyAuthorized(getSelectedText(authorizedGroup));
            form.setHasRelatives(getSelectedText(relativesGroup));
            form.setExplanation(explanationTextArea.getText().trim());
            form.setApplicantSignature(applicantSignature);

            try {
                int applicationId = userDao.saveEmploymentForm(form);

                if (applicationId > 0) {
                    showAlert("Submit", "Application submitted successfully.",
                            AlertType.INFORMATION);
                } else {
                    showAlert("Error", "The application could not be saved.",
                            AlertType.ERROR);
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
                showAlert("Database Error", exception.getMessage(),
                        AlertType.ERROR);
            }
        });

        Scene scene = new Scene(grid, 850, 720);
        scene.getStylesheets().add(
                getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getSelectedText(ToggleGroup group) {
        RadioButton selectedButton =
                (RadioButton) group.getSelectedToggle();
        return selectedButton.getText();
    }

    private void showAlert(
            String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}