package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.*;

public class WorkWithFiles {
    // Как, используя try-catch, ловить ошибки отсутствия страниц
    public void namesOfFilesInDirectory(Path dir, Path saveFile) throws IOException {
        if (!Files.isDirectory(dir)) {
            throw new IOException("Dir parameter must be directory");
        }
        var files = Files.walk(dir);
        //Clean/create save-file
        Files.writeString(saveFile, "");
        files.forEach(file -> {
            try {
                Files.writeString(saveFile, file.toString() + "\r\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
