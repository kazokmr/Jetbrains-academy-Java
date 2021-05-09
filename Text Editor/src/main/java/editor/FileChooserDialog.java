package editor;

import editor.business.file.TextFileReader;
import editor.business.file.TextFileWriter;

import javax.swing.JFileChooser;

public class FileChooserDialog extends JFileChooser {

    public FileChooserDialog() {
        super(System.getProperty("user.dir"));
        setName("FileChooser");
        setMultiSelectionEnabled(false);
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setVisible(false);
    }

    public void saveFile(TextPane textPane) {
        setVisible(true);
        setDialogTitle("Save file");
        if (showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            new TextFileWriter(getSelectedFile()).write(textPane.getText());
        }
        setVisible(false);
    }

    public void loadFile(TextPane textPane) {
        setVisible(true);
        setDialogTitle("Open file");
        if (showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            textPane.setText(new TextFileReader(getSelectedFile()).read());
        }
        setVisible(false);
    }
}
