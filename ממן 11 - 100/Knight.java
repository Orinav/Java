/**
 * Class Name: Knight
 * Author: Ori Nave
 * Date: 08/03/2022
 * Description: The class will calculate the possible moves of a Knight in a chess game from a given position.
 */
import java.util.Scanner;
public class Knight
{
    public static void main(String[]args)
    {
        final int MIN_VAL = 1, MAX_VAL = 8; //the minimum and maximum that chessboard square can get

        Scanner scan = new Scanner (System.in);
        System.out.println ("This program reads two integers which " +
                "represent the knight's location on the chess board: ");
        System.out.println ("Please enter the number of row");
        int row = scan.nextInt();
        System.out.println ("Please enter the number of column");
        int col = scan.nextInt();

        if((row < MIN_VAL || row > MAX_VAL) || (col < MIN_VAL || col > MAX_VAL))
            System.out.println ("input is illegal");
        else
        {
            System.out.println ("Moves: ");
            if(row > MIN_VAL+1 && col < MAX_VAL) //checking if the knight can go 2 steps up, 1 step right
                System.out.println ((row-2) + " " + (col+1));
            if(row > MIN_VAL+1 && col > MIN_VAL) //checking if the knight can go 2 steps up, 1 step left
                System.out.println ((row-2) + " " + (col-1));
            if(row > MIN_VAL && col > MIN_VAL+1) //checking if the knight can go 1 step up, 2 steps left
                System.out.println ((row-1) + " " + (col-2));
            if(row > MIN_VAL && col < MAX_VAL-1) //checking if the knight can go 1 step up, 2 steps right
                System.out.println ((row-1) + " " + (col+2));
            if(row < MAX_VAL && col < MAX_VAL-1) //checking if the knight can go 1 step down, 2 steps right
                System.out.println ((row+1) + " " + (col+2));
            if(row < MAX_VAL && col > MIN_VAL+1) //checking if the knight can go 1 step down, 2 steps left
                System.out.println ((row+1) + " " + (col-2));
            if(row < MAX_VAL-1 && col > MIN_VAL) //checking if the knight can go 2 steps  down, 1 step left
                System.out.println ((row+2) + " " + (col-1));
            if(row < MAX_VAL-1 && col < MAX_VAL) //checking if the knight can go 2 steps down, 1 step right
                System.out.println ((row+2) + " " + (col+1));
        }
    }
}
