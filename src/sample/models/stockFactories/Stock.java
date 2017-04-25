package sample.models.stockFactories;

import java.util.ArrayList;
import java.util.HashMap;

public class Stock {

    private String symbol;
    private String name;
    private String marketCap;
    private String ipoYear;
    private String sector;
    private String industry;
    private String link;

    private String date;
    private ArrayList<StockHistoryRecord> stockHistoryRecords;

    public Stock(String symbol, String name, String marketCap, String ipoYear, String sector, String industry, String link) {
        this.symbol = symbol;
        this.name = name;
        this.marketCap = marketCap;
        this.ipoYear = ipoYear;
        this.sector = sector;
        this.industry = industry;
        this.link = link;
    }

    public ArrayList<StockHistoryRecord> getStockHistoryRecords() {
        return stockHistoryRecords;
    }

    public void setStockHistoryRecords(ArrayList<StockHistoryRecord> stockHistoryRecords) {
        this.stockHistoryRecords = stockHistoryRecords;
    }

    public String getDate() {
        return date;
    }



    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getIpoYear() { return ipoYear; }

    public void setIpoYear(String ipoYear) {
        this.ipoYear = ipoYear;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String tickerSymbolToString() {
        return this.getSymbol();
    }

    public String companyNameToString() { return this.getName();}

    public String marketCapToString() {
        return this.getMarketCap();
    }

    public String ipoYearToString() {
        return this.getIpoYear();
    }

    public String sectorToString() {
        return this.getSector();
    }

    public String industryToString() {
        return this.getIndustry();
    }

    public String linkToString() {
        return this.getLink();
    }
}