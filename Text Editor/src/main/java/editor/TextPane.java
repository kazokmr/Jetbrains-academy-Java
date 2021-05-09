package editor;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;

public class TextPane extends JScrollPane {

    private final JTextArea textArea;

    public TextPane() {
        this.textArea = new JTextArea();
        this.textArea.setName("TextArea");
        setViewportView(textArea);
        setName("ScrollPane");
        Border marginBorder = new EmptyBorder(new Insets(10, 10, 10, 10));
        setBorder(getBorder() == null ? marginBorder : new CompoundBorder(marginBorder, getBorder()));
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void highlightMatchWord(int start, int end) {
        textArea.setCaretPosition(end);
        textArea.select(start, end);
        textArea.grabFocus();
    }
}
