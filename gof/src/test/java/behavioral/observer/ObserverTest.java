package behavioral.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ObserverTest {

    @Test
    void newsByMagazineReturnsMagazineTitle() {
        NewsSource newsSource = new NewsSourceMagazine("Time");
        assertEquals("As a magazine, Time says: no news", newsSource.headline());
        NewsObserver newsObserver = (NewsObserver) newsSource;
        newsObserver.broadcast("there are no clouds");
        assertEquals("As a magazine, Time says: there are no clouds", newsSource.headline());
    }

    @Test
    void newsByPaperReturnsPaperTitle() {
        NewsSource newsSource = new NewsSourcePaper("the New York Times");
        assertEquals("As a paper, the New York Times says: no news", newsSource.headline());
    }

    @Test
    void newsByPamphletReturnsPamphletTitle() {
        NewsSource newsSource = new NewsSourcePamphlet("the Neighborhood news");
        assertEquals("==> As a pamphlet, the Neighborhood news says: no news", newsSource.headline());
        NewsObserver newsObserver = (NewsObserver) newsSource;
        newsObserver.broadcast("the sun is shining");
        assertEquals("==> As a pamphlet, the Neighborhood news says: the sun is shining", newsSource.headline());
    }

    @Test
    void addSubscribersToNewsAgency() {
        NewsSource newsSourceTime = new NewsSourceMagazine("Time");
        NewsSource newsSourceNYT = new NewsSourcePaper("the New York Times");
        NewsSource newsSourceTNN = new NewsSourcePamphlet("the Neighborhood news");

        NewsAgency newsAgency = new NewsAgency();
        newsAgency.subscribe(newsSourceTime);
        newsAgency.subscribe(newsSourceNYT);
        newsAgency.subscribe(newsSourceTNN);

        newsAgency.broadcastNews("all is well");

        assertEquals("As a magazine, Time says: all is well", newsSourceTime.headline());
        assertEquals("As a paper, the New York Times says: no news", newsSourceNYT.headline());
        assertEquals("==> As a pamphlet, the Neighborhood news says: all is well", newsSourceTNN.headline());

        newsAgency.unsubscribe(newsSourceTime);
        newsAgency.broadcastNews("things have changed");

        assertEquals("As a magazine, Time says: all is well", newsSourceTime.headline());
        assertEquals("As a paper, the New York Times says: no news", newsSourceNYT.headline());
        assertEquals("==> As a pamphlet, the Neighborhood news says: things have changed", newsSourceTNN.headline());

        newsAgency.unsubscribe(newsSourceTNN);
        newsAgency.subscribe(newsSourceTime);
        newsAgency.subscribe(newsSourceNYT);
        newsAgency.broadcastNews("another change");

        assertEquals("As a magazine, Time says: another change", newsSourceTime.headline());
        assertEquals("As a paper, the New York Times says: no news", newsSourceNYT.headline());
        assertEquals("==> As a pamphlet, the Neighborhood news says: things have changed", newsSourceTNN.headline());

    }

    //@Test
    void weakReference() {
        NewsSource newsSource1 = new NewsSourceMagazine("Mad");
        WeakReference<NewsSource> newsSource2 = new WeakReference<>(new NewsSourceMagazine("Reader's Digest"));

        assertEquals("Mad", newsSource1.getTitle());
        assertNotNull(newsSource2.get());
        assertEquals("Reader's Digest", newsSource2.get().getTitle());

        System.gc();

        assertEquals("Mad", newsSource1.getTitle());
        assertNotNull(newsSource2.get());
        assertEquals("Reader's Digest", newsSource2.get().getTitle());
    }

}
