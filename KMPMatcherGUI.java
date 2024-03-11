package sdaproiect;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.List;

public class KMPMatcherGUI {
    private JPanel panel;
    private JTextField textInputField;
    private JTextField patternInputField;
    private JTextPane resultTextPane;

    public KMPMatcherGUI() {
        panel = new JPanel(new BorderLayout());

        textInputField = new JTextField(20);
        patternInputField = new JTextField(20);
        resultTextPane = new JTextPane();
        resultTextPane.setEditable(false);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> performKMPMatch());

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Text:"));
        controlPanel.add(textInputField);
        controlPanel.add(new JLabel("Pattern:"));
        controlPanel.add(patternInputField);
        controlPanel.add(searchButton);

        JScrollPane scrollPane = new JScrollPane(resultTextPane);

        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private void performKMPMatch() {
        String text = textInputField.getText();
        String pattern = patternInputField.getText();
        if (text.isEmpty() || pattern.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Please enter both text and pattern.");
            return;
        }

        List<Integer> matchPositions = KMPMatcher.search(text, pattern);
        highlightPattern(text, pattern, matchPositions);
    }

    private void highlightPattern(String text, String pattern, List<Integer> matchPositions) {
        StyledDocument doc = resultTextPane.getStyledDocument();

        Style defaultStyle = resultTextPane.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setForeground(defaultStyle, Color.BLACK);
        Style highlightStyle = resultTextPane.addStyle("Highlight", null);
        StyleConstants.setBackground(highlightStyle, Color.YELLOW);

        try {
            doc.remove(0, doc.getLength());
            doc.insertString(doc.getLength(), text + " Occurances: "+Integer.toString(matchPositions.size()), defaultStyle);

            int patternLength = pattern.length();
            for (int startPos : matchPositions) {
                doc.setCharacterAttributes(startPos, patternLength, highlightStyle, false);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}






