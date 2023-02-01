package xhookman.soundboard.soundboard;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Converter {
    private static String getFileNameWithoutExtension(String fileName){ // Get the file name without extension
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    private static String getValidFileName(String fileName){ // Remove all special characters from the file name
        return fileName.toLowerCase().replaceAll("[^a-z0-9_.]+", "_");
    }

    private static void convert(File sourceFile, File destinationFile) throws IOException {
        String sourceFilePath = sourceFile.getAbsolutePath();
        String destinationFilePath = destinationFile.getAbsolutePath();
        String command = String.format("ffmpeg -i \"%s\" \"%s\"", sourceFilePath, destinationFilePath);
        Runtime.getRuntime().exec(command);
        System.out.println(command);
    }
    public static void checkAndConvertFiles(File dir){ // Check if the files are in the right format and convert them if needed
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            try {
                if (!file.getName().endsWith(".ogg")) {
                    System.out.println("Converting " + file.getName() + " to ogg...");
                    file.renameTo(new File(dir, getValidFileName(file.getName())));
                    convert(file, new File(dir, getFileNameWithoutExtension(file.getName()) + ".ogg"));
                } else {
                    file.renameTo(new File(dir, getValidFileName(getFileNameWithoutExtension(file.getName())) + ".ogg"));
                }
            } catch(Exception e){
                System.out.println("Could not convert " + file.getName() + " :(");
                e.printStackTrace();
            }
        }
    }
}
