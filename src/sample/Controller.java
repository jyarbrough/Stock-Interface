package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public AnchorPane radioButtonPane;
    public AnchorPane manualEntryPane;
    public AnchorPane profilePane;

    public RadioButton listRadioButton;
    public RadioButton manualRadioButton;

    public ToggleGroup radioToggle;

    public ChoiceBox tickerSymbolChoiceBox;

    public TextField tickerSymbolEntryField;
    public TextField companyTitleField;
    public TextField marketCapField;
    public TextField ipoYearField;
    public TextField sectorField;
    public TextField industryField;
    public TextField websiteField;

    public void resetAll() {
        tickerSymbolChoiceBox.setDisable(true);
        tickerSymbolEntryField.setDisable(true);
        companyTitleField.setDisable(true);
        marketCapField.setDisable(true);
        ipoYearField.setDisable(true);
        sectorField.setDisable(true);
        industryField.setDisable(true);
        websiteField.setDisable(true);
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

//        assert manualRadioButton != null : "fx:id=\"manualRadioButton\" was not injected: check your FXML file 'simple.fxml'.";

        listRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetAll();
                tickerSymbolChoiceBox.setDisable(false);
                ObservableList<String> observableList = FXCollections.observableArrayList(
                        "joe"
                );

                tickerSymbolChoiceBox.setItems(observableList);
            }
        });

        manualRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetAll();
                tickerSymbolEntryField.setDisable(false);

            }
        });


        tickerSymbolEntryField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent enterKeyPressed) {
                if (enterKeyPressed.getCode().equals(KeyCode.ENTER)) {
                    companyTitleField.setDisable(false);
                    marketCapField.setDisable(false);
                    ipoYearField.setDisable(false);
                    sectorField.setDisable(false);
                    industryField.setDisable(false);
                    websiteField.setDisable(false);

//                    industryField.setText(PrintTicker.sendCompanyName(this.toString()));

                    try {
                        InsertFilePathAndMerge.insertFilePathAndMerge();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        ReadThroughTickers.readThroughTickers();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    sectorField.setText(ReadThroughTickers.printCompanyMethod());
                }
            }
        });
    }
//
//    public void companyNameController(String companyName) {
//        companyTitleField.setText(companyName);
//    }

}