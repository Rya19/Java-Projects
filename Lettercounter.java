import java.io.*;
import java.util.*;

public class LetterCounter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter file name: ");
        String inputFileName = input.nextLine();
        String outputFileName = "letterCount.txt";

        try {
            File inputFile = new File(inputFileName); //Create a file object
            Scanner fileReader = new Scanner(inputFile); //Pass file object to Scanner to work on the file object
            PrintWriter outputFile = new PrintWriter(outputFileName);

            int[] letterCount = new int[26];
            int lineCount = 0;
            int wordCount = 0;

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                lineCount++; // Track line count

                boolean inWord = false; // Helps track word boundaries

                // Loop through each character in the line
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);

                    // Check if the character is a letter
                    if (Character.isLetter(c)) {
                        letterCount[Character.toUpperCase(c) - 'A']++;

                        // Handle word counting
                        if (!inWord) {
                            wordCount++;
                            inWord = true; // We're inside a word
                        }
                    } 
                    else {
                        // If it's not a letter, we are outside of a word
                        inWord = false;
                    }
                }
            }

            fileReader.close();

            // Prepare output
            String header = inputFileName + " file has " + lineCount + " lines and " + wordCount + " words.";
            System.out.println(header);
            outputFile.println(header);

            for (int i = 0; i < 26; i++) {
                String letterOutput = "The occurrence of '" + (char) ('A' + i) + "' is " + letterCount[i];
                System.out.println(letterOutput);
                outputFile.println(letterOutput);
            }

            outputFile.close();
            System.out.println("Results have been written to " + outputFileName);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the file name and try again.");
        }
    }
}