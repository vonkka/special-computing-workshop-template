package ru.spbu.apcyb.svp.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountWordsInText {
    public Map<String, Long> countWords(String inFile, String outDir) throws IOException {
        Path inPath = Path.of(inFile);
        if (Files.isDirectory(inPath)) {
            throw new IOException("Provided input file is a directory");
        }
        if (!Files.isDirectory(Path.of(outDir))) {
            throw new IOException("Provided output file is not a directory");
        }

        try (Stream<String> lines = Files.lines(inPath);
             FileWriter fileWriter = new FileWriter(outDir + "\\allWordsSet.txt")) {
            var wordCount = lines.flatMap(line -> Arrays.stream(line.split("[^a-zA-ZЁёА-я0-9]"))).
                    filter(line -> !line.isEmpty()).
                    map(String::toLowerCase).
                    collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            for (Map.Entry<String, Long> pair: wordCount.entrySet()) {
                String word = pair.getKey();
                Long count = pair.getValue();
                fileWriter.write(word + " " + count + "\n");
            }
            return wordCount;
        }
    }

    public void divideWordsIntoFiles(String outDir, Map<String, Long> wordCount) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        List<CompletableFuture> futures = new ArrayList<>();
        for (Map.Entry<String, Long> pair: wordCount.entrySet()) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(
                    () -> {
                        try {
                            createWordFile(outDir, pair.getKey(), pair.getValue());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            , executor);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        futures.clear();
        executor.shutdown();
    }


    private void createWordFile(String outDir, String word, Long count) throws IOException {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < count; i++) {
            res.append(word).append(" ");
        }
        res.append(word);
        Files.writeString(Path.of(outDir + "\\" + word + ".txt"), res);
    }
}
