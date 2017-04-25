package sample.models.stockFactories;

import sample.readThroughFileHandlers.PathSetup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class AllStockFactory {

    public HashMap<String, Stock> build(){

        try {
            PathSetup pathSetup = new PathSetup();
            pathSetup.insertFilePathAndMerge();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Stock> tickersMap = new HashMap<String, Stock>();
        File filePointer = new File("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/csvFiles/outputFiles/Tickers.csv");
        Scanner input = null;
        try {
            input = new Scanner(filePointer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        input.nextLine(); // throw away first line with headers
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] stockArray = line.split(",");
            String symbol = stockArray[0].replace("\"", "");
            String name = stockArray[1].replace("\"", "");
            String marketCap = stockArray[3].replace("\"", "");
            String ipoYear = stockArray[4].replace("\"", "");
            String sector = stockArray[5].replace("\"", "");
            String industry = stockArray[6].replace("\"", "");
            String link = stockArray[7].replace("\"", "");

            Stock tempStock = new Stock(symbol, name, marketCap, ipoYear, sector, industry, link);
            tickersMap.put(tempStock.getSymbol(), tempStock);
        }
        input.close();
        return tickersMap;
    }
}