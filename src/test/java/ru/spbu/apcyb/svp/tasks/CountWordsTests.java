package ru.spbu.apcyb.svp.tasks;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class CountWordsTests {
    CountWordsInText countWordsInText = new CountWordsInText();
    WorkWithFiles wwf = new WorkWithFiles();
    @Test
    public void smallCounterTest() throws IOException {
        // Input string: "a b b b c a s f r e t fgs gwe qw r qrq e wq rqw re a f gr r g d"
        String inFile = "D:\\JavaProjectTests\\StreamAPI\\small test.txt";
        String outDir = "D:\\JavaProjectTests\\StreamAPI\\test1";
        Map<String, Long> res = countWordsInText.countWords(inFile, outDir);
        Assertions.assertEquals(3L, res.getOrDefault("a", -1L));
        Assertions.assertEquals(3L, res.getOrDefault("r", -1L));
        Assertions.assertEquals(1L, res.getOrDefault("qrq", -1L));
        Assertions.assertEquals(2L, res.getOrDefault("f", -1L));
    }

    @Test
    public void bigCounterTest() throws IOException {
        // Война и мир
        String inFile = "D:\\JavaProjectTests\\StreamAPI\\VoynaIMir.txt";
        String outDir = "D:\\JavaProjectTests\\StreamAPI\\voynaimir";
        Map<String, Long> res = countWordsInText.countWords(inFile, outDir);
        Assertions.assertEquals(2L, res.getOrDefault("ящик", -1L));
        Assertions.assertEquals(2L, res.getOrDefault("лентах", -1L));
        Assertions.assertEquals(1L, res.getOrDefault("лихтенфельс", -1L));
        Assertions.assertEquals(10L, res.getOrDefault("осталась", -1L));
    }
}
