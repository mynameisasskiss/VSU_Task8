package ru.vsu.cs;

import java.util.HashSet;
import java.util.Set;

public class Evaluate {
    public static int[][] getFriendlyCount(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) { // Перебор всей матрицы
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) { // Если не проверили
                    Set<int[]> friendlyGroup = new HashSet<>(); // Создаем хэшсет
                    int friendlyCount = dfs(matrix, i, j, matrix[i][j], visited, friendlyGroup); // Вывод кол-ва дружественных клеток
                    for (int[] cell : friendlyGroup) { // Перебор набора
                        result[cell[0]][cell[1]] = friendlyCount-1; // Записываем в выходную матрицу
                    }
                }
            }
        }

        return result;
    }

    private static int dfs(int[][] matrix, int x, int y, int value, boolean[][] visited, Set<int[]> friendlyGroup) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (x < 0 || x >= rows || y < 0 || y >= cols || visited[x][y] || matrix[x][y] != value) {
            return 0;
        }

        visited[x][y] = true; // Помечаем как посещенную
        friendlyGroup.add(new int[]{x, y});

        int count = 1;
        // Поиск в пограничных клетках
        count += dfs(matrix, x + 1, y, value, visited, friendlyGroup);
        count += dfs(matrix, x - 1, y, value, visited, friendlyGroup);
        count += dfs(matrix, x, y + 1, value, visited, friendlyGroup);
        count += dfs(matrix, x, y - 1, value, visited, friendlyGroup);

        return count;
    }
}
