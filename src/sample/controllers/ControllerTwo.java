package sample.controllers;

import javafx.fxml.Initializable;
import sample.models.stockFactories.AllStockFactory;
import sample.models.stockFactories.PopulateStockHistory;
import sample.models.stockFactories.Stock;
import sample.models.stockFactories.StockHistoryFactory;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ControllerTwo implements Initializable {


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        final StockHistoryFactory stockHistoryFactory = new StockHistoryFactory();
        AllStockFactory allStockFactory = new AllStockFactory();
        PopulateStockHistory populateStockHistory = new PopulateStockHistory();
        HashMap<String, Stock> tickersMap = allStockFactory.build();
        populateStockHistory.populateStockHistory(stockHistoryFactory, tickersMap);

    }

}
