package com.company.myClass;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);
    private Console() {}

    public static String stringIn(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
    //-------------- Input validation ------------------
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

    //validate Student Id
    public static String validateStudentId(String prompt) {
        System.out.println(prompt);
        String result = scanner.next();
        while (!result.matches("^(s|S)[0-9]{7}")) {
            System.out.print("Invalid student ID.(e.g s3836387)");
            result = scanner.next();
        }

        return result;
    }

    public static String validateCourseId(String prompt) {
        System.out.print(prompt);
        String result = scanner.next();
        while (!result.matches("^[a-zA-Z]{3,4}[0-9]{4}")) {
            System.out.print("Invalid course ID.(e.g COSC2440)");
            result = scanner.next();
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





}
