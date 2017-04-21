package sample;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InsertFilePathAndMerge {
    static void insertFilePathAndMerge() throws IOException {
        List<Path> paths = Arrays.asList(Paths.get("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/amex.csv"), Paths.get("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/NASDAQ.csv"), Paths.get("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/NYSE.csv"));
        List<String> mergedLines = FilesToMerge.getMergedLines(paths);
        Path target = Paths.get("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/Tickers.csv");
        Files.write(target, mergedLines, Charset.forName("UTF-8"));
    }
}