package net.ricardoviana.maprouter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapOfPoiTest {

    @Test
    void simpleMappingOfPois() {
        MapOfPoi mapOfPoi = MapOfPoi.fromDesign("" +
                "..." +
                "12." +
                "...", new String[]{}
        );

        Poi poi1 = mapOfPoi.getPoi("1");
        assertEquals(0, poi1.getColumn());
        assertEquals(1, poi1.getRow());
        Poi poi2 = mapOfPoi.getPoi("2");
        assertEquals(1, poi2.getColumn());
        assertEquals(1, poi2.getRow());

        assertEquals(1.0d, mapOfPoi.distance(poi2, poi1));
    }

    @Test
    void diagonal() {
        MapOfPoi mapOfPoi = MapOfPoi.fromDesign("" +
                        "..1" +
                        ".2." +
                        "...",
                new String[]{});

        Poi poiStart = mapOfPoi.getPoi("1");
        Poi poiEnd = mapOfPoi.getPoi("2");

        assertEquals(Math.sqrt(2), mapOfPoi.distance(poiStart, poiEnd));
    }

    @Test
    void diagonal2() {
        MapOfPoi mapOfPoi = MapOfPoi.fromDesign("" +
                        "1.3" +
                        ".2." +
                        ".4.",
                new String[]{});

        Poi poiStart = mapOfPoi.getPoi("1");
        Poi poiEnd = mapOfPoi.getPoi("4");

        assertEquals(Math.sqrt(5), mapOfPoi.distance(poiStart, poiEnd));
    }

    @Test
    void badRoute1() {
        MapOfPoi mapOfPoi = MapOfPoi.fromDesign("" +
                        ".A..1" +
                        "...B2" +
                        ".....",
                new String[]{});

        Poi poiStart = mapOfPoi.getPoi("1");
        Poi poiEnd = mapOfPoi.getPoi("1");

        assertThrows(
                IllegalArgumentException.class,
                () -> mapOfPoi.shortestTravel(poiStart, poiEnd),
                "Both POIs cannot be the same"
        );
    }

    @Test
    void cannotTravelFromLetterToLetter() {
        MapOfPoi mapOfPoi = MapOfPoi.fromDesign("" +
                        ".A..1" +
                        "...B2" +
                        ".....",
                new String[]{});

        Poi poiStart = mapOfPoi.getPoi("A");
        Poi poiEnd = mapOfPoi.getPoi("B");

        assertThrows(
                IllegalArgumentException.class,
                () -> mapOfPoi.shortestTravel(poiStart, poiEnd),
                "Cannot travel from letter to letter"
        );
    }

    @Test
    void canTravelFromLetterToNumberIfRegistered() {
        MapOfPoi mapOfPoi = MapOfPoi.fromDesign("" +
                        ".A..1" +
                        "...B2" +
                        ".....",
                new String[]{"A1"});

        Poi poiStart = mapOfPoi.getPoi("A");
        Poi poiEnd = mapOfPoi.getPoi("1");
        System.out.println("poiStart = " + poiStart);
        System.out.println("poiEnd = " + poiEnd);

        assertEquals(3, mapOfPoi.shortestTravel(poiStart, poiEnd));
    }

//    @Test
//    void cannotTravelFromLetterToNumberIfNotRegistered() {
//        MapOfPoi mapOfPoi = MapOfPoi.fromDesign("" +
//                        ".A..1" +
//                        "...B2" +
//                        ".....",
//                new String[]{});
//
//        Poi poiStart = mapOfPoi.getPoi("A");
//        Poi poiEnd = mapOfPoi.getPoi("1");
//
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> mapOfPoi.shortestTravel(poiStart, poiEnd),
//                "Cannot travel from letter to number when route is not registered"
//        );
//    }
//
//    @Test
//    void complexTravelAddsTheDistanceOfEachSegmentWithNavigation() {
//        MapOfPoi mapOfPoi = MapOfPoi.fromDesign(
//                ".A..1" +
//                        "...B2" +
//                        ".....",
//                new String[]{"A1", "B2"});
//
//        Poi poiStart = mapOfPoi.getPoi("A");
//        Poi poiEnd = mapOfPoi.getPoi("B");
//
//        assertEquals(3.0d + 1.0d + 1.0d, mapOfPoi.distance(poiStart, poiEnd));
//    }
//
//    @Test
//    void complexTravelAddsTheDistanceOfEachSegmentWithNavigation2() {
//        MapOfPoi mapOfPoi = MapOfPoi.fromDesign(
//                 ".A..1" +
//                        "...B2" +
//                        "....." +
//                        "3...C",
//                new String[]{"A1", "B2", "C2"});
//
//        Poi poiStart = mapOfPoi.getPoi("A");
//        Poi poiEnd = mapOfPoi.getPoi("C");
//
//        assertEquals(3.0d + 1.0d + 2.0d, mapOfPoi.distance(poiStart, poiEnd));
//    }
//

}

