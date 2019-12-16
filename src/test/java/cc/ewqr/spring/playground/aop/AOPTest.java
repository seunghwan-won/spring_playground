package cc.ewqr.spring.playground.aop;

import cc.ewqr.spring.playground.calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AOPTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
    ArithmeticCalculator arithmeticCalculator;
    UnitCalculator unitCalculator;
    MaxCalculator maxCalculator;
    MinCalculator minCalculator;

    @BeforeEach
    void setUp() {
        arithmeticCalculator = context.getBean("arithmeticCalculator", ArithmeticCalculator.class);
        unitCalculator = context.getBean("unitCalculator", UnitCalculator.class);
        maxCalculator = context.getBean("maxCalculator", MaxCalculator.class);
        minCalculator = context.getBean("minCalculator", MinCalculator.class);
    }

    @Test
    void arithmeticCalculatorAddTest() {
        arithmeticCalculator.add(1, 1);
    }

    @Test
    void unitCalculatorKTPTest() {
        unitCalculator.kilogramToPound(100);
    }

    @Test
    void unitCalculatorKTMTest() {
        unitCalculator.kilometerToMile(10);
    }

    @Test
    void maxTest() {
        assertEquals(10, maxCalculator.max(10, 2), 0.1);
    }

    @Test
    void minTest() {
        assertEquals(2, minCalculator.min(10, 2), 0.1);
    }

    @Test
    void multiInheritanceArithmeticCalculator() {
        MaxCalculator maxCalculator = (MaxCalculator) arithmeticCalculator;
        MinCalculator minCalculator = (MinCalculator) arithmeticCalculator;
        assertEquals(10, maxCalculator.max(10, 2), 0.1);
        assertEquals(2, minCalculator.min(10, 2), 0.1);
    }
}
