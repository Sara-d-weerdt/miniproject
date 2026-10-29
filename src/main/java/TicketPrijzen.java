
import java.math.BigDecimal;

public class TicketPrijzen {

    private static final BigDecimal STANDAARD_PRIJS =
            new BigDecimal("15.00");

    private static final BigDecimal OUDEREN_KORTING =
            new BigDecimal("0.25");

    private static final BigDecimal STUDENTEN_KORTING =
            new BigDecimal("0.15");

    public BigDecimal calculatePrice(int age) {

        if (age < 0) {
            throw new IllegalArgumentException(
                    "Leeftijd mag niet negatief zijn"
            );
        }

        if (age < 3) {
            return BigDecimal.ZERO.setScale(2);
        }

        if (age >= 65) {
            return STANDAARD_PRIJS
                    .multiply(BigDecimal.ONE.subtract( OUDEREN_KORTING))
                    .setScale(2);
        }

        if (age >= 18 && age <= 30) {
            return STANDAARD_PRIJS
                    .multiply(BigDecimal.ONE.subtract(STUDENTEN_KORTING))
                    .setScale(2);
        }

        return STANDAARD_PRIJS;
    }
}
