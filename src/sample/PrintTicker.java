package sample;

import java.util.HashMap;

public class PrintTicker {
    PrintTicker() {
    }

//    public String getDisplayMessage(HashMap<String, Stock> tickersMap) {
//        Stock stock = tickersMap.get("\"XXII\"");
//
//        return "-----The Information For The Stock-----\n"  + stock.toString();
//    }


    public String displayCompanyName(HashMap<String, Stock> tickersMap) {
        Stock stock = tickersMap.get("\"XXII\"");

        return "Company Name: " + stock.companyNameToString();
    }

    public String displayCompanySymbol(HashMap<String, Stock> tickersMap) {
        Stock stock = tickersMap.get("\"XXII\"");

        return "Symbol" + stock.tickerSymbolToString();
    }

    public String displayMarketCap(HashMap<String, Stock> tickersMap) {
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