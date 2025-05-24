/**
 * Class Name: Chess
 * Author: Ori Nave
 * Date: 08/03/2022
 * Description: The class will calculate the threats of a chess tool on other chess tool by a given position(can be bishop, rook or a knight only)
 */
import java.util.Scanner;
public class Chess
{
    public static void main(String[]args)
    {
        final int MIN_VAL = 1, MAX_VAL = 8; //the minimum and maximum that chessboard square can get

        Scanner scan = new Scanner (System.in);
        System.out.println("Please enter the type"+
                " of the first chessman");
        char first = scan.next().charAt(0);
        System.out.println ("Please enter the number of row");
        int row1 = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col1 = scan.nextInt();
        System.out.println("Please enter the type"+
                " of the second chessman");
        char second = scan.next().charAt(0);
        System.out.println ("Please enter the number of row");
        int row2 = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col2 = scan.nextInt();

        final boolean BISHOP_THREATS = (row2 - row1 == col2 - col1) || (row1 - row2 == col2 - col1);
        final boolean ROOK_THREATS = (row1 == row2) || (col1 == col2);
        final boolean KNIGHT_THREATS = (row1 == row2+1 && col1 == col2+2) || (row1 == row2+2 && col1 == col2+1) || (row1 == row2+2 && col1 == col2-1) || (row1 == row2+1 && col1 == col2-2) ||
                (row1 == row2-1 && col1 == col2+2) || (row1 == row2-2 && col1 == col2+1) || (row1 == row2-2 && col1 == col2-1) || (row1 == row2-1 && col1 == col2-2);

        if(first == second)
            System.out.println("Chessmen should be different from each other");
        else if((row1 < MIN_VAL || row1 > MAX_VAL) || (col1 < MIN_VAL || col1 > MAX_VAL) || (row2 < MIN_VAL || row2 > MAX_VAL) || (col2 < MIN_VAL || col2 > MAX_VAL))
            System.out.println("Position is not legal");
        else if(row1 == row2 && col1 == col2)
            System.out.println("Chessmen positions should not be identical");

        else if((first == 'b' && second == 'r') || (first == 'r' && second == 'b')) // testing bishop and rook
        {
            if(BISHOP_THREATS)
                System.out.println("bishop threats rook");
            else if(ROOK_THREATS)
                System.out.println("rook threats bishop");
            else
                System.out.println("no threat");
        }

        else if((first == 'r' && second == 'k') || (first == 'k' && second == 'r')) // testing rook and knight
        {
            if(ROOK_THREATS)
                System.out.println("rook threats knight");
            else if(KNIGHT_THREATS)
                System.out.println("knight threats rook");
            else
                System.out.println("no threat");
        }

        else if((first == 'b' && second == 'k') || (first =='k' && second == 'b')) // testing bishop and knight
        {
            if(BISHOP_THREATS)
                System.out.println("bishop threats knight");
            else if(KNIGHT_THREATS)
                System.out.println("knight threats bishop");
            else
                System.out.println("no threat");
        }

    }
}
