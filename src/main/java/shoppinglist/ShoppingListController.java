package shoppinglist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingListController implements Initializable {

    @FXML
    private ListView<ListEntry> listView;
    @FXML
    private TextField tfItem;
    @FXML
    private TextField tfAmount;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnRemove;

    private ObservableList<ListEntry> shoppingList;
    private ListEntry selectedEntry;

    public ShoppingListController() {
        this.shoppingList = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpListView();
        selectedEntry = null;

    }

    private void onItemSelected(ListEntry listEntry) {
        if(listEntry != null) {
            importEntry(listEntry);
            selectedEntry = listEntry;
        }
    }

    public void onSave() {
        addEntryToList();
        resetInputFields();
        resetSelection();
    }

    public void onRemove() {
        removeSelectedItem();
        validateSaveButtonState();
    }

    public void onEnteredItem() {
        validateSaveButtonState();
    }

    public void onEnteredAmount() {
        validateSaveButtonState();
        validateAmount();
    }

    private void setUpListView() {
        listView.setItems(shoppingList);
        listView.setCellFactory(itemListView -> new ListEntryCell());
        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> onItemSelected(newValue));
    }

    private void validateSaveButtonState() {
        boolean enable = false;
        if (isInputValueMissing()) {
            enable = true;
        } else if(!tfAmount.getText().isEmpty()) {
            int currentAmount = Integer.valueOf(tfAmount.getText());
            String itemName = tfItem.getText();
            if(hasListItemWithName(itemName) && isItemAmountEqualTo(currentAmount)) {
                enable = true;
            }
        }
        btnSave.setDisable(enable);
    }

    private boolean isItemAmountEqualTo(int amount) {
        return selectedEntry.getAmount() == amount;
    }


    private void validateAmount() {
        if(!isStringInt(tfAmount.getText())) {
            showErrorAlert();
            resetTfAmount();
        }
    }

    private boolean isInputValueMissing() {
        String itemName = tfItem.getText();
        String amount = tfAmount.getText();
        return amount.isEmpty() || itemName.isEmpty();
    }

    private void removeSelectedItem() {
        int indexToRemove = listView.getSelectionModel().getSelectedIndex();
        resetSelection();
        btnRemove.setDisable(true);
        shoppingList.remove(indexToRemove);
    }

    private void resetSelection() {
        listView.getSelectionModel().select(null);
        selectedEntry = null;
    }

    private void importEntry(ListEntry listEntry) {
        tfAmount.setText(String.valueOf(listEntry.getAmount()));
        tfItem.setText(listEntry.getItem().getName());
        btnRemove.setDisable(false);
    }

    private void resetInputFields() {
        btnSave.setDisable(true);
        resetTfAmount();
        tfItem.setText("");
    }


    private void resetTfAmount() {
        tfAmount.setText("");
    }

    private void showErrorAlert() {
        String headerText = "Amount must be a whole number!";
        String contentText = "Please enter a valid value for amount";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void addEntryToList() {
        int amount = Integer.valueOf(tfAmount.getText());
        String itemName = tfItem.getText();
        if(hasListItemWithName(itemName)) {
            removeSelectedItem();
        }
        Item item = new Item(tfItem.getText());
        ListEntry listEntry = new ListEntry(item, amount);
        shoppingList.add(listEntry);
    }

    private boolean hasListItemWithName(String itemName) {
        for (ListEntry el : shoppingList) {
            if (el.getItem().getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isStringInt(String string) {
        return string.matches("[0-9]*");
    }
}