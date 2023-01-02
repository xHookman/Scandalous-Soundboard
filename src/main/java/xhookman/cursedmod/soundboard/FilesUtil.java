package xhookman.cursedmod.soundboard;


import xhookman.cursedmod.Cursedmod;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static xhookman.cursedmod.client.CursedmodClient.LOGGER;

public class FilesUtil {
    private static File soundboardDir;
    private static File tmpFile;

    static {
        try {
            tmpFile = File.createTempFile("cursedmodtmp", ".jar");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ;
    public static void createFiles(){
        soundboardDir = new File("mods/soundboard");
        if (!soundboardDir.exists()) {
            soundboardDir.mkdir();
        }
    }
    public static File getDir(){
        return soundboardDir;
    }

    public static void copyFile(File fileToCopy) {
        // Open the jar file for reading
        File jarFile = new File("mods/cursedmod-1.0-SNAPSHOT.jar");
        try (ZipInputStream in = new ZipInputStream(new FileInputStream(jarFile))) {
            // Create a temporary jar file
            try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tmpFile))) {
                // Copy all the entries from the jar file
                ZipEntry entry = in.getNextEntry();
                while (entry != null) {
                    out.putNextEntry(new ZipEntry(entry.getName()));
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = in.read(buffer)) > 0) {
                        out.write(buffer, 0, count);
                    }
                    entry = in.getNextEntry();
                }
                // Add the file to the jar file
                try (FileInputStream fileIn = new FileInputStream(fileToCopy)) {
                    out.putNextEntry(new ZipEntry("assets/cursedmod/sounds/" + fileToCopy.getName()));
                    byte[] fileBuffer = new byte[1024];
                    int fileCount;
                    while ((fileCount = fileIn.read(fileBuffer)) > 0) {
                        out.write(fileBuffer, 0, fileCount);
                    }
                }
            }
            LOGGER.info(" !!!!!!!!!!!!!!!!!!!!!!!!! File copied : " + tmpFile.getPath());
            // Replace the original jar file with the modified jar file
            Files.copy(tmpFile.toPath(), jarFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateSoundsJson(File folder) { // Ne fonctionne pas quand le json existe déjà
        // Open the jar file for reading
        File jarFile = new File("mods/cursedmod-1.0-SNAPSHOT.jar");
        try (ZipInputStream in = new ZipInputStream(new FileInputStream(jarFile))) {
            // Create a temporary jar file

            try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tmpFile))) {
                // Copy all the entries from the jar file
                ZipEntry entry = in.getNextEntry();
                while (entry != null) {
                    out.putNextEntry(new ZipEntry(entry.getName()));
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = in.read(buffer)) > 0) {
                        out.write(buffer, 0, count);
                    }
                    entry = in.getNextEntry();
                }
                ZipEntry newEntry = new ZipEntry("assets/cursedmod/sounds.json");
                String content = "{";

                for(int i=0; i<folder.listFiles().length; i++){
                    String fileName = folder.listFiles()[i].getName().split(".ogg")[0];
                    content += "\"" + fileName + "\": {\n" +
                            "    \"sounds\": [\n" +
                            "      \"cursedmod:" + fileName + "\"\n" +
                            "    ]\n";
                            if(i+1<folder.listFiles().length)
                                content+="  },\n";
                            else
                                content+="  }\n";
                }
                content+="}";

                StringWriter stringWriter = new StringWriter();
                stringWriter.write(content);

                byte[] jsonData = stringWriter.toString().getBytes();
                out.putNextEntry(newEntry);
                out.write(jsonData, 0, jsonData.length);
            }
            // Replace the original jar file with the modified jar file
            Files.copy(tmpFile.toPath(), jarFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getJarName() {
        String path = Cursedmod.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return path.substring(path.lastIndexOf("/"));
    }
}
