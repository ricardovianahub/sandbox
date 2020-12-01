package behavioral.observer;

public class NewsSourcePamphlet extends NewsSource implements NewsObserver {
    public NewsSourcePamphlet(String title) {
        super(title);
    }

    public String headline() {
        return "==> " + super.headline("pamphlet");
    }

    @Override
    public void broadcast(String news) {
        this.news = news;
    }
}
