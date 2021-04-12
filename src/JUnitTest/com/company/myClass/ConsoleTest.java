package com.company.myClass;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {
    InputStream sysInBackup = System.in;
    ByteArrayInputStream in;

    @Test
    void stringIn() {
        System.out.println("Test string Input without space");
        in = new ByteArrayInputStream("src/com/company/resource".getBytes());
        System.setIn(in);
        assertEquals("src/com/company/resource",Console.stringIn("Input:"));
        System.setIn(sysInBackup);
    }

    @Test
    void validateInt() {
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        assertEquals(1,Console.validateInt("Input:",0,1));
        System.setIn(sysInBackup);
    }

    @Test
    void validateStudentIdnormal() {
        in = new ByteArrayInputStream("s3836387".getBytes());
        System.setIn(in);
        assertEquals("s3836387",Console.validateStudentId("Input."));
        System.setIn(sysInBackup);
    }

    @Test
    void validateStudentIdCapitalize() {
        ByteArrayInputStream in = new ByteArrayInputStream("S3836387".getBytes());
        System.setIn(in);
        assertEquals("S3836387",Console.validateStudentId("Input."));
        System.setIn(sysInBackup);
    }

    @Test
    void validateCourseIdnormal() {
        System.out.println("Test student id input");
        // Test with normal course id. (4 char and 4 number)
        ByteArrayInputStream in = new ByteArrayInputStream("COSC4030".getBytes());
        System.setIn(in);
        assertEquals("COSC4030",Console.validateCourseId("Input."));
        System.setIn(sysInBackup);
    }

    @Test
    void validateCourseIddefaultSample() {
        // Test with course Id with 3 char and 4 number
        in = new ByteArrayInputStream("BUS2232".getBytes());
        System.setIn(in);
        assertEquals("BUS2232",Console.validateCourseId("Input."));
        System.setIn(sysInBackup);
    }

    @Test
    void validateSemNormal() {
        // Test with course Id with 3 char and 4 number
        in = new ByteArrayInputStream("2020A".getBytes());
        System.setIn(in);
        assertEquals("2020A",Console.validateSem("Input."));
        System.setIn(sysInBackup);
    }


}