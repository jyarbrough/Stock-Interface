package sample.printHandlers;

import sample.models.stockFactories.StockHistoryRecord;

import java.util.ArrayList;
import java.util.stream.Stream;

public class PrintStats extends StockHistoryRecord {
    public PrintStats() {
    }

    public String getStatsDisplayMessage(ArrayList<StockHistoryRecord> tempHistory) {

        String displayMessage = null;
        for (int i = 0; i < tempHistory.size(); i++) {
            StockHistoryRecord stockStats = tempHistory.get(i);

            displayMessage = "\nThe Stats Are: " + stockStats.toString();
        }
        return displayMessage;

    }


    public String getDateStats(ArrayList<StockHistoryRecord> tempHistory) {

        String dateMessage = null;
        for (int i = 0; i < tempHistory.size(); i++) {
            StockHistoryRecord stockStats = tempHistory.get(i);

            dateMessage = stockStats.getDate();
        }

        return dateMessage;
    }

    public String getHighStats(ArrayList<StockHistoryRecord> tempHistory) {

        String highMessage = null;
        for (int i = 0; i < tempHistory.size(); i++) {
            StockHistoryRecord stockStats = tempHistory.get(i);

            highMessage = String.valueOf(stockStats.getHigh());
        }

        return highMessage;
    }

    public String getLowStats(ArrayList<StockHistoryRecord> tempHistory) {

        String lowMessage = null;
        for (int i = 0; i < tempHistory.size(); i++) {
            StockHistoryRecord stockStats = tempHistory.get(i);

            lowMessage = String.valueOf(stockStats.getLow());
        }

        return lowMessage;
    }

    public String getAdjCloseStats(ArrayList<StockHistoryRecord> tempHistory) {

        String adjCloseMessage = null;
        for (int i = 0; i < tempHistory.size(); i++) {
            StockHistoryRecord stockStats = tempHistory.get(i);

            adjCloseMessage = String.valueOf(stockStats.getAdjClose());
        }

        return adjCloseMessage;
    }

    @Override
    public Stream<StockHistoryRecord> stream() {
        return null;
    }
}
