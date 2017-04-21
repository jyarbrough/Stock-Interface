package sample;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilesToMerge {
    static List<String> getMergedLines(List<Path> paths) throws IOException {
        List<String> mergedLines = new ArrayList<String>();
        for (Path p : paths) {
            List<String> lines = Files.readAllLines(p, Charset.forName("UTF-8"));
            if (!lines.isEmpty()) {
                if (mergedLines.isEmpty()) {
                    mergedLines.add(lines.get(0));
                }
                mergedLines.addAll(lines.subList(1, lines.size()));
            }
        }
        return mergedLines;
    }
}