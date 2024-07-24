public class Main {
    public static void main(String[] args) {
        String input1 = "c20ad4d76fe97759aa27a0c99bff6710";
        String input2 = "01a6ed5cb97b464996f7cd2c491f7431";

        System.out.println("First number (1-9) in input1: " + findFirstNumber(input1));
        System.out.println("First number (1-9) in input2: " + findFirstNumber(input2));
    }

    public static char findFirstNumber(String input) {
        // Iterate through the characters of the string
        for (char c : input.toCharArray()) {
            // Check if the character is a digit between '1' and '9'
            if (c >= '1' && c <= '9') {
                return c; // Return the first digit found
            }
        }
        // If no digit between '1' and '9' is found, return a placeholder or throw an exception
        return '0'; // You can choose a different placeholder or handle the case differently
    }
}

