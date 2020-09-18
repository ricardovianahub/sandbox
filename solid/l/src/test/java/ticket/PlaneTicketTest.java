package ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlaneTicketTest {

    @ParameterizedTest
    @CsvSource({
            "ORD,LAX,4:30,1500,AA20,1200",
            "ORD,LAX,4:30,2000,AA20,1600",
            "ORD,LAX,4:30,1500,REGULAR,1500",
            "DFW,LAX,4:30,1500,REGULAR,1350",
            "ORD,LAX,4:30,1500,NONREV,0"
    })
    void standardTicketHasBaseFare(String origin, String destination,
                                   String duration,
                                   double cost, String discount,
                                   double expected) {
        PlaneTicket planeTicket;
        switch (discount) {
            case "AA20":
                planeTicket = new PlaneTicketAA20(
                        origin, destination, duration, cost
                );
                break;
            case "NONREV":
                planeTicket = new PlaneTicketNonrev(
                        origin, destination, duration
                );
                break;
            default:
                planeTicket = new PlaneTicket(
                        origin, destination, duration, cost
                );
        }

        Assertions.assertEquals(expected, planeTicket.fare());
    }

    @ParameterizedTest
    @CsvSource({
            "ORD,LAX,4:30,1500,1500,Something LLC",
            "DFW,LAX,4:30,1500,1500,Something LLC"
    })
    void airPass(String origin, String destination, String duration,
                 double cost, double expected) {
        AirPass airPass = new AirPass(
                origin, destination, duration, cost, "Something LLC"
        );
        Assertions.assertEquals(expected, airPass.fare());
    }

}
