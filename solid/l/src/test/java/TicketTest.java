import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TicketTest {

    @ParameterizedTest
    @CsvSource({
            "ORD,LAX,4:30,1500,AA20,1200",
            "ORD,LAX,4:30,2000,AA20,1600",
            "ORD,LAX,4:30,1500,REGULAR,1500",
            "ORD,LAX,4:30,1500,NONREV,0"
    })
    void standardTicketHasBaseFare(String origin, String destination, String duration,
                                   double cost, String discount, double expected) {
        PlaneTicket planeTicket = new PlaneTicket(
                origin, destination, duration, cost, discount
        );

        assertEquals(expected, planeTicket.fare());
    }

}
