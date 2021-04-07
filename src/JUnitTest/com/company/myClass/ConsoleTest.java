package com.company.myClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    void validateName() {
        String output = Console.validateName("Ngo Quang Khai");
        assertEquals("Ngo Quang Khai", output);
    }
}