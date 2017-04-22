package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import sample.models.Stock;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public AnchorPane radioButtonPane;
    public AnchorPane manualEntryPane;
    public AnchorPane profilePane;
    public RadioButton listRadioButton;
    public RadioButton manualRadioButton;
    public ToggleGroup radioToggle;
    public ListView tickerSymbolListView;
    public TextField tickerSymbolEntryField;
    public TextField companyTitleField;
    public TextField marketCapField;
    public TextField ipoYearField;
    public TextField sectorField;
    public TextField industryField;
    public TextField websiteField;

    public void resetAll() {
        tickerSymbolListView.setDisable(true);
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

        AllStockFactory allStockFactory = new AllStockFactory();
        final HashMap<String, Stock> tickersMap = allStockFactory.build();

        PrintTicker printTicker = new PrintTicker();

        listRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetAll();
                tickerSymbolListView.setDisable(false);

                ObservableList<String> observableList = FXCollections.observableArrayList();

                for (Stock stock : tickersMap.values()) {
                    String symbol = stock.getSymbol();
                    observableList.add(symbol);
                }

                tickerSymbolListView.setItems(observableList);
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


                    String companyName = printTicker.getCompanyName(tickersMap, tickerSymbolEntryField.getText());
                    String marketCap = printTicker.getMarketCap(tickersMap);

                    companyTitleField.setText(companyName);
                    marketCapField.setText(marketCap);

//                    sectorField.setText(ReadThroughTickers.printCompanyMethod());
                }
            }
        });
    }
//
//    public void companyNameController(String companyName) {
//        companyTitleField.setText(companyName);
//    }

    public void printToTextFields(String companyName, String marketCap, String ipoYear, String sector, String industry, String link) {


    }
}