package behavioral.observer;

public class NewsSourcePaper extends NewsSource {
    public NewsSourcePaper(String title) {
        super(title);
    }

    @Override
    protected String headline() {
        return super.headline("paper");
    }
}
