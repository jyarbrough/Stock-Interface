package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.models.stockFactories.*;

import java.io.IOException;
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
    public Button graphViewButton;

    public TextField searchDateEntryField;
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
        graphViewButton.setDisable(true);
        searchDateEntryField.setText("");
        companyTitleField.setText("");
        marketCapField.setText("");
        ipoYearField.setText("");
        sectorField.setText("");
        industryField.setText("");
        websiteField.setText("");
        if (shouldDisableSymbolEntryField) {
            tickerSymbolEntryField.setDisable(true);
        }
        setFieldsToFalse(true);
        stockDataTable.setItems(FXCollections.observableArrayList());
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        final StockHistoryFactory stockHistoryFactory = new StockHistoryFactory();
        AllStockFactory allStockFactory = new AllStockFactory();
        PopulateStockHistory populateStockHistory = new PopulateStockHistory();
        HashMap<String, Stock> tickersMap = allStockFactory.build();
        populateStockHistory.populateStockHistory(stockHistoryFactory, tickersMap);

        initializeEventListeners(tickersMap);
    }

    private void initializeEventListeners(HashMap<String, Stock> tickersMap) {
        initializeList(tickersMap);
        intializeListRadioButton(tickersMap);
        initializeManualRadioButton();
        initializeGraphButton();
        initializeTickerSymbolEntry(tickersMap);
        initializeSearchField(tickersMap);
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

    private void initializeSearchField(final HashMap<String, Stock> tickersMap) {
        searchDateEntryField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent enterKeyPressed) {
                if (enterKeyPressed.getCode().equals(KeyCode.ENTER)) {
                    stockDataTable.setItems(FXCollections.observableArrayList());

                    String dateSearch = searchDateEntryField.getText();
                    String searchSymbol = tickerSymbolEntryField.getText();


                    Stock stock = tickersMap.get(searchSymbol);
                    ArrayList<StockHistoryRecord> stockHistoryRecords = stock.getStockHistoryRecords();

                    try {
                        ObservableList<StockHistoryRecord> stockData = FXCollections.observableArrayList();
                        for (StockHistoryRecord stockHistoryRecord : stockHistoryRecords) {
                            String date = stockHistoryRecord.getDate();

                            if (dateSearch.equals(date)) {
                                stockData.add(stockHistoryRecord);
                            }
                        }
                        stockDataTable.setItems(stockData);
                    } catch (Exception e) {
//            e.printStackTrace();
                    }
                }
            }
        });
    }


    private void displayProfile(String searchSymbol, HashMap<String, Stock> tickersMap) {
        resetAll(false);
        setFieldsToFalse(false);
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

    private void setFieldsToFalse(boolean value) {
        searchDateEntryField.setDisable(value);
        companyTitleField.setDisable(value);
        marketCapField.setDisable(value);
        ipoYearField.setDisable(value);
        sectorField.setDisable(value);
        industryField.setDisable(value);
        websiteField.setDisable(value);
        stockDataTable.setDisable(value);
        graphViewButton.setDisable(value);
    }

    private void initializeManualRadioButton() {
        manualRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetAll(true);
                tickerSymbolEntryField.setDisable(false);
            }
        });
    }

    private void initializeGraphButton() {
        graphViewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root = null;
                stage = (Stage) graphViewButton.getScene().getWindow();

                try {
                    root = FXMLLoader.load(getClass().getResource("../Stock-Graph.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
    }


    private void initializeList(final HashMap<String, Stock> tickersMap) {
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

    private void intializeListRadioButton(final HashMap<String, Stock> tickersMap) {
        tickerSymbolListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                String searchSymbol = tickerSymbolListView.getSelectionModel().getSelectedItem().toString();

                displayProfile(searchSymbol, tickersMap);

            }
        });
    }
}
