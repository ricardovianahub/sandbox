package behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionOne implements MyCollection {
    private List objects;

    public MyCollectionOne() {
        this.objects = new ArrayList();
    }

    @Override
    public void add(Object o) {
        objects.add(o);
    }

    @Override
    public MyIterator newIterator() {
        return new MyIt(this);
    }

    private static class MyIt implements MyIterator {
        private final MyCollectionOne myCollectionOne;
        private int index;

        public MyIt(MyCollectionOne myCollectionOne) {
            this.myCollectionOne = myCollectionOne;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < myCollectionOne.objects.size();
        }

        @Override
        public Object next() {
            return myCollectionOne.objects.get(index++);
        }
    }
}
