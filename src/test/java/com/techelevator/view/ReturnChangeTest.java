package com.techelevator.view;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ReturnChangeTest {

    @Test
    void test_for_change() {
        // Arrange
        BigDecimal testTransaction = new BigDecimal(1.20);
        String expected = "4 Quarter(s) 2 Dime(s) ";
        // Act
        String actual = ReturnChange.returnChange(testTransaction);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void test_for_zero_change() {
        // Arrange
        BigDecimal testTransaction = new BigDecimal(0.00);
        String expected = "";
        // Act
        String actual = ReturnChange.returnChange(testTransaction);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void test_for_substantial_change() {
        BigDecimal testTransaction = new BigDecimal(200.15);
        String expected = "800 Quarter(s) 1 Dime(s) 1 Nickel(s) ";
        // Act
        String actual = ReturnChange.returnChange(testTransaction);
        // Assert
        assertEquals(expected, actual);
    }
}