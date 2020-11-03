package creational.builder;

public class ComplexObjectJoshuaBlock {
    private final String first;
    private final String second;
    private final String third;

    private ComplexObjectJoshuaBlock(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public String getThird() {
        return third;
    }

    public static class Builder {
        private String first;
        private String second;
        private String third;

        public ComplexObjectJoshuaBlock build() {
            return new ComplexObjectJoshuaBlock(first, second, third);
        }

        public Builder withFirst(String first) {
            this.first = first;
            return this;
        }

        public Builder withSecond(String second) {
            this.second = second;
            return this;
        }

        public Builder withThird(String third) {
            this.third = third;
            return this;
        }
    }
}
