package CalculatorTesting14.CalculatorTesting.service;

import CalculatorTesting14.CalculatorTesting.exception.DivideByZeroException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceImplTest {
    private CalculatorService calculatorService = new CalculatorServiceImpl();

    private static final int POSITIVE_VALUE = 10;
    private static final int NEGATIVE_VALUE = -5;
    private static final int ZERO = 0;

    public static Stream<Arguments> plusCases() {
        return Stream.of(
                Arguments.of(POSITIVE_VALUE, POSITIVE_VALUE, POSITIVE_VALUE + POSITIVE_VALUE),
                Arguments.of(NEGATIVE_VALUE, NEGATIVE_VALUE, NEGATIVE_VALUE + NEGATIVE_VALUE),
                Arguments.of(ZERO, POSITIVE_VALUE, ZERO + POSITIVE_VALUE)
        );
    }

    public static Stream<Arguments> minusCase() {
        return Stream.of(
                Arguments.of(POSITIVE_VALUE, POSITIVE_VALUE, POSITIVE_VALUE - POSITIVE_VALUE),
                Arguments.of(NEGATIVE_VALUE, NEGATIVE_VALUE, NEGATIVE_VALUE - NEGATIVE_VALUE),
                Arguments.of(ZERO, POSITIVE_VALUE, ZERO - POSITIVE_VALUE),
                Arguments.of(POSITIVE_VALUE, NEGATIVE_VALUE, POSITIVE_VALUE - NEGATIVE_VALUE)
        );
    }

    public static Stream<Arguments> divedeCase() {
        return Stream.of(
                Arguments.of(POSITIVE_VALUE, POSITIVE_VALUE, POSITIVE_VALUE / POSITIVE_VALUE),
                Arguments.of(NEGATIVE_VALUE, NEGATIVE_VALUE, NEGATIVE_VALUE / NEGATIVE_VALUE),
                Arguments.of(ZERO, POSITIVE_VALUE, ZERO / POSITIVE_VALUE),
                Arguments.of(POSITIVE_VALUE, NEGATIVE_VALUE, POSITIVE_VALUE / NEGATIVE_VALUE)
        );
    }


    @Test
    void sum() {
        int result = calculatorService.sum(POSITIVE_VALUE, POSITIVE_VALUE);
        assertEquals(POSITIVE_VALUE + POSITIVE_VALUE, result);
    }

    @ParameterizedTest
    @MethodSource("plusCases")
    void sumParams(int num1, int num2, int expected) {
        int result = calculatorService.sum(num1, num2);
        assertEquals(expected, result);
    }

    @Test
    void subtract() {
        int result = calculatorService.subtract(NEGATIVE_VALUE, POSITIVE_VALUE);
        assertEquals(NEGATIVE_VALUE - POSITIVE_VALUE, result);
    }

    @ParameterizedTest
    @MethodSource("minusCase")
    void subtractParams(int num1, int num2, int expected) {
        int result = calculatorService.subtract(num1, num2);
        assertEquals(expected, result);
    }

    @Test
    void multiply() {
        int result = calculatorService.multiply(NEGATIVE_VALUE, POSITIVE_VALUE);
        assertEquals(NEGATIVE_VALUE * POSITIVE_VALUE, result);
    }

    @Test
    void divide() {
        int result = calculatorService.divide(NEGATIVE_VALUE, POSITIVE_VALUE);
        assertEquals(NEGATIVE_VALUE / NEGATIVE_VALUE, result);
    }

    @ParameterizedTest
    @MethodSource("divedeCase")
    void divideParams(int num1, int num2, int expected) {
        int result = calculatorService.divide(num1, num2);
        assertEquals(expected, result);
    }

    @Test
    void divideByZero() {
        assertThrows(DivideByZeroException.class,
                () -> calculatorService.divide(POSITIVE_VALUE, ZERO));
    }
}