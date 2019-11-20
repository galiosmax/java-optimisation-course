package utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Utils {

    public static File open(String[] args) {

        File needed;
        if (args.length == 1) {
            needed = new File(args[0]);
        } else {
            needed = askFile();
        }
        return needed;

    }

    private static File askFile() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Files", "txt", "TXT");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileFilter(filter);
        File needed = null;
        if (fileChooser.showDialog(null, "Open file") == JFileChooser.APPROVE_OPTION) {
            String name = fileChooser.getSelectedFile().getAbsolutePath();
            needed = new File(name);
            String extension = getExtension(name);
            if (!extension.equals("txt") && !extension.equals("TXT")) {
                showCantReadDialog();
                return null;
            }
        }
        return needed;
    }

    private static String getExtension(String name) {
        String extension = "";

        int i = name.lastIndexOf('.');
        if (i > 0) {
            extension = name.substring(i + 1);
        }
        return extension;
    }

    private static void showCantReadDialog() {
        JOptionPane.showMessageDialog(null, "Can't read the file");
    }

}
