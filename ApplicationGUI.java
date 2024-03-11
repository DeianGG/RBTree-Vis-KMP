package sdaproiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private RedBlackTreeGUI treeGUI;
    private KMPMatcherGUI kmpGUI;

    public ApplicationGUI() {
        frame = new JFrame("Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem rbTreeItem = new JMenuItem("RedBlackTree Visualizer");
        JMenuItem kmpItem = new JMenuItem("KMP Matcher");

        menu.add(rbTreeItem);
        menu.add(kmpItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        treeGUI = new RedBlackTreeGUI();
        kmpGUI = new KMPMatcherGUI();
        mainPanel = new JPanel(new CardLayout());

        mainPanel.add(treeGUI.getPanel(), "Tree");
        mainPanel.add(kmpGUI.getPanel(), "KMP");

        frame.add(mainPanel);
        CardLayout cl = (CardLayout) mainPanel.getLayout();

        rbTreeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, "Tree");
            }
        });
        kmpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainPanel, "KMP");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
    	new ApplicationGUI();
    }
}


