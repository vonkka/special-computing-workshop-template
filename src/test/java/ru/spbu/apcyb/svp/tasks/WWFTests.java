package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WWFTests {
    WorkWithFiles wwf = new WorkWithFiles();

    @Test
    public void getFilesTest() throws IOException {
        Path dir = Path.of("src\\main\\resources\\testDir");
        Path saveIn = Path.of("src\\main\\resources\\saveTest1");
        Path checkFile = Path.of("src\\main\\resources\\checkTest1");
        wwf.namesOfFilesInDirectory(dir, saveIn);
        Assertions.assertEquals(-1, Files.mismatch(saveIn, checkFile));
    }

    @Test
    public void notExistingLocalDirectory() throws IOException {
        Path dir = Path.of("src\\main\\resources\\somedir");
        Path saveIn = Path.of("src\\main\\resources\\saveTest1");
        Assertions.assertThrows(IOException.class,
                () -> wwf.namesOfFilesInDirectory(dir, saveIn),
                "No such file exception must be thrown");
    }

    @Test
    public void dirIsFile() throws IOException {
        Path dir = Path.of("src\\main\\resources\\checkIn");
        Path saveIn = Path.of("src\\main\\resources\\saveTest3");
        Assertions.assertThrows(IOException.class,
                () -> wwf.namesOfFilesInDirectory(dir, saveIn),
                "Directory excepted error must be thrown");
    }

    @Test
    public void saveFileIsDirectory() throws IOException {
        Path dir = Path.of("src\\main\\resources\\testDir");
        Path saveIn = Path.of("src\\main\\resources\\testDir");
        Assertions.assertThrows(IOException.class,
                () -> wwf.namesOfFilesInDirectory(dir, saveIn),
                "Save file must be file but not directory");
    }

}
