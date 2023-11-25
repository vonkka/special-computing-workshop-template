package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MultiThreading {
    private int maxThreads = 8;
    private final int startSize = 100;

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public double[] getNums(String file) throws IOException, NumberFormatException {
        if (Files.isDirectory(Path.of(file))) {
            throw new IOException("Provided file is a directory");
        }
        Scanner scanner = new Scanner(new File(file));
        double[] numbers = new double[startSize];
        int k = 0;
        while (scanner.hasNextLine()) {
            StringTokenizer stringTokenizer = new StringTokenizer(scanner.nextLine(), " ");
            while (stringTokenizer.hasMoreTokens()) {
                numbers[k++] = Double.parseDouble(stringTokenizer.nextToken());
                if (k == numbers.length) {
                    numbers = Arrays.copyOf(numbers, k * 10);
                }
            }
        }
        numbers = Arrays.copyOf(numbers, k);
        return numbers;
    }

    public double[] calculateTan(double[] nums) throws InterruptedException {
        double[] res = new double[nums.length];
        Queue<Thread> threads = new LinkedList<>();
        int blockSize = (int) Math.ceil((double) nums.length / maxThreads);
        for (int i = 0; i < maxThreads; i++) {
            Thread thread = new Thread(new MyThread(res, nums, i * blockSize, (i + 1) * blockSize));
            thread.start();
            threads.add(thread);
        }
        for (Thread thread: threads) {
            thread.join();
        }
        threads.clear();
        return res;
    }

    public void writeTan(Path fileOut, double[] res) throws IOException {
        Files.writeString(fileOut, Arrays.toString(res).replace("[", "").
                replace("]", "").replace(",", ""));
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