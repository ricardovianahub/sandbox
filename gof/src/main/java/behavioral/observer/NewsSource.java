package behavioral.observer;

public abstract class NewsSource {
    protected String title;
    protected String news;

    public NewsSource(String title) {
        this.title = title;
        this.news = "no news";
    }

    abstract String headline();

    public void setNews(String news) {
        this.news = news;
    }

    protected String headline(String newsSourceType) {
        return "As a " + newsSourceType + ", " + this.title + " says: " + this.news;
    }

    public String getTitle() {
        return title;
    }
}
