package ru.vsu.cs;

public class ArrayConverter {

    public static int[][] convertToIntArray(String[] stringArray) {
        if (stringArray == null || stringArray.length == 0) {
            return new int[0][0];
        }

        int[][] result = new int[stringArray.length][];

        for (int i = 0; i < stringArray.length; i++) {
            String[] numbers = stringArray[i].trim().split("\\s+");
            result[i] = new int[numbers.length];

            for (int j = 0; j < numbers.length; j++) {
                result[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        return result;
    }
}