package ru.vsu.cs;

import java.io.IOException;

public class NoGUI {
    public static void main(String[] args) {
        String fileIn = args[0];
        String fileOut = args[1];
        try {
            int[][] data = FileIO.readIntMatrixFromFile(fileIn);
            FileIO.writeIntMatrixToFile(fileOut, Evaluate.getFriendlyCount(data));
        }
        catch (IOException e){
            System.out.println("Ошибка при чтении файла. Проверьте целостность файлов");
        }
    }
}
