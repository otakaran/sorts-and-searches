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
        int [] rip = new int [100];
        String inputFile = "";
        switch (selection) 
        {
            case 1:  inputFile = "input/input1.txt";
            break;
            case 2:  inputFile = "input/input2.txt";
            break;
            case 3:  inputFile = "input/input3.txt";
            break;
            case 4:  inputFile = "input/input4.txt";
            break;
            default: System.out.println("Bad input.");
            break;
        }
        System.out.println(new File(inputFile).getAbsoluteFile());
        try
        {
            Scanner scan = new Scanner(new File(inputFile));
            int [] data = new int [100];
            int i = 0;
            while(scan.hasNextInt())
            {
                data[i++] = scan.nextInt();
            }
            return data;
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("The file was not found.");
        }
        catch (IOException exception)
        {
            System.out.println(exception);
        }

        return rip;
    }

    /**
     * An example of a method - replace this comment with your own
     */
    public static int[] insertSort(int[] input)
    {
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
    public static int selectionSort()
    {

        return 0;
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
        // All the sorting algorithm that the user identified previously
        switch (algorithmSelection) 
        {
            case 1:  insertSort(data);
            break;
            case 2:  mergeSort();
            break;
            case 3:  selectionSort();
            break;
            default: System.out.println("Bad input.");
            break;
        }
    }
}
