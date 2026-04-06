import java.io.*;
import java.util.Scanner;

public class Project01Q3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;

        while (repeat) {
            System.out.println("Welcome to the Cipher program.");
            System.out.println("Type 1 for Substitution Cipher.");
            System.out.println("Type 2 for Shuffle Cipher.");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Cipher cipher;
            if (choice == 1) {
                System.out.print("What is the key (shift amount) for your code? ");
                int shift = scanner.nextInt();
                scanner.nextLine();
                cipher = new SubstitutionCipher(shift);
                System.out.println("SubstitutionCipher-shift amount = " + shift);

            } 
            else {
                System.out.print("What is the key (shuffle amount) for your code? ");
                int n = scanner.nextInt();
                scanner.nextLine();
                cipher = new ShuffleCipher(n);
                System.out.println("ShuffleCipher-shuffle amount = " + n);

            }

            System.out.print("Enter input file name: ");
            String inputFile = scanner.nextLine();

            System.out.print("Enter output file name: ");
            String outputFile = scanner.nextLine();

            System.out.print("Encode (E) or Decode (D): ");
            char action = scanner.nextLine().charAt(0);

            //processFile(inputFile, outputFile, cipher, action);

            System.out.println("Do you want to do another message (Y/N)?");
            char again = scanner.nextLine().charAt(0);
            repeat = (again == 'Y' || again == 'y');
        }
        scanner.close();
    }
}

interface MessageEncoder {
    String Encode_Text(String plainText); //concrete func | part A
    //Classes that implement this interface must have the encode function
}

interface MessageDecoder {
    String Decode_Text(String Cipher_Text); //concrete func | Part E
}

abstract class Cipher {
    public abstract String Cipher_Type(); //concrete func | Part B
}


class SubstitutionCipher extends Cipher implements MessageDecoder, MessageEncoder { // Part C
    //PRIVATE
    private int shift;

    //PUBLIC    

    public SubstitutionCipher(int shift) { //Constructor
        this.shift = shift;
    }

    @Override
    public String Cipher_Type() {
        return "SubstitutionCipher"; //Returns string 
    }

    private char Shift_Character(char letter, int shift) {
        if (Character.isLetter(letter)) {
            char base_char = Character.isUpperCase(letter) ? 'A' : 'a'; //If letter is upper, base_char = ascii value 65. Else, base_char = ascii val 97
            return (char) (((letter - base_char) + shift) % 26 + base_char);
            // Zero-based index. if letter = C, then C - A = 67 - 65 = 2. C is 2nd letter of alphabet. We add shift value, so 2 + shift val (= 3) = 5.
            //then we need circular shifting if the shift is > than the letter index (like shift = 3, and letter is Z). Then add base to convert to character on ascii val.
            //Case it with char
        }
        return letter; //if letter is not a letter
    }

    @Override
    public String Encode_Text(String plainText) {
        StringBuilder encoded_Text = new StringBuilder(); //allows for text modifications since strings are immutable

        for (char letter : plainText.toCharArray()) { //allows to process one letter at a time
            encoded_Text.append(Shift_Character(letter, shift)); 
        }

        return encoded_Text.toString();
    }

    @Override
    public String Decode_Text(String Cipher_Text) {
        StringBuilder Decode_Text = new StringBuilder(); //allows for text modifications since strings are immutable

        for (char letter : Cipher_Text.toCharArray()) { //allows to process one letter at a time
            Decode_Text.append(Shift_Character(letter, -shift)); //shifts in other direction due to - sign
        }

        return Decode_Text.toString();
    }
}


class ShuffleCipher extends Cipher implements MessageEncoder, MessageDecoder {
    //PRIVATE
    private int n; //What is n? Its to store how many times the text is shuffled (maybe make it more clear...)

    //PUBLIC
    public ShuffleCipher(int n) {
        this.n = n;
    }

    @Override
    public String Cipher_Type() {
        return "ShuffleCipher";
    }

    private String Shuffle_Text(String user_text) {
        int Text_Middle = (user_text.length() + 1) / 2; //If its odd, makes the first half get the extra character
        String First_Half = user_text.substring(0, Text_Middle);
        String Second_Half = user_text.substring(Text_Middle);

        StringBuilder Text_Shuffled = new StringBuilder();
        int i = 0, j = 0;

        while (i < First_Half.length() || j < Second_Half.length()) {
            if (i < First_Half.length()) {
                Text_Shuffled.append(First_Half.charAt(i)); //Put first letter of First_Half
                i++;
            }
            if (j < Second_Half.length()) {
                Text_Shuffled.append(Second_Half.charAt(j)); //Then puts first letter of Second_Half 
                j++;
            }
        }
        return Text_Shuffled.toString(); //Keep doing that until all letters are used
    }

    private String Unshuffle_Text(String user_text) {
        StringBuilder First_Half = new StringBuilder();
        StringBuilder Second_Half = new StringBuilder();

        for (int i = 0; i < user_text.length(); i++) { //loops through entire string text
            if (i % 2 == 0) { //even index go to firsthalf
                First_Half.append(user_text.charAt(i));
            } else { //odd index go to secondhalf
                Second_Half.append(user_text.charAt(i));
            }
    }
        return First_Half.toString() + Second_Half.toString();
    }

    @Override
    public String Encode_Text(String plainText) {
        String text = plainText;
        for (int i = 0; i < n; i++) {
            text = Shuffle_Text(text);
        }
        return text;
    }

    @Override
    public String Decode_Text(String cipherText) {
        String text = cipherText;
        for (int i = 0; i < n; i++) {
            text = Unshuffle_Text(text);
        }
        return text;
    }


}