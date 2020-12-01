package behavioral.observer;

public class NewsSourceMagazine extends NewsSource implements NewsObserver {
    public NewsSourceMagazine(String title) {
        super(title);
    }

    @Override
    String headline() {
        return super.headline("magazine");
    }

    @Override
    public void broadcast(String news) {
        this.news = news;
    }
}
