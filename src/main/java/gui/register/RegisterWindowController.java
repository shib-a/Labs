package gui.register;

import gui.AlertUtility;
import gui.collections.CollectionsWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterWindowController {
    private ResourceBundle currentBundle;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label passwordAgainLabel;

    @FXML
    private TextField passwordAgainField;

    @FXML
    private Label welcomeRegLabel;

    private final List<Locale> supportedLocales = Arrays.asList(
            new Locale("is"),
            new Locale("ru"),
            new Locale("da"),
            new Locale("es","GT")
    );
    private int currentLocaleIndex;
    public void setLocale(int index) {
        this.currentLocaleIndex = index;
        AlertUtility.infoAlert(String.valueOf(currentLocaleIndex));
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
        updateUI();
    }


    @FXML
    public void initialize() {
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
        updateUI();
    }

    /**
     * Update LoginWindow UI
     */
    private void updateUI() {
        welcomeRegLabel.setText(currentBundle.getString("welcomeRegLabel"));
        usernameLabel.setText(currentBundle.getString("usernameLabel"));
        usernameField.setPromptText(currentBundle.getString("usernameField"));
        passwordLabel.setText(currentBundle.getString("passwordLabel"));
        passwordField.setPromptText(currentBundle.getString("passwordField"));
        passwordAgainLabel.setText(currentBundle.getString("passwordLabel"));
        passwordAgainField.setPromptText(currentBundle.getString("passwordField"));
        signUpButton.setText(currentBundle.getString("signUpButton"));



    }
    @FXML
    protected void onSignIiLabelClick() {

        //тут должно быть добавление юзера в табличку
        CollectionsWindow collectionsWindow = new CollectionsWindow(currentLocaleIndex);
        collectionsWindow.show();

    }
}
