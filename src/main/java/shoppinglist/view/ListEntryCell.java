package shoppinglist.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import shoppinglist.data.ListEntry;

import java.io.IOException;

public class ListEntryCell extends ListCell<ListEntry> {

    @FXML
    private Label tfItem;

    @FXML
    private Label tfAmount;

    @FXML
    private AnchorPane listEntryPane;

    private FXMLLoader loader;

    @Override
    protected void updateItem(ListEntry entry, boolean empty) {
        super.updateItem(entry, empty);

        if (empty || entry == null) {
            setGraphic(null);
        } else {
            setUpLoader();
            setFieldValues(entry);
            setGraphic(listEntryPane);
        }
    }

    private void setFieldValues(ListEntry entry) {
        String itemText = String.valueOf(entry.getItem().getName());
        tfItem.setText(itemText);

        String amountText = String.valueOf(entry.getAmount());
        tfAmount.setText(amountText);
    }

    private void setUpLoader() {
        if (loader != null) {
            return;
        }

        try {
            loader = new FXMLLoader(getClass().getResource("ListEntryCell.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

