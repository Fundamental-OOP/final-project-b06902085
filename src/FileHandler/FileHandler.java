package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Paths;

public class FileHandler {
    private static final Map<Object, File> files = new HashMap<>();

    public static void addFileByFilePath(Object fileName, File file) {
        files.put(fileName, file);
    }

    public static void addFileByFilePath(Object fileName, String path) {
        files.put(fileName, Paths.get(path).toFile());
    }

    public static void removeFile(Object fileName) {
        files.remove(fileName);
    }

    public static BufferedReader getBufferedReader(Object fileName) {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(files.get(fileName));
            br = new BufferedReader(fr); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }
}
