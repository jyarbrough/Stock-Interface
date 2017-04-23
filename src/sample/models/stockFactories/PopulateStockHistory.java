package sample.models.stockFactories;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class PopulateStockHistory {
    static void populateStockHistory(StockHistoryFactory stockHistoryFactory, HashMap<String, Stock> stocks) {
        for (String symbol : stocks.keySet()) {
            String path = "/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/outputFiles/".concat(symbol).concat(".csv");

            ArrayList<StockHistoryRecord> stockHistoryRecords = null;
            try {
                stockHistoryRecords = stockHistoryFactory.build(path);
            } catch (FileNotFoundException e) {
//                e.printStackTrace();
            } finally {
                Stock stock = stocks.get(symbol);
                stock.setStockHistoryRecords(stockHistoryRecords);
            }
        }
    }
}