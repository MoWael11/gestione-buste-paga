package it.eforhum.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Input {
    protected Input() {}

    private static final Scanner scanner = new Scanner(System.in);

    public static String stringInput(String message) {
        System.out.print(message);
        String input =  scanner.nextLine();
        if (input.isBlank()) {
            System.out.println("Input non valido, riprova.");
            return stringInput(message);
        }
        return input;
    }

    public static Byte byteInput(String message) {
        System.out.print(message);
        try {
            byte input = scanner.nextByte();
            scanner.nextLine();
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, riprova.");
            scanner.nextLine();
            return byteInput(message);
        }
    }
    
    public static Integer integerInput(String message) {
        System.out.print(message);
        try {
            Integer input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, riprova.");
            scanner.nextLine();
            return integerInput(message);
        }
    }
    
    public static Double doubleInput(String message) {
        System.out.print(message);
        try {
            Double input = scanner.nextDouble();
            scanner.nextLine();
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, riprova.");
            scanner.nextLine();
            return doubleInput(message);
        }
    }

    public static LocalDate dateInput(String message) {
        LocalDate date;
        try {
            date = LocalDate.parse(stringInput(message + " (formato YYYY-MM-DD): ") , DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return dateInput(message);
        }
        return date;
    }

    public static Integer yearInput(String message) {
        Integer year = integerInput(message + " (formato YYYY): ");
        if (year < 0 || year > 9999) {
            System.out.println("Input invalido");
            return yearInput(message);
        }

        return year;
    }

}
