package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadThroughTickers {
    static void readThroughTickers() throws FileNotFoundException {
        HashMap<String, Stock> tickersMap = new HashMap<String, Stock>();
        File filePointer = new File("/Users/joeyarbrough/Advanced-Java-Labs/Homework/Assign02/src/Tickers.csv");
        Scanner input = new Scanner(filePointer);
        input.nextLine(); // throw away first line with headers
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] stockArray = line.split(",");
            String symbol = stockArray[0];
            String name = stockArray[1];
            String marketCap = stockArray[3];
            String ipoYear = stockArray[4];
            String sector = stockArray[5];
            String industry = stockArray[6];
            String link = stockArray[7];

            Stock tempStock = new Stock(symbol, name, marketCap, ipoYear, sector, industry, link);
            tickersMap.put(tempStock.getSymbol(), tempStock);
        }

        PrintTicker printTicker = new PrintTicker();

        String printCompanyName = printCompanyMethod(tickersMap, printTicker);
        String printMarketCap = printTicker.displayMarketCap(tickersMap);
        String printIpoYear = printTicker.displayIpoYear(tickersMap);
        String printSector = printTicker.displaySector(tickersMap);
        String printIndustry = printTicker.displayIndustry(tickersMap);
        String printLink = printTicker.displayLink(tickersMap);

        System.out.println(printCompanyName + "bla bla " + printMarketCap);

        input.close();
    }

    public static String printCompanyMethod(HashMap<String, Stock> tickersMap, PrintTicker printTicker) {
        return printTicker.displayCompanyName(tickersMap);
    }
}