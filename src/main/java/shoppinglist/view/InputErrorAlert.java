package shoppinglist.view;

import javafx.scene.control.Alert;

class InputErrorAlert extends Alert {

    private static final String TITLE_TEXT = "Error";
    private static final String HEADER_TEXT = "Amount must be a whole number!";
    private static final String CONTENT_TEXT = "Please enter a valid amount value";

    InputErrorAlert() {
        super(Alert.AlertType.ERROR);
        this.setTitle(TITLE_TEXT);
        this.setHeaderText(HEADER_TEXT);
        this.setContentText(CONTENT_TEXT);
    }
}
