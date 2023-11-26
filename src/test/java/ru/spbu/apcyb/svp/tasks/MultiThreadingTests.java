package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MultiThreadingTests {
    MultiThreading multiThreading = new MultiThreading();

    @Test
    public void numbers16Test() throws IOException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\16numbersIn.txt";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\16numbersOut.txt");
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\16numbersCheck.txt");
        multiThreading.writeTan(outFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        multiThreading.writeTan(checkFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        Assertions.assertEquals(-1, Files.mismatch(outFile, checkFile));
    }

    @Test
    public void numbers1000Test() throws IOException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\1000numbersIn.txt";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000numbersOut.txt");
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000numbersCheck.txt");
        multiThreading.writeTan(outFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        multiThreading.writeTan(checkFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        Assertions.assertEquals(-1, Files.mismatch(outFile, checkFile));
    }

    @Test
    public void numbers10000Test() throws IOException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\10000numbersIn.txt";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\10000numbersOut.txt");
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\10000numbersCheck.txt");
        multiThreading.writeTan(outFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        multiThreading.writeTan(checkFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        Assertions.assertEquals(-1, Files.mismatch(outFile, checkFile));
    }

    @Test
    public void numbers1000000Test() throws IOException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\1000000numbersIn.txt";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000000numbersOut.txt");
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000000numbersCheck.txt");
        multiThreading.writeTan(outFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        multiThreading.writeTan(checkFile,
                multiThreading.calculateTan(multiThreading.getNums(inFile)));
        Assertions.assertEquals(-1, Files.mismatch(outFile, checkFile));
    }

    @Test
    public void speedTest1000() throws IOException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\1000numbersIn.txt";
        String outFile = "src\\main\\resources\\multiThreadingTests\\1000numbersOut.txt";
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000numbersCheck.txt");
        List<Double> input = multiThreading.getNums(inFile);
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
    public void speedTest10000() throws IOException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\10000numbersIn.txt";
        String outFile = "src\\main\\resources\\multiThreadingTests\\10000numbersOut.txt";
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\10000numbersCheck.txt");
        List<Double> input = multiThreading.getNums(inFile);
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
    public void speedTest1000000() throws IOException {
        String inFile = "src\\main\\resources\\multiThreadingTests\\1000000numbersIn.txt";
        String outFile = "src\\main\\resources\\multiThreadingTests\\1000000numbersOut.txt";
        Path checkFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000000numbersCheck.txt");
        List<Double> input = multiThreading.getNums(inFile);
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
        String inFile = "src\\main\\resources\\multiThreadingTests\\badInput.txt";
        Path outFile = Path.of("src\\main\\resources\\multiThreadingTests\\1000000numbersOut.txt");
        Assertions.assertThrows(NumberFormatException.class,
                () -> {
                    multiThreading.writeTan(outFile,
                            multiThreading.calculateTan(multiThreading.getNums(inFile)));
                },
                "Number format exception must be thrown");
    }
}