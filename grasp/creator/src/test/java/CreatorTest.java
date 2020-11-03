import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CreatorTest {

    @Test
    void constructor() {
        String typeOfReservation = "NonRev";
        CoreReservation reservation1 =
                CoreReservationFactory.of(typeOfReservation);

        assertTrue(reservation1 instanceof CoreReservationNonRev);
    }

}
