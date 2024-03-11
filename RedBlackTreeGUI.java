package sdaproiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedBlackTreeGUI {
    private RedBlackTree tree = new RedBlackTree();
    private JPanel panel;
    private JTextField addField, deleteField;

    public RedBlackTreeGUI() {
        panel = new JPanel(new BorderLayout());

        JPanel controlPanel = new JPanel();
        addField = new JTextField(10);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int data = Integer.parseInt(addField.getText());
                tree.insert(data);
                panel.repaint();
            }
        });

        deleteField = new JTextField(10);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int data = Integer.parseInt(deleteField.getText());
                tree.deleteNode(data);
                panel.repaint();
            }
        });

        controlPanel.add(new JLabel("Add:"));
        controlPanel.add(addField);
        controlPanel.add(addButton);
        controlPanel.add(new JLabel("Delete:"));
        controlPanel.add(deleteField);
        controlPanel.add(deleteButton);

        JPanel treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
            }
        };

        panel.add(treePanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);
    }

    private void drawTree(Graphics g, Node node, int x, int y, int horizGap) {
        if (node == null || node == tree.getNIL()) {
            return;
        }

        g.setColor(Color.WHITE);

        if (node.left != tree.getNIL()) {
            g.drawLine(x, y, x - horizGap, y + 50);
        }
        if (node.right != tree.getNIL()) {
            g.drawLine(x, y, x + horizGap, y + 50);
        }

        g.setColor(node.color == 1 ? Color.BLACK : Color.RED);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(node.data), x - 5, y + 5);

        drawTree(g, node.left, x - horizGap, y + 50, horizGap / 2);
        drawTree(g, node.right, x + horizGap, y + 50, horizGap / 2);
    }


    public JPanel getPanel() {
        return panel;
    }
}

