package P1;

import java.io.*;

public class MagicSquare {

    public static void main(String[] args) {
        // TODO
        System.out.println("hello world");
        isLegalMagicSquare("./src/P1/txt/1.txt");
    }

    private static boolean isLegalMagicSquare(String fileName){
        // TODO
        try{
            BufferedReader content = new BufferedReader(new FileReader(fileName));
            String line = content.readLine();
            while(line!=null){
                String[] parseNum = line.split(" ");
                System.out.println();
                line = content.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    static void generateMagicSquare(int n){

    }

}
