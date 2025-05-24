//The answer for Question 1 section A are the statements 3,5,6
/**
 * The class Ex14 contains methods regards to Complexity and Recursion.
 * @author Ori Nave
 * @version 27/05/2022
 */
public class Ex14
{

    /**
     * checks if a specific value exists in the array, the algorithm starts from the upper right corner and checks whether the value
     * is smaller/higher than the current number, if its smaller we'll "remove" one column, if it's higher we'll "remove" one row.
     * in that way we're certain that at max checks we'll reach n checks - O(n) time complexity.
     * we defined 3 variables(n, i, j) that none of them is an array - O(1) space complexity.
     * @param m 2d array
     * @param val desired value to find in the array
     * @return true if the value is in the array, false if not.
     */
    public static boolean findValWhat (int [][] m, int val)
    {
        int n = m.length;
        if (val < m[0][0] || val > m[n-1][n-1])
            return false;

        int i = 0;
        int j = n-1;

        while (m[i][j] != val)
        {
            if (val > m[i][j])
            {
                if (i+1 >= n) //if we exceed from the array limits
                    return false;
                else
                    i++;
            }

            else if (val < m[i][j])
            {
                if (j-1 < 0) //if we exceed from the array limits
                    return false;
                else
                    j--;
            }
        }
        return true;
    }

    /**
     * checks if a specific value is in the array.
     * we have 4 for loops that runs until n, none of the loops are nested - time complexity of O(n)
     * we defined 5 variables(n, row, i, k, j) that none of them is an array - space complexity of O(1)
     * @param m 2d array
     * @param val desired value to find in the array
     * @return true if the value found the array, false if not
     */
    public static boolean findValTest (int [][] m, int val)
    {
        int n = m.length;
        int row = 0;

        for (int i = 0; i < n ; i++) //find the first row index that the first number in that row higher than val
        {
            if (m[i][0] > val)
            {
                row = i;
                break;
            }
        }

        if (row == 0) //if we didn't found any number of the start of a row that bigger than val - in that case we look only on the last row.
        {
            for (int k = 0; k < n; k++)
            {
                if (m[n-1][k] == val)
                    return true;
            }
        }

        for (int j = 0; j < n; j++) //trying to find val in the row we found that the first number in the row is higher than val.
        {
            if (m[row][j] == val)
                return true;
        }

        if (row > 0) //to not exceed from the array limits
        {
            for (int j = 0; j < n; j++) //trying to find val in the row before that row we found earlier.
            {
                if (m[row-1][j] == val)
                    return true;
            }
        }
        return false;
    }

    /**
     * the method will look into an array and will check how many sub arrays exists that arranged in ascending order.
     * we have one for loop that runs until n-1 -> time complexity of O(n).
     * we defined 4 variables(n, counter, sequence, i), none of them is an array - space complexity of O(1).
     * @param a 1d array
     * @return the number of sub arrays that are arranged in an ascending order.
     */
    public static int strictlyIncreasing (int[] a)
    {
        int n = a.length;
        if (n < 2) //if the array composed from less than 2 numbers
            return 0;

        int counter = 0;
        int sequence = 0;

        for (int i = 0; i < n-1; i++)
        {
            if (a[i] < a[i+1])
            {
                counter += 1 + sequence;
                sequence++;
            }
            else
                sequence = 0;
        }

        return counter;
    }


    /**
     * the method will return a number that represents the length of the longest flat sequence series.
     * flat sequence series in this case is a sub array which all the numbers in it are the same number or
     * the numbers are consecutive numbers(2 numbers at most).
     * @param arr a 1d array contains only integers.
     * @return a number that represents the length of the longest flat sequence series.
     */
    public static int longestFlatSequence(int[] arr)
    {
        if (arr.length == 0) //empty series
            return 0;
        else if (arr.length == 1) //series with 1 number
            return 1;
        return longestFlatSequence(arr, 0, 0, 0);
    }

    private static int longestFlatSequence(int [] arr, int i, int curr_counter, int max_counter)
    {
        if (i == arr.length)
            return 0;
        curr_counter = Math.max(curr_counter, lengthFlat(arr, i));
        max_counter = Math.max(curr_counter, longestFlatSequence(arr, i+1, curr_counter, max_counter));
        return max_counter;
    }

    private static int lengthFlat (int[] arr, int index)
    {
        return lengthFlat(arr, index, arr[index], arr[index], 0);
    }

    private static int lengthFlat (int[] arr, int index, int num1, int num2, int score)
    {
        if (index == arr.length)
            return 0;
        if ((num2 != num1) && (num2 - arr[index] != score) && (num2 != arr[index]))//if we changed num2 and it not equals to the score or to the number in the current array index.
            return 0;
        if (arr[index] == num1)
            return 1+ lengthFlat(arr, index+1, num1, num2, score);
        else if (arr[index] - num1 == 1)
        {
            score = 1;
            num2 = arr[index];
            return 1 + lengthFlat(arr, index + 1, num1, arr[index], score);
        }
        else if (arr[index] - num1 == -1)
        {
            score = -1;
            num2 = arr[index];
            return 1 + lengthFlat(arr, index+1, num1, arr[index], score);
        }
        else
            return 0;
    }


    /**
     * the method will get a 2d array that contains the numbers -1, 0, 1. the method will serve as a root that starts from [0][0] which can only go through
     * numbers that are 0 or 1(if it goes through 0 we will add 0 points to the sum of the route, if it goes through 1 we'll add 1 point to the route).
     * if the array row index is even it can only go right/down, if it's odd it can only go left/down.
     * eventually we'll return the route's sum that was the highest.
     * @param mat 2d array that contains only the numbers -1, 0, 1.
     * @return the highest points that possible in the route.
     */
    public static int findMaximum(int[][] mat)
    {
        if (mat[0][0] == -1)
            return -1;
        return findMaximum(mat, 0, 0);
    }
    private static int findMaximum(int[][] mat, int i, int j)
    {
        if (!canProceed(mat, i, j)) //can't go any direction
            return mat[i][j];

        int x,y;
        if (isTwoPaths(mat, i , j))
        {
            if (canRight(mat, i, j))
            {
                x = i;
                y = j+1;
            }
            else //canLeft
            {
                x = i;
                y = j-1;
            }
            return Math.max(mat[i][j] + findMaximum(mat, x, y), mat[i][j] + findMaximum(mat, i+1, j));
        }
        else if (canDown(mat, i, j))
                return mat[i][j] + findMaximum(mat, i+1, j);
        else
        {
            if (canRight(mat, i, j))
            {
                x = i;
                y = j+1;
            }
            else //canLeft
            {
                x = i;
                y = j-1;
            }
            return mat[i][j] + findMaximum(mat, x, y);
        }
    }

    private static boolean canDown(int[][] mat, int i, int j)
    {
        return ((i+1 < mat.length) && (mat[i+1][j] != -1));
    }

    private static boolean canRight(int[][] mat, int i, int j)
    {
        return ((i%2==0) && (j+1 < mat[i].length) && (mat[i][j+1] != -1));
    }
    private static boolean canLeft(int[][] mat, int i, int j)
    {
        return ((i%2 != 0) && (j-1 >= 0) && (mat[i][j-1] != -1));
    }

    private static boolean canProceed(int[][]mat, int i, int j)
    {
        return canRight(mat, i, j) || canLeft(mat, i, j) || canDown(mat, i , j);
    }

    private static boolean isTwoPaths(int[][]mat,  int i, int j)
    {
        return (canRight(mat, i, j) && canDown(mat, i, j)) || (canLeft(mat, i, j) && canDown(mat, i, j));
    }


}
