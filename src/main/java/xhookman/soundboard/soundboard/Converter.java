package xhookman.soundboard.soundboard;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
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

    private static void convert(File source, File target) throws EncoderException {
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libvorbis");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("ogg");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(source), target, attrs);
    }
    public static void checkAndConvertFiles(File dir){ // Check if the files are in the right format and convert them if needed
        for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (!file.getName().endsWith(".ogg")) {
                    System.out.println("Converting " + file.getName() + " to ogg...");
                    try {
                        convert(file, new File(dir, getValidFileName(getFileNameWithoutExtension(file.getName())) + ".ogg"));
                        file.delete();
                    } catch (EncoderException e) {
                        System.out.println("Could not convert " + file.getName() + " :(");
                    }
                } else {
                    file.renameTo(new File(dir, getValidFileName(getFileNameWithoutExtension(file.getName())) + ".ogg"));
                }
        }
    }
}
