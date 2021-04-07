package com.company.myClass;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);
    private Console() {}

    public String charIn(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public int intIn(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    //-------------- Input validation ------------------
    //check if the input is integer
    public static int validateInt(String prompt) {
        System.out.print(prompt);
        int input;
        while (!scanner.hasNextInt()) {
            System.out.print("Please input a number!");
            scanner.next();
        }
        input = scanner.nextInt();
        return input;
    }
    //check if the input is integer and is in range
    public static int validateInt(String prompt, int min, int max) {
        int input;
        System.out.print(prompt);
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number: ");
                scanner.next();
            }
            input = scanner.nextInt();
            if (((input < min) || (input > max))){
                System.out.print("Index out of range. Please re-enter: ");
            }else{
                break;
            }
        } while (true);
        return input;
    }

    //validate name
    public static String validateName() {
        String result = scanner.nextLine();
        while (!result.matches("^[a-zA-Z\\s]+")) {
            System.out.print("Please don't use special character in name");
            result = scanner.nextLine();
        }
        return result;
    }

    //validate Student Id
    public static String validateStudentId(String prompt) {
        System.out.println(prompt);
        String result = scanner.next();
        while (!result.matches("^(s|S)[0-9]{7}")) {
            System.out.print("Invalid student ID.(e.g s3836387)");
            result = scanner.nextLine();
        }

        return result;
    }

    public static String validateCourseId(String prompt) {
        System.out.print(prompt);
        String result = scanner.next();
        while (!result.matches("^[a-zA-Z]{4}[0-9]{4}")) {
            System.out.print("Invalid course ID.(e.g COSC2440)");
            result = scanner.nextLine();
        }
        return result;
    }

    public static String validateSem(String prompt) {
        System.out.print(prompt);
        String result = scanner.next();
        boolean isValid = false;
        while (!isValid) {
            while (!result.matches("^[0-9]{4}(A|B|C)$")) {
                System.out.print("Invalid semester format or semester (A,B,C). Please capitalize last letter.(e.g 2020A)");
                result = scanner.next();
            }
            LocalDate year = LocalDate.parse(result.substring(0,4)+"-01-01");
            if (year.compareTo(LocalDate.of(2001,1,1))>0){
                isValid = true;
            }else{
                System.out.println("Please input a valid year! (After 2001-01-01)");
                result = scanner.next();
            }
        }
        return result;
    }

    //validate input date
    public static String validateDateWithLimit(String prompt) {
        String result = null;
        // Parses the first date
        LocalDate valDate;
        // Parses the second date
        LocalDate limitDate = LocalDate.parse("2015-01-01");
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(prompt);
                result = scanner.next();
                valDate = LocalDate.parse(result);
                if (valDate.compareTo(limitDate)<0){
                    isValid = true;
                }else{
                    System.out.println("Please input a valid date! (Before 2015-01-01)");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Wrong date format!(yyyy-mm-dd)");
                isValid = false;
            }
        }
        return result;

    }



}
