package sample.models.stockFactories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
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
    public TableView tableView;
    public TextField tickerSymbolEntryField;
    public TextField companyTitleField;
    public TextField marketCapField;
    public TextField ipoYearField;
    public TextField sectorField;
    public TextField industryField;
    public TextField websiteField;

    private TextField filterField;
//    private TableView<StockHistoryRecord> stockTable;
//    private TableColumn<StockHistoryRecord, String> firstNameColumn;
//    private TableColumn<StockHistoryRecord, String> lastNameColumn;
//    private ObservableList<StockHistoryRecord> masterData = FXCollections.observableArrayList();


    public void resetAll() {
        tickerSymbolListView.setDisable(true);
        tickerSymbolEntryField.setDisable(true);
        companyTitleField.setDisable(true);
        marketCapField.setDisable(true);
        ipoYearField.setDisable(true);
        sectorField.setDisable(true);
        industryField.setDisable(true);
        websiteField.setDisable(true);
        tableView.setDisable(true);

    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        final HashMap<String, Stock> tickersMap = buildingStocks();
        final PrintProfile printProfile = new PrintProfile();

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
                    tableView.setDisable(false);

                    ObservableList<String> observableList = FXCollections.observableArrayList();

                    for (Stock stock : tickersMap.values()) {
                        ArrayList<StockHistoryRecord> data = stock.getStockHistoryRecords();

                        observableList.add(String.valueOf(data));

                    }

                    String companyName = printProfile.getCompanyName(tickersMap, tickerSymbolEntryField.getText());
                    String marketCap = printProfile.getMarketCap(tickersMap, tickerSymbolEntryField.getText());
                    String ipoYear = printProfile.getIpoYear(tickersMap, tickerSymbolEntryField.getText());
                    String sector = printProfile.getSector(tickersMap, tickerSymbolEntryField.getText());
                    String industry = printProfile.getIndustry(tickersMap, tickerSymbolEntryField.getText());
                    String website = printProfile.getLink(tickersMap, tickerSymbolEntryField.getText());

                    companyTitleField.setText(companyName);
                    marketCapField.setText(marketCap);
                    ipoYearField.setText(ipoYear);
                    sectorField.setText(sector);
                    industryField.setText(industry);
                    websiteField.setText(website);

                }
            }
        });
    }

    private HashMap<String, Stock> buildingStocks() {
        AllStockFactory allStockFactory = new AllStockFactory();
        HashMap<String, Stock> tickersMap = allStockFactory.build();
        final StockHistoryFactory stockHistoryFactory = new StockHistoryFactory();
        PopulateStockHistory.populateStockHistory(stockHistoryFactory, tickersMap);
        return tickersMap;
    }
}