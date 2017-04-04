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
        // Select which file should be scanned
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
        // Scan the given file, returning an error if the file does not exist
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
        // Begin algorithm timer
        long startTime = System.currentTimeMillis();
        int temp;
        for (int i = 1; i < input.length; i++) 
        {
            for(int j = i ; j > 0 ; j--)
            {
                if(input[j] < input[j-1])
                {
                    // Insert the new value, moving as many values as necessary
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
        // Is the input is 1 there is nothing to sort
        if (input.length <= 1) 
        {
            return input;
        }

        // Split the array in half
        // Creates two new arrays of langth based on the old array
        int[] first = new int[input.length / 2];
        int[] second = new int[input.length - first.length];
        // Copy-spit the old array into the two new arrays
        // Format is: arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
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
        // Next/First element to consider in the first array
        int firstElement = 0;
        // Next/Firs element to consider in the second array
        int secondElement = 0;
        int j = 0;
        // As long are we are within the bounds of the arrays, move the smaller element into the result
        while (firstElement < first.length && secondElement < second.length) 
        {
            if (first[firstElement] < second[secondElement]) 
            {
                result[j] = first[firstElement];
                firstElement++;
            } 
            // Otherwise the second array's value is greater, copy it to the result
            else 
            {
                result[j] = second[secondElement];
                secondElement++;
            }
            j++;
        }
        // Copy the remiander
        System.arraycopy(first, firstElement, result, j, first.length - firstElement);
        System.arraycopy(second, secondElement, result, j, second.length - secondElement);
    }

    /**
     * Selection Sort
     */
    public static int[] selectionSort(int[] input)
    {
        // Begin timer
        long startTime = System.currentTimeMillis();
        // Loop for the whole data
        for (int i = 0; i < input.length - 1; i++)
        {
            int index = i;
            // Loop until we find the next lowest number
            for (int j = i + 1; j < input.length; j++)
            {
                if (input[j] < input[index]) 
                {
                    index = j;
                }
            }
            // Move the smallest value 
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
        // Begin timer
        long startTime = System.currentTimeMillis();
        int j;
        boolean swap = true;
        int temp;
        while (swap)
        {
            swap = false;    
            // Swap is false unless we find a new swap possible
            for(j = 0; j < input.length - 1; j++)
            {
                // If one value is larger we swap it
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
        // End calculation time
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
                case 1:  System.out.print("Sort time (MS): ");
                int[] result = insertSort(data);
                getValues(result);
                break;
                case 2:  long startTime = System.currentTimeMillis();
                int[] result2 = mergeSort(data);
                long endTime = System.currentTimeMillis();
                long duration = (endTime - startTime);
                System.out.print("\nSort time (MS): ");
                System.out.print(duration);
                getValues(result2);
                break;
                case 3:  System.out.print("Sort time (MS): ");
                int[] result3 = selectionSort(data);
                getValues(result3);
                break;
                case 4:  System.out.print("Sort time (MS): ");
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
