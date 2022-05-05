package com.shalimov.collection.list.linkedlist;

import com.shalimov.collection.list.AbstractListTest;
import com.shalimov.collection.list.List;


public class LinkedListTest extends AbstractListTest {


    @Override
    public List<String> getListImpl() {
        return new LinkedList<>();
    }
}

