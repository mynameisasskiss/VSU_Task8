package ru.vsu.cs;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GUI extends JFrame {
    private JButton inputButton;
    private JPanel pane;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton saveAsButton;
    private JTextField outputFileField;
    private JTextField errorField;

    public GUI() {
        super("Дружелюбная программа");
        setContentPane(pane);
        setVisible(true);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        setJMenuBar(menuBar);
        //outputFileField.setVisible(false);
        //errorField.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);


        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String data[] = textArea1.getText().split("\n");
                    int[][] intArr = ArrayConverter.convertToIntArray(data);
                    int[][] outputData = Evaluate.getFriendlyCount(intArr);
                    for (int i = 0; i < outputData.length; i++) {
                        textArea2.append((Arrays.toString(outputData[i]).replace("[", "").replace("]", "").replace(",", "") + "\n"));
                    }
                    inputButton.setEnabled(false);
                }
        });
        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputFileField.setVisible(true);
                if (textArea2.getText() != "") {
                    System.out.println(textArea2.getText());
                    try {
                        FileIO.writeStringToFile(outputFileField.getText(), textArea2.getText());
                    } catch (Exception ex) {
                        errorField.setVisible(true);
                        errorField.setText("Ошибка при записи файла");
                    }
                }
            }
        });
    }

    private JMenu createFileMenu() {
        // Создание выпадающего меню
        JMenu tableMenu = new JMenu("Таблица");

        // Пункт меню "Сохранить"
        JMenuItem saveItem = new JMenuItem("Сохранить в файл");
        // Добавление в меню пункта save
        tableMenu.add(saveItem);
        // callback (метод который будет вызван при нажатии кнопки save)
        saveItem.addActionListener(arg0 -> {
            String filePath = chooseFile();
            if (filePath != null) {
                int[][] matrix = ArrayConverter.convertToIntArray(textArea2.getText().split("\n"));
                try {
                    FileIO.writeIntMatrixToFile(filePath, matrix);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        // Пункт меню "Открыть"
        JMenuItem openItem = new JMenuItem("Открыть из файла");
        tableMenu.add(openItem);
        openItem.addActionListener(arg0 -> {
            String filePath = chooseFile();
            if (filePath != null) {
                try {
                    int[][] matrix = FileIO.readIntMatrixFromFile(filePath);
                    for (int i = 0; i < matrix.length; i++) {
                        textArea1.append((Arrays.toString(matrix[i]).replace("[", "").replace("]", "").replace(",", "") + "\n"));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Добавление разделителя
        tableMenu.addSeparator();

        // Пункт меню "Заполнить нулями"
        JMenuItem fill0Item = new JMenuItem("Заполнить нулями");
        tableMenu.add(fill0Item);
        fill0Item.addActionListener(arg0 -> {
            // TODO
        });

        return tableMenu;
    }

    public String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    public static void main(String[] args) {
        GUI v = new GUI();
    }
}
