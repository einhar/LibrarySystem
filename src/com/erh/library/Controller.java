package com.erh.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Controller {

    private Library model;

    @FXML
    private ChoiceBox<BookSearchType> choiceBox;

    @FXML
    private ListView<Book> listView;

    public Controller(Library model, Stage stage) {
        this.model = model;
        stage.setOnCloseRequest(e -> model.close());
    }

    public void initialize() {
        choiceBox.getItems().setAll(BookSearchType.values());
        choiceBox.getSelectionModel().selectFirst();
    }

    public void onSearch(ActionEvent event) {
        String param = ((TextField)event.getSource()).getText();
        System.out.println("TextField.value = " + param);

        listView.getItems().setAll(model.search(choiceBox.getValue(), param));
    }

    public void onLoan() {
        model.loanBook(listView.getSelectionModel().getSelectedItem().getUniqueID());
    }

    public void onReturn() {
        model.returnBook(listView.getSelectionModel().getSelectedItem().getUniqueID());
    }
}
