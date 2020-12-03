package behavioral.iterator;

public class MyCollectionThree implements MyCollection {
    public static final String SEPARATOR = "[X]";
    private StringBuilder sb;

    public MyCollectionThree() {
        this.sb = new StringBuilder(SEPARATOR);
    }

    @Override
    public void add(Object o) {
        this.sb.append(o);
        this.sb.append(SEPARATOR);
    }

    @Override
    public MyIterator newIterator() {
        return new Iter(this);
    }

    private static class Iter implements MyIterator {
        private final MyCollectionThree myCollectionThree;
        private int index;

        public Iter(MyCollectionThree myCollectionThree) {
            this.myCollectionThree = myCollectionThree;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return !"".equals(this.myCollectionThree.sb.toString().substring(index + SEPARATOR.length()));
        }

        @Override
        public Object next() {
            String string = this.myCollectionThree.sb.toString();
            int nextIndex = string.indexOf(SEPARATOR, index + SEPARATOR.length());
            String result = string.substring(index + SEPARATOR.length(), nextIndex);
            this.index = nextIndex;
            return result;
        }
    }
}
