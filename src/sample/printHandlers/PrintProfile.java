package sample.printHandlers;

import sample.models.stockFactories.Stock;

import java.util.HashMap;

public class PrintProfile {
    public PrintProfile() {
    }

    public String getCompanyName(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get(searchKey);

        return stock.companyNameToString();
    }

    public String getCompanySymbol(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get(searchKey);

        return stock.tickerSymbolToString();
    }

    public String getMarketCap(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get(searchKey);

        return stock.marketCapToString();
    }

    public String getIpoYear(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get(searchKey);

        return stock.ipoYearToString();
    }

    public String getSector(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get(searchKey);

        return stock.sectorToString();
    }

    public String getIndustry(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get(searchKey);

        return stock.industryToString();
    }

    public String getLink(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get(searchKey);

        return stock.linkToString();
    }

}