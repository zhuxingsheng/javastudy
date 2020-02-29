package com.jack.collection;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by Jack on 2018/10/10.
 */
public class SetTest {

    public static void main(String[] args) {
        Set<Long> sets = Sets.newHashSet();
        sets.add(123456789l);
        sets.add(123456789l);
        sets.add(new Long(123456789l));
        System.out.println(sets.size());
    }
}