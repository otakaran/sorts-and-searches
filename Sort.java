// Import statements
import java.util.Scanner;
import java.io.*;
import java.io.File;
/**
 * Sort contain a number of methods that sort a file of comma seperated integers into correct
 * using different algorithms.
 * 
 * @author Otakar Andrysek
 * @version .1
 * @date 4/1/2017
 */
public class Sort
{
    // Instance variables
    private int x;

    /**
     * Constructor for objects of class Sort
     */
    public Sort()
    {
        // Initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static int selectAlgorithm()
    {
        int selection = 0;
        boolean noSelection = true;
        // Begin looping main menu
        // Loop until we receive valid user input
        while (noSelection)
        {
            // Print menu
            System.out.println("#######################");
            System.out.println("#  1. Insertion Sort  #");
            System.out.println("#  2. Merge Sort      #");
            System.out.println("#  3. Selection Sort  #");
            System.out.println("#######################\n");
            System.out.println("Type the integer [1-3] that represents your selection followed by {RETURN}.");
            // Scan for user input
            Scanner scan = new Scanner(System.in);
            selection = Integer.valueOf(scan.nextLine());
            scan.close();
            // Check for valid input, if input is valid break the loop, if invalid let the user know and scan again
            if (selection == 1 || selection == 2 || selection == 3)
            {
                noSelection = false;
            }
            else
            {
                System.out.println("INVALID SELECTION");
            }
        }
        return selection;
    }

    public static int selectFile()
    {
        int selection = 0;
        boolean noSelection = true;
        // Begin looping main menu
        // Loop until we receive valid user input
        while (noSelection)
        {
            // Print menu
            System.out.println("########################");
            System.out.println("#  1. Very Short File  #");
            System.out.println("#  2. Short File       #");
            System.out.println("#  3. Long File        #");
            System.out.println("#  4. Very Long File   #");
            System.out.println("########################\n");
            System.out.println("Type the integer [1-4] that represents your selection followed by {RETURN}.");
            // Scan for user input
            Scanner scan = new Scanner(System.in);
            selection = Integer.valueOf(scan.nextLine());
            scan.close();
            // Check for valid input, if input is valid break the loop, if invalid let the user know and scan again
            if (selection == 1 || selection == 2 || selection == 3 || selection == 4)
            {
                noSelection = false;
            }
            else
            {
                System.out.println("INVALID SELECTION");
            }
        }
        return selection;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static int[] scanFile(int selection)
    {
        int [] data = new int [10000];
        int i = 0;
        int length = 0;
        String inputFile = "";
        switch (selection) 
        {
            case 1:  inputFile = "input/input1.txt";
            length = 1000;
            break;
            case 2:  inputFile = "input/input2.txt";
            length = 10000;
            break;
            case 3:  inputFile = "input/input3.txt";
            length = 100000;
            break;
            case 4:  inputFile = "input/input4.txt";
            length = 1000000;
            break;
            default: System.out.println("Bad input.");
            break;
        }
        System.out.println();
        try
        {
            Scanner scan = new Scanner(new File(inputFile)).useDelimiter(",");
            int[] array = new int[length];
            while(scan.hasNextInt())
            {
                array[i++] = scan.nextInt();
                //System.out.println(array[i-1]);
            }
            return array;
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("The file was not found.");
        }
        return null;
    }

    /**
     * An example of a method - replace this comment with your own
     */
    public static void getValues(int[] input)
    {
        System.out.print("\nThe 10 lowest values: ");
        for (int i = 0; i < 10; i++)
        {
            System.out.print(input[i]);
            System.out.print(", ");
        }
        System.out.print("\nThe 10 highest values: ");
        for (int i = 1; i < 11; i++)
        {
            System.out.print(input[input.length - i]);
            System.out.print(", ");
        } 
    }

    /**
     * An example of a method - replace this comment with your own
     */
    public static int[] insertSort(int[] input)
    {
        long startTime = System.nanoTime();
        int temp;
        for (int i = 1; i < input.length; i++) 
        {
            for(int j = i ; j > 0 ; j--)
            {
                if(input[j] < input[j-1])
                {
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.print("Sort time (ns): ");
        System.out.print(duration);

        return input;
    }

    /**
     * An example of a method - replace this comment with your own
     */
    public static int mergeSort()
    {

        return 0;
    }

    /**
     * An example of a method - replace this comment with your own
     */
    public static int[] selectionSort(int[] input)
    {
        long startTime = System.nanoTime();
        for (int i = 0; i < input.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < input.length; j++)
            {
                if (input[j] < input[index]) 
                {
                    index = j;
                }
            }

            int smallerNumber = input[index];  
            input[index] = input[i];
            input[i] = smallerNumber;
        }
        // Calculate execution time
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.print("Sort time (ns): ");
        System.out.print(duration);
        
        // Return proccessed array
        return input;
    }

    /**
     * Main method that calls sorting algorithms
     * 
     * @param    selection    User selected sorting algorithm
     */
    public static void main(String args[])
    {
        // Get the selection from the menu
        int algorithmSelection = selectAlgorithm();
        // Get the selection for file length
        int fileSelection = selectFile();
        // Scan selected file
        int[] data = scanFile(fileSelection);
        // Call the sorting algorithm that the user identified previously
        switch (algorithmSelection) 
        {
            case 1:  int[] result = insertSort(data);
            getValues(result);
            break;
            case 2:  //int[] result2 = mergeSort();
            //getValues(result2);
            break;
            case 3:  int[] result3 = selectionSort(data);
            getValues(result3);
            break;
            default: System.out.println("Bad input.");
            break;
        }
    }
}
