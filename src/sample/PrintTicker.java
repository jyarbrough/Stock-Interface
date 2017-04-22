package sample;

import sample.models.Stock;

import java.util.HashMap;

public class PrintTicker {
    PrintTicker() {
    }

//    public String getDisplayMessage(HashMap<String, Stock> tickersMap) {
//        Stock stock = tickersMap.get("\"XXII\"");
//
//        return "-----The Information For The Stock-----\n"  + stock.toString();
//    }


    public String getCompanyName(HashMap<String, Stock> tickersMap, String searchKey) {
        Stock stock = tickersMap.get("\"" + searchKey + "\"");

        return stock.companyNameToString().substring(1, stock.companyNameToString().length() - 1 );
//        return stock.companyNameToString().replace("\"", "");
    }

    public String getCompanySymbol(HashMap<String, Stock> tickersMap) {
        Stock stock = tickersMap.get("\"XXII\"");

        return stock.tickerSymbolToString();
    }

    public String getMarketCap(HashMap<String, Stock> tickersMap) {
        Stock stock = tickersMap.get("\"XXII\"");

        return stock.marketCapToString();
    }

    public String displayIpoYear(HashMap<String, Stock> tickersMap) {
        Stock stock = tickersMap.get("\"XXII\"");

        return stock.ipoYearToString();
    }

    public String displaySector(HashMap<String, Stock> tickersMap) {
        Stock stock = tickersMap.get("\"XXII\"");

        return stock.sectorToString();
    }

    public String displayIndustry(HashMap<String, Stock> tickersMap) {
        Stock stock = tickersMap.get("\"XXII\"");

        return stock.industryToString();
    }

    public String displayLink(HashMap<String, Stock> tickersMap) {
        Stock stock = tickersMap.get("\"XXII\"");

        return stock.linkToString();
    }
}