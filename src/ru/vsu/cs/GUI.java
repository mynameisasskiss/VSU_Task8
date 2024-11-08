package ru.vsu.cs;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    private JButton inputButton;
    private JTable table2;
    private JTextField textField1;
    private JPanel pane;

    public GUI() {
        super("GUI");
        setContentPane(pane);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
    }

    public static void main(String[] args) {
        GUI v = new GUI();
        v.setSize(400, 400);
        v.setTitle("Дружелюбная программа");
    }
}
