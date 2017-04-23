package sample.models.stockFactories;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PathSetup {
    void insertFilePathAndMerge() throws IOException {
        List<Path> paths = Arrays.asList(Paths.get("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/inputFiles/amex.csv"), Paths.get("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/inputFiles/NASDAQ.csv"), Paths.get("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/inputFiles/NYSE.csv"));
        List<String> mergedLines = MergedLines.getMergedLines(paths);
        Path target = Paths.get("/Users/joeyarbrough/Advanced-Java-Labs/My-Tutorials/InterfaceTest/src/sample/outputFiles/Tickers.csv");
        Files.write(target, mergedLines, Charset.forName("UTF-8"));
    }
}