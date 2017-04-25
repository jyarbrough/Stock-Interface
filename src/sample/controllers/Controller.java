package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.models.stockFactories.*;
import sample.printHandlers.PrintProfile;
import sample.printHandlers.PrintStats;

import java.net.URL;
import java.util.*;

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

    @FXML
    TableView<StockHistoryRecord> stockDataTable = new TableView<>();


    public void resetAll(boolean shouldDisableSymbolEntryField) {
        tickerSymbolListView.setDisable(true);
        companyTitleField.setText("");
        marketCapField.setText("");
        ipoYearField.setText("");
        sectorField.setText("");
        industryField.setText("");
        websiteField.setText("");
        if (shouldDisableSymbolEntryField) {
            tickerSymbolEntryField.setDisable(true);
        }
        setProfileFieldsToFalse(true);
        stockDataTable.setItems(FXCollections.observableArrayList());
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {



        final PrintProfile printProfile = new PrintProfile();
        final StockHistoryFactory stockHistoryFactory = new StockHistoryFactory();

        PrintStats printStats = new PrintStats();
        AllStockFactory allStockFactory = new AllStockFactory();
        PopulateStockHistory populateStockHistory = new PopulateStockHistory();
        HashMap<String, Stock> tickersMap = allStockFactory.build();
        populateStockHistory.populateStockHistory(stockHistoryFactory, tickersMap);

        chooseFromListOption(tickersMap);
        intializeListView(tickersMap);
        manualEntryOption();

        initializeTickerSymbolEntry(tickersMap);
    }

    private void initializeTickerSymbolEntry(final HashMap<String, Stock> tickersMap) {
        tickerSymbolEntryField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent enterKeyPressed) {
                if (enterKeyPressed.getCode().equals(KeyCode.ENTER)) {
                    String searchSymbol = tickerSymbolEntryField.getText();

                    displayProfile(searchSymbol, tickersMap);
                }
            }
        });
    }

    private void displayProfile(String searchSymbol, HashMap<String, Stock> tickersMap) {
        resetAll(false);
        setProfileFieldsToFalse(false);
        setupTableColumns();


        Stock stock = tickersMap.get(searchSymbol);
        ArrayList<StockHistoryRecord> stockHistoryRecords = stock.getStockHistoryRecords();

        try {
            ObservableList<StockHistoryRecord> stockData = FXCollections.observableArrayList();
            stockData.addAll(stockHistoryRecords);
            stockDataTable.setItems(stockData);
        } catch (Exception e) {
//            e.printStackTrace();
        }

        companyTitleField.setText(stock.companyNameToString());
        marketCapField.setText(stock.marketCapToString());
        ipoYearField.setText(stock.ipoYearToString());
        sectorField.setText(stock.sectorToString());
        industryField.setText(stock.industryToString());
        websiteField.setText(stock.linkToString());
    }

    private void setupTableColumns() {
        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<StockHistoryRecord, String>("date"));

        TableColumn highColumn = new TableColumn("High");
        highColumn.setMinWidth(100);
        highColumn.setCellValueFactory(
                new PropertyValueFactory<StockHistoryRecord, String>("high"));

        TableColumn lowColumn = new TableColumn("Low");
        lowColumn.setMinWidth(100);
        lowColumn.setCellValueFactory(
                new PropertyValueFactory<StockHistoryRecord, String>("low"));

        TableColumn adjCloseColumn = new TableColumn("Closing Value");
        adjCloseColumn.setMinWidth(100);
        adjCloseColumn.setCellValueFactory(
                new PropertyValueFactory<StockHistoryRecord, String>("adjClose"));

        stockDataTable.getColumns().addAll(dateColumn, highColumn, lowColumn, adjCloseColumn);
    }

    private void setProfileFieldsToFalse(boolean value) {
        companyTitleField.setDisable(value);
        marketCapField.setDisable(value);
        ipoYearField.setDisable(value);
        sectorField.setDisable(value);
        industryField.setDisable(value);
        websiteField.setDisable(value);
        stockDataTable.setDisable(value);
    }

    private void manualEntryOption() {
        manualRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetAll(true);
                tickerSymbolEntryField.setDisable(false);
            }
        });
    }

    private void chooseFromListOption(final HashMap<String, Stock> tickersMap) {
        listRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetAll(true);
                tickerSymbolListView.setDisable(false);

                ObservableList<String> observableList = FXCollections.observableArrayList();

                for (Stock stock : tickersMap.values()) {
                    String symbol = stock.getSymbol();

                    observableList.add(symbol);
                }
                tickerSymbolListView.setItems(observableList);
            }
        });
    }

    private void intializeListView(final HashMap<String, Stock> tickersMap) {
        tickerSymbolListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                String searchSymbol = tickerSymbolListView.getSelectionModel().getSelectedItem().toString();

                displayProfile(searchSymbol, tickersMap);

            }
        });
    }
}