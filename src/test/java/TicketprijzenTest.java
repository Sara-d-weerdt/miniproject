
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketprijzenTest {

    private TicketprijzenTest calculator;

    @BeforeEach
    void setUp() {
        calculator = new TicketprijzenTest();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.00",
            "1, 0.00",
            "2, 0.00"
    })
    void childrenUnderThreeGetFreeTicket(int age, String expected) {
        assertPrice(expected, age);
    }

    @ParameterizedTest
    @CsvSource({
            "18, 12.75",
            "20, 12.75",
            "30, 12.75"
    })
    void studentsGetFifteenPercentDiscount(int age, String expected) {
        assertPrice(expected, age);
    }

    @ParameterizedTest
    @CsvSource({
            "65, 11.25",
            "70, 11.25",
            "80, 11.25"
    })
    void seniorsGetTwentyFivePercentDiscount(int age, String expected) {
        assertPrice(expected, age);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 31, 40, 64})
    void otherVisitorsPayFullPrice(int age) {
        assertPrice("15.00", age);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -100})
    void negativeAgeThrowsException(int age) {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculatePrice(age)
        );
    }

    private void assertPrice(String expected, int age) {
        BigDecimal actual = calculator.calculatePrice(age);

        assertEquals(
                0,
                new BigDecimal(expected).compareTo(actual),
                "Onjuiste prijs voor leeftijd " + age
        );
    }
}
