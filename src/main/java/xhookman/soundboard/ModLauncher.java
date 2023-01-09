package xhookman.soundboard;

import xhookman.soundboard.soundboard.FilesUtil;
import javax.swing.*;
import java.io.File;

import static xhookman.soundboard.soundboard.FilesUtil.*;

public class ModLauncher {

    private static void removeJar(){ // The file is removed after 2 seconds
        try {
            String jarName = getJarPath().substring(getJarPath().lastIndexOf("/")+1);
            String command = "Start-Sleep -Seconds 2;Remove-Item -Path '"+ jarName + "' -Force;" +
                    "Rename-Item " + getNewJarName() + " " + getJarPath().substring(getJarPath().lastIndexOf("/")+1);
           // ProcessBuilder pb = new ProcessBuilder("powershell", "-Command", "Start-Sleep -Seconds 2; Start-Process -FilePath 'deleteJar.bat'"); // Obliged because Windows 11 is trash and doing it with a cmd does not work
           // ProcessBuilder pb = new ProcessBuilder("powershell -Command Start-Sleep -Seconds 2;Remove-Item -Path '"+ getWindowsJarPath() + "' -Force"); // Obliged because Windows 11 is trash and doing it with a cmd does not work
            ProcessBuilder pb = new ProcessBuilder("powershell", "-Command", command); // Obliged because Windows 11 is trash and doing it with a cmd does not work
            pb.directory(new File(getJarPath()).getParentFile());
            System.out.println("Deleting jar...");
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] args) {
            FilesUtil.createFiles();
            File dir = FilesUtil.getDir();
            System.out.println("Checking files name...");
            checkFilesName(dir);
            System.out.println("Generating new jar...");
            FilesUtil.generateFiles(dir);
            System.out.println("Mod updated! The old jar will be deleted 2 seconds after you close this window.");
            //show a message box

            String message = "Mod updated! The old jar will be deleted 2 seconds after you close this window.";
            String title = "Soundboard Updater";
            JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION);

            removeJar();
        }
}
