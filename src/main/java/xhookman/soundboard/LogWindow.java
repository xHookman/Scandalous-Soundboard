package xhookman.soundboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static xhookman.soundboard.soundboard.FilesUtil.getJarPath;
import static xhookman.soundboard.soundboard.FilesUtil.getNewJarName;

public class LogWindow {
    private final JFrame frame;
    private final JTextArea textArea;
    private final JButton button;

    public LogWindow(){
        frame = new JFrame("Soundboard Updater");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea();
        button = new JButton("Ok");
        frame.add(textArea,  BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        textArea.setEditable(false);
        textArea.setText("Please wait while the mod is updating...");
        button.addActionListener((ActionEvent e) -> {
            removeJar();
            frame.dispose();
        });

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                removeJar();
            }
        });
    }
    public void writeToLog(String text){
        textArea.append("\n" + text);
        frame.pack();
    }

    public void addOkButton(){
        frame.add(button, BorderLayout.SOUTH);
        frame.pack();
    }

    private static void removeJar(){ // The file is removed after 2 seconds
        try {
            String jarName = getJarPath().substring(getJarPath().lastIndexOf("/")+1);
            String command = "Start-Sleep -Seconds 2;Remove-Item -Path '"+ jarName + "' -Force;" +
                    "Rename-Item " + getNewJarName() + " " + getJarPath().substring(getJarPath().lastIndexOf("/")+1);
            ProcessBuilder pb = new ProcessBuilder("powershell", "-Command", command); // Obliged because Windows 11 is trash and doing it with a cmd does not work
            pb.directory(new File(getJarPath()).getParentFile());
            System.out.println("Deleting jar...");
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
