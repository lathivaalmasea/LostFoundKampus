package View.Component;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LabeledInput extends JPanel {

    private final JComponent input;

    private LabeledInput(String labelText, JComponent inputField) {
        this.input = inputField;

        setLayout(new BorderLayout(8, 4));

        JLabel label = new JLabel(labelText);
        label.setFont(AppTheme.LABEL_FONT);

        add(label, BorderLayout.NORTH);
        add(inputField, BorderLayout.CENTER);
    }

    public static LabeledInput text(String labelText, int columns) {
        JTextField field = new JTextField(columns);
        return new LabeledInput(labelText, field);
    }

    public static LabeledInput password(String labelText, int columns) {
        JPasswordField field = new JPasswordField(columns);
        return new LabeledInput(labelText, field);
    }

    public String getText() {
        if (input instanceof JTextField textField) {
            return textField.getText();
        }
        return "";
    }

    public char[] getPassword() {
        if (input instanceof JPasswordField passwordField) {
            return passwordField.getPassword();
        }
        return new char[0];
    }
}
