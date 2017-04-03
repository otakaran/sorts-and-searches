// Import statements
import java.util.Scanner;
import java.io.*;

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
    /**
     * Algorithm selector menu
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
            System.out.println("#########################");
            System.out.println("#  1. Insertion Sort    #");
            System.out.println("#  2. Merge Sort        #");
            System.out.println("#  3. Selection Sort    #");
            System.out.println("#  4. Bubble Sort       #");
            System.out.println("#  5. All of the Above  #");
            System.out.println("#  6. Exit              #");
            System.out.println("#########################\n");
            System.out.println("Type the integer [1-6] that represents your selection followed by {RETURN}.");
            // Scan for user input
            Scanner scan = new Scanner(System.in);
            selection = Integer.valueOf(scan.nextLine());
            scan.close();
            // Check for valid input, if input is valid break the loop, if invalid let the user know and scan again
            if (selection == 1 || selection == 2 || selection == 3 || selection == 4 || selection == 5 || selection == 6)
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
     * File selector menu
     */
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
     * File scanner method
     * 
     * @param  selection   a sample parameter for a method
     * @return     data from selected file
     */
    public static int[] scanFile(int selection)
    {
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
        try
        {
            Scanner scan = new Scanner(new File(inputFile)).useDelimiter(",");
            int[] array = new int[length];
            while(scan.hasNextInt())
            {
                array[i++] = scan.nextInt();
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
     * Get 10 smallest and largest values
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
        System.out.println("\n");
    }

    /**
     * Insertion sort
     */
    public static int[] insertSort(int[] input)
    {
        long startTime = System.currentTimeMillis();
        int temp;
        for (int i = 1; i < input.length; i++) 
        {
            for(int j = i ; j > 0 ; j--)
            {
                if(input[j] < input[j-1])
                {
                    // Insert the new value
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        // Calculate execution time
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.print(duration);

        return input;
    }

    /**
     * Merge sort
     */
    public static int[] mergeSort(int[] input) {
        if (input.length <= 1) 
        {
            return input;
        }

        // Split the array in half
        int[] first = new int[input.length / 2];
        int[] second = new int[input.length - first.length];
        System.arraycopy(input, 0, first, 0, first.length);
        System.arraycopy(input, first.length, second, 0, second.length);

        // Sort each half [RECURSION!]
        mergeSort(first);
        mergeSort(second);

        // Merge the halves together, overwriting the original array
        merge(first, second, input);
        return input;
    }

    /**
     * Merge sort helper
     */
    public static void merge(int[] first, int[] second, int [] result) {
        // Next element to consider in the first array
        int firstElement = 0;
        // Next element to consider in the second array
        int secondElement = 0;
        int j = 0;
        // As long as neither first nor second is past the end, move the smaller element into the result.
        while (firstElement < first.length && secondElement < second.length) 
        {
            if (first[firstElement] < second[secondElement]) 
            {
                result[j] = first[firstElement];
                firstElement++;
            } 
            else 
            {
                result[j] = second[secondElement];
                secondElement++;
            }
            j++;
        }
        // Copy The remiander
        System.arraycopy(first, firstElement, result, j, first.length - firstElement);
        System.arraycopy(second, secondElement, result, j, second.length - secondElement);
    }

    /**
     * Selection Sort
     */
    public static int[] selectionSort(int[] input)
    {
        long startTime = System.currentTimeMillis();
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
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);

        System.out.print(duration);

        // Return proccessed array
        return input;
    }

    /**
     * Bubble sort
     */
    public static int[] bubbleSort(int[] input)
    {
        long startTime = System.currentTimeMillis();
        int j;
        boolean swap = true;   // set flag to true to begin first pass
        int temp;   //holding variable

        while (swap)
        {
            swap = false;    //set flag to false awaiting a possible swap
            for(j = 0; j < input.length - 1; j++)
            {
                if (input[j] < input[j+1])
                {
                    temp = input[j];                
                    // Swap elements
                    input[j] = input[j+1];
                    input[j+1] = temp;
                    // A swap occurred 
                    swap = true;              
                } 
            } 
        } 
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.print(duration);
        return input;
    } 

    /**
     * Main method that calls sorting algorithms
     * 
     * @param    selection    User selected sorting algorithm
     */
    public static void main(String args[])
    {
        boolean sorting = true;
        int fileSelection = 1;
        while (sorting)
        {
            // Get the selection from the menu
            int algorithmSelection = selectAlgorithm();
            // Get the selection for file length
            if (algorithmSelection < 5)
            {
                fileSelection = selectFile();
            }
            // Scan selected file
            int[] data = scanFile(fileSelection);
            // Call the sorting algorithm that the user identified previously
            switch (algorithmSelection) 
            {
                case 1:  System.out.print("Sort time (ns): ");
                int[] result = insertSort(data);
                getValues(result);
                break;
                case 2:  long startTime = System.currentTimeMillis();
                int[] result2 = mergeSort(data);
                long endTime = System.currentTimeMillis();
                long duration = (endTime - startTime);
                System.out.print("\nSort time (ns): ");
                System.out.print(duration);
                getValues(result2);
                break;
                case 3:  System.out.print("Sort time (ns): ");
                int[] result3 = selectionSort(data);
                getValues(result3);
                break;
                case 4:  System.out.print("Sort time (ns): ");
                int[] result4 = bubbleSort(data);
                getValues(result4);
                break;
                
                // This nasty code block makes a table with the sorting times for all files
                case 5:  
                System.out.println("\nTime in (MS)       File 1       File 2       File 3");
                
                // Get all files
                int[] data1 = scanFile(1);
                int[] data2 = scanFile(2);
                int[] data3 = scanFile(3);
                int[] data4 = scanFile(4);
                
                // Insert sort
                System.out.print("Insert             ");
                insertSort(data1);
                System.out.print("            ");
                insertSort(data2);
                System.out.print("           ");
                insertSort(data3);
                System.out.print("         ");
                //insertSort(data4);
                System.out.println();
                
                // Merge sort
                System.out.print("Merge              ");
                long startTime1 = System.currentTimeMillis();
                mergeSort(data1);
                long endTime1 = System.currentTimeMillis();
                long duration1 = (endTime1 - startTime1);
                System.out.print(duration1);
                System.out.print("            ");
                long startTime2 = System.currentTimeMillis();
                mergeSort(data2);
                long endTime2 = System.currentTimeMillis();
                long duration2 = (endTime2 - startTime2);
                System.out.print(duration2);
                System.out.print("            ");
                long startTime3 = System.currentTimeMillis();
                mergeSort(data3);
                long endTime3 = System.currentTimeMillis();
                long duration3 = (endTime3 - startTime3);
                System.out.print(duration3);
                System.out.print("           ");
                //long startTime4 = System.currentTimeMillis();
                //mergeSort(data4);
                //long endTime4 = System.currentTimeMillis();
                //long duration4 = (endTime4 - startTime4);
                //System.out.print(duration4);
                System.out.println();
                
                // Selection sort
                System.out.print("Selection          ");
                selectionSort(data1);
                System.out.print("            ");
                selectionSort(data2);
                System.out.print("           ");
                selectionSort(data3);
                System.out.print("         ");
                //selectionSort(data4);
                System.out.println();
                
                // Bubble sort
                System.out.print("Bubble             ");
                selectionSort(data1);
                System.out.print("            ");
                selectionSort(data2);
                System.out.print("           ");
                selectionSort(data3);
                System.out.print("         ");
                //selectionSort(data4);
                System.out.println("\n");
                
                break;
                default: sorting = false;
                break;
            }
        }
    }
}
