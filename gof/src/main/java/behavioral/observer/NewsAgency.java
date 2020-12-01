package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency {

    List<NewsObserver> newsObservers;

    public NewsAgency() {
        this.newsObservers = new ArrayList<>();
    }

    public void broadcastNews(String news) {
        for (NewsObserver newsObserver : newsObservers) {
            newsObserver.broadcast(news);
        }
    }

    public void unsubscribe(Object observer) {
        this.newsObservers.remove(observer);
    }

    public void subscribe(Object observer) {
        if (observer instanceof NewsObserver) {
            this.newsObservers.add((NewsObserver) observer);
        }
    }
}
