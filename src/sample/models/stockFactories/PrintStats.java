package sample.models.stockFactories;

import java.util.ArrayList;

public class PrintStats {
    PrintStats() {
    }

    public String getStatsDisplayMessage(ArrayList<StockHistoryRecord> tempHistory) {
        String displayMessage = null;
        for(int i = 0; i < tempHistory.size(); i++) {
            StockHistoryRecord stockStats = tempHistory.get(i);
            displayMessage = "\nThe Stats Are: " + stockStats.toString();
        }
        return displayMessage;
    }
}