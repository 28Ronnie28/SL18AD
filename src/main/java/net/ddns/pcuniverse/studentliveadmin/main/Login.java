package net.ddns.pcuniverse.studentliveadmin.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    private ConnectionHandler connectionHandler;
    Boolean loggedIn = false;

    public Login(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("StudentLive Admin");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("SLLogo.png")));

        //Setup login pane
        ImageView loginLogoImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("SLLogo.png")));
        loginLogoImageView.setFitHeight(150);
        loginLogoImageView.setFitWidth(150);
        TextField usernameTextField = new TextField("admin");
        usernameTextField.setPromptText("Username");
        usernameTextField.getStyleClass().add("login-fields");
        PasswordField passwordField = new PasswordField();
        passwordField.setText("admin");
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("login-fields");
        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);
        loginButton.getStyleClass().add("login-button");
        loginButton.setOnAction(e -> {
            if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                if (connectionHandler.login(usernameTextField.getText(), passwordField.getText())) {
                    loggedIn = true;
                    stage.close();
                } else {
                    UserNotification.showErrorMessage("Login failed", "Incorrect login details entered");
                }
            } else {
                UserNotification.showErrorMessage("Invalid inputs", "Invalid username or password entered");
            }
        });
        VBox loginPane = new VBox(loginLogoImageView, usernameTextField, passwordField, loginButton);
        loginPane.setPadding(new Insets(25));
        loginPane.setSpacing(25);
        loginPane.setAlignment(Pos.CENTER);

        //Setup scene
        Scene scene = new Scene(loginPane, 400, 400);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("StudentLiveStyle.css").toExternalForm());

        //Select and show scene
        stage.setScene(scene);
        stage.showAndWait();
    }
}
