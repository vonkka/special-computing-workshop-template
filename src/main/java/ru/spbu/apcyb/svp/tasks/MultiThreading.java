package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreading {
    private int maxThreads = 8;

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public List<Double> getNums(String file) throws IOException, NumberFormatException {
        if (Files.isDirectory(Path.of(file))) {
            throw new IOException("Provided file is a directory");
        }
        Scanner scanner = new Scanner(new File(file));
        List<Double> numbers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            StringTokenizer stringTokenizer = new StringTokenizer(scanner.nextLine(), " ");
            while (stringTokenizer.hasMoreTokens()) {
                double num = Double.parseDouble(stringTokenizer.nextToken());
                numbers.add(num);
            }
        }
        return numbers;
    }

    public List<Double> calculateTan(List<Double> nums) {
        Double[] res = new Double[nums.size()];
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        List<CompletableFuture> futures = new ArrayList<>();
        int blockSize = (int) Math.ceil((double) nums.size() / maxThreads);
        for (int i = 0; i < maxThreads; i++) {
            int k = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(
                    () -> calculateTan(res, nums, k * blockSize, (k + 1) * blockSize)
                    , executor);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        futures.clear();
        executor.shutdown();
        return Arrays.asList(res);
    }

    public void writeTan(Path fileOut, List<Double> res) throws IOException {
        Files.writeString(fileOut, res.toString().replace("[", "").
                replace(",", "").replace("]", ""));
    }

    public void calculateTan(Double[] res, List<Double> nums, int start, int end) {
        end = Math.min(end, nums.size());
        for (int i = start; i < end; ++i) {
            res[i] = Math.tan(nums.get(i));
        }
    }

    private void generateNumbers(Path saveIn, int num, double min, double len) throws IOException {
        double[] res = new double[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            res[i] = min + random.nextDouble() * len;
        }
        Files.writeString(saveIn, Arrays.toString(res).replace("[", "").
                replace("]", "").replace(",", ""));
    }
}