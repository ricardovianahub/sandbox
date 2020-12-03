package others;

public final class ShopClient {
    private final String first;
    private final String last;

    public ShopClient(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String fullName() {
        return "This is " + last + ", " + first;
    }

    public static class Visitor {
        public String visit(ShopClient shopClient) {
            return shopClient.first;
        }
    }
}
