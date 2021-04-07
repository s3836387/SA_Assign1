package com.company.myClass;


import com.company.myClass.*;

import java.io.*;
import java.util.*;


public class FileProcessor {

    //write into the file by line
    public void writeNewEnrolment(StudentEnrolment data, String filePath) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(filePath, true));
        String delimiter = ",";
        pw.print(data.toString());
        pw.println();
        pw.flush();
        pw.close();
    }

    //update data in the file
    public void updateFile(String filePath, List<StudentEnrolment> listData) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(filePath, false));
        for (StudentEnrolment row : listData) {
            pw.print(row.toString());
            pw.println();
        }
        pw.close();
    }

    //Show records to the console
    public void showRecords(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String line;
        System.out.println("ID\tName\tBirthday\tGender\tPhone\tEmail\tAddress");
        while ((line = csvReader.readLine()) != null) {
            String[] data = line.split(",");
            for (String record : data) {
                System.out.print(record + "\t");
            }
            System.out.println();
        }
        csvReader.close();

    }

    //read all the data into array list
    public List<String> readFile(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        List<String> records = new ArrayList<>();
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            records.add("\""+row+"\"");
        }
        csvReader.close();
        return records;
    }




}
