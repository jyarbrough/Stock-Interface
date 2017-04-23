package sample.models.stockFactories;

public class StockHistoryRecord {

    private String date;
    private Double high;
    private Double low;
    private Double adjClose;

    public StockHistoryRecord(String date, Double high, Double low, Double adjClose) {
        this.date = date;
        this.high = high;
        this.low = low;
        this.adjClose = adjClose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    @Override
    public String toString() {
        return "\nDate: " + this.getDate() + "\nHigh: " + this.getHigh() + "\nLow: " + this.getLow()
                + "\nAdjust Closing: " + this.getAdjClose();
    }
}