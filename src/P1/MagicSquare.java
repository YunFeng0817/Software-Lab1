package P1;

import java.io.*;

public class MagicSquare {

    public static void main(String[] args) {
        // TODO
        System.out.println(isLegalMagicSquare("./src/P1/txt/1.txt"));
        System.out.println(isLegalMagicSquare("./src/P1/txt/2.txt"));
        System.out.println(isLegalMagicSquare("./src/P1/txt/3.txt"));
        System.out.println(isLegalMagicSquare("./src/P1/txt/4.txt"));
        System.out.println(isLegalMagicSquare("./src/P1/txt/5.txt"));
    }

    private static boolean isLegalMagicSquare(String fileName) {
        // TODO
        final int LENGTH = 1000;
        int[][] MagicSquare = new int[LENGTH][LENGTH];
        int[] ColumnSum = new int[LENGTH], RowSum = new int[LENGTH];
        int DiagonalPositive = 0, DiagonalNegative = 0;
        int count = 0;
        try {
            BufferedReader content = new BufferedReader(new FileReader(fileName));
            String line = content.readLine();
            while (line != null) {
                String[] ParseNum = line.split("\t");
                for (int i = 0; i < ParseNum.length; i++) {
                    MagicSquare[count][i] = Integer.parseInt(ParseNum[i]);
                    RowSum[count] += MagicSquare[count][i];
                    ColumnSum[i] += MagicSquare[count][i];
                    if (i == count) {
                        DiagonalNegative += MagicSquare[count][i];
                    }
                    if (i + count +1 == ParseNum.length) {
                        DiagonalPositive += MagicSquare[count][i];
                    }
                }
                count++;
                line = content.readLine();
            }
            boolean flag = true;
            for (int i = 0; i < count - 1; i++) {
                if (ColumnSum[i] != ColumnSum[i + 1]) {
                    flag = false;
                    break;
                }
                if (RowSum[i] != RowSum[i + 1]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (RowSum[0] == DiagonalPositive) {
                    if (RowSum[0] == DiagonalNegative)
                        return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static void generateMagicSquare(int n) {

    }

}
