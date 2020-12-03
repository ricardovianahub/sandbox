package behavioral.iterator;

public class MyCollectionTwo implements MyCollection {

    private Object[] objects;
    private int size;

    public MyCollectionTwo(int totalSize) {
        this.objects = new Object[totalSize];
        this.size = 0;
    }

    @Override
    public void add(Object o) {
        this.objects[this.size++] = o;
    }

    @Override
    public MyIterator newIterator() {
        return new MyIt(this);
    }

    private static class MyIt implements MyIterator {
        private final MyCollectionTwo myCollectionTwo;
        private int pointer;

        public MyIt(MyCollectionTwo myCollectionTwo) {
            this.myCollectionTwo = myCollectionTwo;
            this.pointer = 0;
        }

        @Override
        public Object next() {
            return this.myCollectionTwo.objects[this.pointer++];
        }

        @Override
        public boolean hasNext() {
            return this.pointer < this.myCollectionTwo.size;
        }
    }
}
