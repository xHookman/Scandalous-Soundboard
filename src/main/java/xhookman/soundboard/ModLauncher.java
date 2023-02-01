package xhookman.soundboard;

import xhookman.soundboard.soundboard.Converter;
import xhookman.soundboard.soundboard.FilesUtil;
import java.io.File;

public class ModLauncher {
        public static void main(String[] args) {
            LogWindow logWindow = new LogWindow();
            FilesUtil.setLogWindow(logWindow);
            FilesUtil.createSoundboardDir();
            File dir = FilesUtil.getSoundboardDir();
            logWindow.writeToLog("Checking and converting files...");
            Converter.checkAndConvertFiles(dir);
            logWindow.writeToLog("Generating new jar...");
            FilesUtil.generateFiles(dir);
            logWindow.writeToLog("Mod updated! The old jar will be deleted 2 seconds after you close this window.");
            logWindow.addOkButton();
        }
}
