package com.shalimov.collection.list.arraylist;
import com.shalimov.collection.list.AbstractListTest;
import com.shalimov.collection.list.List;


public class ArrayListTest extends AbstractListTest {

    @Override
    public List<String> getListImpl() {
        return new ArrayList<>();
    }
}
