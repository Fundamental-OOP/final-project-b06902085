package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Paths;
import java.util.List;

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

    private static BufferedReader getBufferedReader(Object fileName) {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(files.get(fileName));
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }

    public static int process(Object fileName,List<List<Integer>> NoteList) {
        try( BufferedReader br = getBufferedReader(fileName) ){
            String currentLine;
            char[] charArr;
            while((currentLine = br.readLine()) != null) {
               charArr = currentLine.toCharArray();
               NoteList.get(0).add(Integer.parseInt(String.valueOf(charArr[0])));
               NoteList.get(1).add(Integer.parseInt(String.valueOf(charArr[1])));
               NoteList.get(2).add(Integer.parseInt(String.valueOf(charArr[2])));
               NoteList.get(3).add(Integer.parseInt(String.valueOf(charArr[3])));
            } 
        }catch(IOException e) {
                e.printStackTrace();   
        }

        return NoteList.get(0).size();
    }

}

