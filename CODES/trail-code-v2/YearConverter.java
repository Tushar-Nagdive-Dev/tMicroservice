import java.util.Scanner;

public class YearConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the year (e.g., 500 BCE, 300 AD): ");
        String input = scanner.nextLine().trim();

        // Split the input into the year and era part
        String[] parts = input.split(" ");
        int year = Integer.parseInt(parts[0]);
        String era = parts[1].toUpperCase();

        int convertedYear = convertToCE(year, era);
        if (convertedYear != Integer.MIN_VALUE) {
            System.out.println("The year in CE is: " + convertedYear);
        } else {
            System.out.println("Invalid era input.");
        }

        scanner.close();
    }

    // Function to convert different eras to CE
    private static int convertToCE(int year, String era) {
        switch (era) {
            case "BCE":
            case "BC":
                return -year + 1; // BCE or BC years are negative, offset for 1-based years.
            case "AD":
            case "CE":
                return year; // AD and CE years remain the same.
            default:
                return Integer.MIN_VALUE; // Return error code if the era is invalid.
        }
    }
}

/*
 *
 * Explanation:
BC (Before Christ) and BCE (Before Common Era) represent years before year 1 CE. For example, 500 BC would be converted to -499 CE (as there's no year 0).
AD (Anno Domini) and CE (Common Era) represent years in the CE system, where AD 500 = CE 500.

Input Examples:
500 BCE will output -499 CE.
500 AD or 500 CE will output 500 CE.
Pros:
Simple and straightforward: This approach is easy to understand and implement.
Customizable: You can add more era systems if needed by expanding the switch statement.
Efficient: It directly converts the input without unnecessary complexity.
Cons:
Limited Input Handling: The program only handles inputs in a specific format like "500 BCE". It may not gracefully handle inputs like "BCE 500" or other variations.
No error validation for input format: If the input is incorrect (e.g., no space between year and era), the program will crash or misbehave.
Basic input handling: The user has to strictly follow the format. This could be enhanced by more robust parsing.

*/
