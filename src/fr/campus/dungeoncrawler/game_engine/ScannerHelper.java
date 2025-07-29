package fr.campus.dungeoncrawler.game_engine;
import java.util.Scanner;

public class ScannerHelper {
        private static final Scanner scanner = new Scanner(System.in);

        public static String getString(String message) {
                System.out.print(message); //show message to user
                return scanner.nextLine(); //wait for user to press enter that return the text
        }

        public static int getInt(String message) {
                System.out.print(message);
                while (!scanner.hasNextInt()) {
                        System.out.print("Please enter a number: ");
                        scanner.next(); // skip invalid input
                }
                int value = scanner.nextInt();
                scanner.nextLine(); // consume the leftover newline
                return value;
        }


}
