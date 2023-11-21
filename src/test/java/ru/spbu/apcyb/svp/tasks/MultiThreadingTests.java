package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MultiThreadingTests {
    MultiThreading multiThreading = new MultiThreading();

    @Test
    public void numbers16Test() throws IOException, InterruptedException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\16numbersIn";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\16numbersOut");
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\16numbersCheck");
        multiThreading.writeTan(outFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        Assertions.assertEquals(-1, Files.mismatch(outFile, checkFile));
    }

    @Test
    public void numbers1000Test() throws IOException, InterruptedException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\1000numbersIn";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000numbersOut");
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000numbersCheck");
        multiThreading.writeTan(outFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        Assertions.assertEquals(-1, Files.mismatch(outFile, checkFile));
    }

    @Test
    public void numbers10000Test() throws IOException, InterruptedException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\10000numbersIn";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\10000numbersOut");
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\10000numbersCheck");
        multiThreading.writeTan(outFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        Assertions.assertEquals(-1, Files.mismatch(outFile, checkFile));
    }

    @Test
    public void numbers1000000Test() throws IOException, InterruptedException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\1000000numbersIn";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000000numbersOut");
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000000numbersCheck");
        multiThreading.writeTan(outFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        Assertions.assertEquals(-1, Files.mismatch(outFile, checkFile));
    }

    @Test
    public void speedTest1000() throws IOException, InterruptedException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\1000numbersIn";
        double[] input = multiThreading.getNums(inFile);
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            multiThreading.calculateTan(input);
        }
        long t1 = System.nanoTime() - start;
        multiThreading.setMaxThreads(1);
        start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            multiThreading.calculateTan(input);
        }
        long t2 = System.nanoTime() - start;
        System.out.println((double) t2 / t1);
    }

    @Test
    public void speedTest10000() throws IOException, InterruptedException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\10000numbersIn";
        double[] input = multiThreading.getNums(inFile);
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            multiThreading.calculateTan(input);
        }
        long t1 = System.nanoTime() - start;
        multiThreading.setMaxThreads(1);
        start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            multiThreading.calculateTan(input);
        }
        long t2 = System.nanoTime() - start;
        System.out.println((double) t2 / t1);
    }

    @Test
    public void speedTest1000000() throws IOException, InterruptedException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\1000000numbersIn";
        double[] input = multiThreading.getNums(inFile);
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            multiThreading.calculateTan(input);
        }
        long t1 = System.nanoTime() - start;
        multiThreading.setMaxThreads(1);
        start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            multiThreading.calculateTan(input);
        }
        long t2 = System.nanoTime() - start;
        System.out.println((double) t2 / t1);
    }

    @Test
    public void badInput() {
        String inFile = "src\\main\\resources\\multiThreadingTests\\badInput";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000000numbersOut");
        Assertions.assertThrows(NumberFormatException.class,
                () -> {
                    multiThreading.writeTan(outFile,
                            multiThreading.calculateTan(multiThreading.getNums(inFile)));
                },
                "Number format exception must be thrown");
    }
}
