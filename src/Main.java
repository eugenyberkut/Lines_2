import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Yevhen on 07.10.2015.
 */
public class Main {

    int[][] a;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int startRow=0, startColumn=0, finishRow=0, finishColumn=0;
            int n = Integer.parseInt(reader.readLine());
            char[][] p = new char[n][];
            for (int i = 0; i < n; i++) {
                p[i] = reader.readLine().toCharArray();
            }
            a = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(p[i][j]=='O') a[i][j] = 100000; else
                        if (p[i][j]=='@') {
                            a[i][j] = 1;
                            startRow = i;
                            startColumn = j;
                        } else if (p[i][j]=='X') {
                            finishRow = i;
                            finishColumn = j;
                        }
                }
            }

            findWay(startRow, startColumn, finishRow, finishColumn);

            //printA();
            if (a[finishRow][finishColumn]==0) {
                System.out.println("N");
            } else {
                System.out.println("Y");
                int k = a[finishRow][finishColumn];
                int row = finishRow;
                int column = finishColumn;
                while (k>1) {
                    p[row][column] = '+';
                    k--;
                    if (row>0 && a[row-1][column]==k) {
                        row--;
                    } else if (row<n-1 && a[row+1][column]==k){
                        row++;
                    } else if (column>0 && a[row][column-1]==k) {
                        column--;
                    } else if (column<n-1 && a[row][column+1]==k) {
                        column++;
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(p[i][j]);
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printA() {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j]==100000) System.out.printf(" xx");
                else System.out.printf("%3d", a[i][j]);
            }
            System.out.println();
        }
    }

    void findWay(int startRow, int startColumn, int finishRow, int finishColumn) {
        Deque<Cell> deque = new ArrayDeque<>();
        deque.addLast(new Cell(startRow, startColumn, 1));
        while (!deque.isEmpty()) {
            Cell cell = deque.removeFirst();
            int i = cell.row;
            int j = cell.column;
            int k = cell.value;
            if (i>0 && a[i-1][j]==0) {
                a[i-1][j] = k+1;
                deque.addLast(new Cell(i-1, j, k+1));
            }
            if (j>0 && a[i][j-1]==0) {
                a[i][j-1] = k+1;
                deque.addLast(new Cell(i, j-1, k+1));
            }
            if (i<a.length-1 && a[i+1][j]==0) {
                a[i+1][j] = k+1;
                deque.addLast(new Cell(i+1, j, k+1));
            }
            if (j<a[i].length-1 && a[i][j+1]==0) {
                a[i][j+1] = k+1;
                deque.addLast(new Cell(i, j+1, k+1));
            }
        }
    }

    class Cell {
        int row;
        int column;
        int value;

        public Cell(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }
}
