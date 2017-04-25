package sample.models.stockFactories;

import sample.printHandlers.PrintStats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StockHistoryFactory extends ArrayList<StockHistoryRecord> {
    public StockHistoryFactory() {
    }

    ArrayList<StockHistoryRecord> build(String path) throws FileNotFoundException {
        File filePointer = new File(path);
        Scanner input = new Scanner(filePointer);
        ArrayList<StockHistoryRecord> tempHistory = new ArrayList<>();
        input.nextLine(); // skips over the first line (header)
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] stockHistoryArray = line.split(",");
            String date = stockHistoryArray[0];
            Double high = Double.valueOf(stockHistoryArray[2]);
            Double low = Double.valueOf(stockHistoryArray[3]);
            Double adjClose = Double.valueOf(stockHistoryArray[5]);

            StockHistoryRecord stockHistoryRecord = new StockHistoryRecord(date, high, low, adjClose);
            tempHistory.add(stockHistoryRecord);
            PrintStats printStats = new PrintStats();
            String dateMessage = printStats.getDateStats(tempHistory);
            String lowMessage = printStats.getLowStats(tempHistory);

            System.out.println(dateMessage + " " + lowMessage);
        }
        return tempHistory;
    }
}