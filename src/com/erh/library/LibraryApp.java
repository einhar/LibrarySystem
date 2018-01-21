package com.erh.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibraryApp extends Application {

    private BookDAO buildBookDAO() {
        return new ApacheDerbyBookDAO();
    }

    private Library buildModel() {
        return new Library(buildBookDAO());
    }

    private Controller buildController(Stage stage) {
        return new Controller(buildModel(), stage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));
        loader.setControllerFactory(t -> buildController(primaryStage));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);


        /*
        BookDAO bookDAO = new ApacheDerbyBookDAO();
        bookDAO.setup();
        Library model = new Library(bookDAO);
        model.addNewBook("TestName1", "John Smith_Jack Seith_Jane Smith", 2010);
        model.addNewBook("TestName1", "John Smith_Jack Seith_Jane Smith", 2010);
        model.addNewBook("TestName1", "John Smith_Jack Seith_Jane Smith", 2010);
        model.addNewBook("TestName1", "John Smith_Jack Seith_Jane Smith", 2010);

        model.addNewBook("TestName2", "John Smith_Jane Smith", 2013);
        model.addNewBook("TestName2", "John Smith_Jane Smith", 2013);
        model.addNewBook("TestName2", "John Smith_Jane Smith", 2013);
        model.addNewBook("TestName2", "John Smith_Jane Smith", 2013);

        model.addNewBook("Name3", "Jane Smith", 2015);
        model.addNewBook("Name3", "Jane Smith", 2015);
        model.addNewBook("Name3", "Jane Smith", 2015);

        bookDAO.close();
        System.exit(0);
        */
    }
}
