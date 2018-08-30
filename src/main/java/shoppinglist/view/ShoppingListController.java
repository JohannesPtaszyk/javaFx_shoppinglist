package shoppinglist.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import shoppinglist.data.Item;
import shoppinglist.data.ListEntry;
import shoppinglist.data.ShoppingListInteractor;
import shoppinglist.util.StringUtil;

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
    @FXML
    private Button btnUp;
    @FXML
    private Button btnDown;

    private ObservableList<ListEntry> shoppingList;
    private ListEntry selectedEntry;
    private ShoppingListInteractor listInteractor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.shoppingList = FXCollections.observableArrayList();
        this.listInteractor = new ShoppingListInteractor(shoppingList);
        setUpListView();
        selectedEntry = null;

    }

    private void onItemSelected(ListEntry listEntry) {
        if(listEntry != null) {
            importEntry(listEntry);
            disableUpAndDownButton(false);
            selectedEntry = listEntry;
        }
    }

    public void onSave() {
        addEntryToList();
        resetInputFields();
        resetSelection();
        disableUpAndDownButton(true);
    }

    public void onRemove() {
        resetInputFields();
        removeSelectedItem();
        validateSaveButtonState();
        disableUpAndDownButton(true);
    }

    public void onUp() {
        listInteractor.moveEntryUp(selectedEntry);
        reselectEntryAfterMove("up");
    }

    public void onDown() {
        listInteractor.moveEntryDown(selectedEntry);
        reselectEntryAfterMove("down");
    }

    public void onEnteredItem() {
        if(isEnteredItemInList()) {
            selectEnteredItem();
        } else {
            validateSaveButtonState();
        }
    }

    public void onEnteredAmount() {
        validateAmount();
        validateSaveButtonState();

    }

    private void setUpListView() {
        listView.setItems(shoppingList);
        listView.setCellFactory(itemListView -> new ListEntryCell());
        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> onItemSelected(newValue));
    }

    private void addEntryToList() {
        int amount = Integer.valueOf(tfAmount.getText());
        String itemName = tfItem.getText();
        if(listInteractor.hasListEntryWithItemName(itemName)) {
            removeSelectedItem();
        }
        Item item = new Item(tfItem.getText());
        ListEntry listEntry = new ListEntry(item, amount);
        shoppingList.add(listEntry);
    }

    private void validateSaveButtonState() {
        boolean disable = false;
        String amount = tfAmount.getText();
        if (isInputValueMissing()) {
            disable = true;
        } else if(!amount.isEmpty()) {
            int currentAmount = Integer.valueOf(amount);
            String itemName = tfItem.getText();
            if(listInteractor.hasListEntryWithItemName(itemName)
                    && isItemAmountEqualTo(currentAmount)) {
                disable = true;
            }
        }
        btnSave.setDisable(disable);
    }

    private void validateAmount() {
        if(!StringUtil.isStringAnInteger(tfAmount.getText())) {
            showErrorAlert();
            resetTfAmount();
        }
    }

    private void disableUpAndDownButton(boolean disable) {
        btnUp.setDisable(disable);
        btnDown.setDisable(disable);
    }

    private boolean isItemAmountEqualTo(int amount) {
        return selectedEntry.getAmount() == amount;
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

    private void reselectEntryAfterMove(String direction) {
        listView.requestFocus();
        if(direction.equals("up")) {
            if(!listInteractor.isFirstListEntry(selectedEntry)) {
                listView.getSelectionModel().selectIndices(shoppingList.indexOf(selectedEntry) - 1);
            } else {
                listView.getSelectionModel().selectFirst();
            }
        } else if(direction.equals("down")) {
            if(!listInteractor.isLastListEntry(selectedEntry)) {
                listView.getSelectionModel().selectIndices(shoppingList.indexOf(selectedEntry) + 1);
            } else {
                listView.getSelectionModel().selectLast();
            }
        }
        listView.scrollTo(selectedEntry);
    }

    private void selectEnteredItem() {
        int index = listInteractor.getIndexOfEntryWithItemName(tfItem.getText());
        listView.getSelectionModel().select(index);
        listView.scrollTo(index);
        tfItem.positionCaret(tfItem.getText().length());
        resetTfAmount();
    }

    private boolean isEnteredItemInList() {
        return listInteractor.hasListEntryWithItemName(tfItem.getText());
    }

    private void importEntry(ListEntry listEntry) {
        tfAmount.setText(String.valueOf(listEntry.getAmount()));
        tfItem.setText(listEntry.getItem().getName());
        btnRemove.setDisable(false);
    }

    private void showErrorAlert() {
        InputErrorAlert alert = new InputErrorAlert();
        alert.show();
    }

    private void resetInputFields() {
        btnSave.setDisable(true);
        resetTfAmount();
        tfItem.setText("");
    }


    private void resetTfAmount() {
        tfAmount.setText("");
    }
}