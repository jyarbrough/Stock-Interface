package sample.models.stockFactories;

import java.util.HashMap;

public class PrintProfile {
    PrintProfile() {
    }

    public String getCompanyName(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get(searchKey);


//        another example of how to remove "" from the csv file
//        return stock.companyNameToString().substring(1, stock.companyNameToString().length() - 1 );
//        return stock.companyNameToString().replace("\"", "");

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