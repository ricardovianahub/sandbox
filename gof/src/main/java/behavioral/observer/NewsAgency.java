package behavioral.observer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsAgency {

    List<WeakReference<NewsObserver>> newsObservers;

    public NewsAgency() {
        this.newsObservers = new ArrayList<>();
    }

    public void broadcastNews(String news) {
        for (WeakReference<NewsObserver> newsObserver : newsObservers) {
            Objects.requireNonNull(newsObserver.get()).broadcast(news);
        }
    }

    public void unsubscribe(Object observer) {
        for (WeakReference<NewsObserver> newsObserverWeakReference : this.newsObservers) {
            if (Objects.equals(newsObserverWeakReference.get(), observer)) {
                this.newsObservers.remove(newsObserverWeakReference);
                break;
            }
        }
    }

    public void subscribe(Object observer) {
        if (observer instanceof NewsObserver) {
            this.newsObservers.add(new WeakReference<>((NewsObserver) observer));
        }
    }
}
