/*
 * Copyright (C) 2014 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.flossware.common.Filter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the StringUtil class.
 *
 * @author Scot P. Floess
 */
public class CollectionUtilTest {

    // For testing only...
    static final class StringComparator implements Comparator<String> {

        @Override
        public int compare(final String t, final String t1) {
            return t.compareTo(t1);
        }
    }

    static final class DummyClass {

        public void methodOne(final int p) {
        }

        public void methodTwo(final String s) {
        }

        public void methodThree(final long l) {
        }
    }

    static final class EndsWithMethodFilter implements Filter<Method, String> {

        @Override
        public boolean accept(Method toFilter, String value) {
            return toFilter.getName().endsWith(value);
        }
    }

    static final class StartsWithMethodFilter implements Filter<Method, String> {

        @Override
        public boolean accept(Method toFilter, String value) {
            return toFilter.getName().startsWith(value);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addValue_null() {
        CollectionUtil.addValue(null, "Foo", false);
    }

    @Test
    public void test_addValue() {
        Set<String> set = new TreeSet<>();

        CollectionUtil.addValue(set, "Foo", false);

        Assert.assertTrue("Should not have added", set.isEmpty());

        CollectionUtil.addValue(set, "Bar", true);

        Assert.assertFalse("Should have added", set.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_asList_null() {
        CollectionUtil.asList(null);
    }

    @Test()
    public void test_asList() {
        final Set<String> toCopy = new TreeSet<>();

        toCopy.add("One");
        toCopy.add("Two");
        toCopy.add("Three");

        final List<String> theCopy = CollectionUtil.asList(toCopy);

        Assert.assertEquals("Sould have same number", toCopy.size(), theCopy.size());
        Assert.assertNotSame("Should be a new copy", toCopy, theCopy);

        for (final String str : theCopy) {
            Assert.assertTrue("Should have removed", toCopy.remove(str));
        }

        Assert.assertTrue("Should have found all values", toCopy.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_asSet_null() {
        CollectionUtil.asSet(null);
    }

    @Test()
    public void test_asSet() {
        final List<String> toCopy = new ArrayList<>();

        toCopy.add("One");
        toCopy.add("Two");
        toCopy.add("Three");

        final Set<String> theCopy = CollectionUtil.asSet(toCopy);

        Assert.assertEquals("Sould have same number", toCopy.size(), theCopy.size());
        Assert.assertNotSame("Should be a new copy", toCopy, theCopy);

        for (final String str : toCopy) {
            Assert.assertTrue("Should have removed", theCopy.remove(str));
        }

        Assert.assertTrue("Should have found all values", theCopy.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_sort_nullCollection() {
        CollectionUtil.sort(null, new StringComparator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_sort_nullComparator() {
        CollectionUtil.sort(new TreeSet(), null);
    }

    @Test()
    public void test_sort() {
        final List<String> toSort = new ArrayList<>();
        toSort.add("One");
        toSort.add("Two");
        toSort.add("Three");

        final Collection<String> sorted = CollectionUtil.sort(toSort, new StringComparator());

        Assert.assertEquals("Should have same number", toSort.size(), sorted.size());

        Assert.assertEquals("Should be correct value", "One", sorted.toArray()[0]);
        Assert.assertEquals("Should be correct value", "Three", sorted.toArray()[1]);
        Assert.assertEquals("Should be correct value", "Two", sorted.toArray()[2]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_filter_nullCollection() {
        CollectionUtil.filter((Collection) null, new EndsWithMethodFilter(), "foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_filter_collection_nullFilter() {
        CollectionUtil.filter(new TreeSet(), null, "foo");
    }

    @Test
    public void test_filter_collection() {
        final Collection<Method> endsWithCollection1 = CollectionUtil.filter(Arrays.asList(DummyClass.class.getMethods()), new EndsWithMethodFilter(), "One");
        Assert.assertFalse("Should have values", endsWithCollection1.isEmpty());
        Assert.assertEquals("Should have 1 value", 1, endsWithCollection1.size());
        Assert.assertEquals("Should have value", "methodOne", ((Method) endsWithCollection1.toArray()[0]).getName());

        final Collection<Method> endsWithCollection2 = CollectionUtil.filter(Arrays.asList(DummyClass.class.getMethods()), new EndsWithMethodFilter(), "" + System.currentTimeMillis());
        Assert.assertTrue("Should have no values", endsWithCollection2.isEmpty());

        final Collection<Method> startsWithCollection1 = CollectionUtil.filter(Arrays.asList(DummyClass.class.getMethods()), new StartsWithMethodFilter(), "me");
        Assert.assertFalse("Should have values", startsWithCollection1.isEmpty());
        Assert.assertEquals("Should have correct number of values", 3, startsWithCollection1.size());
        for (Method m : DummyClass.class.getMethods()) {
            if (m.getName().startsWith("me")) {
                Assert.assertTrue("Should have found value [" + m.getName() + "]", startsWithCollection1.contains(m));
            }
        }

        final Collection<Method> startsWithCollection2 = CollectionUtil.filter(Arrays.asList(DummyClass.class.getMethods()), new StartsWithMethodFilter(), "" + System.currentTimeMillis());
        Assert.assertTrue("Should have no values", startsWithCollection2.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_void_filter_collection_nullResults() {
        CollectionUtil.filter((Collection) null, new EndsWithMethodFilter(), "foo", null);
    }

    @Test
    public void test_void_filter_collection() {
        final Collection<Method> endsWithCollection1 = new ArrayList<>();

        CollectionUtil.filter(Arrays.asList(DummyClass.class.getMethods()), new EndsWithMethodFilter(), "One", endsWithCollection1);
        Assert.assertFalse("Should have values", endsWithCollection1.isEmpty());
        Assert.assertEquals("Should have 1 value", 1, endsWithCollection1.size());
        Assert.assertEquals("Should have value", "methodOne", ((Method) endsWithCollection1.toArray()[0]).getName());

        final Collection<Method> endsWithCollection2 = new ArrayList<>();
        CollectionUtil.filter(Arrays.asList(DummyClass.class.getMethods()), new EndsWithMethodFilter(), "" + System.currentTimeMillis(), endsWithCollection2);
        Assert.assertTrue("Should have no values", endsWithCollection2.isEmpty());

        final Collection<Method> startsWithCollection1 = new ArrayList<>();

        CollectionUtil.filter(Arrays.asList(DummyClass.class.getMethods()), new StartsWithMethodFilter(), "me", startsWithCollection1);
        Assert.assertFalse("Should have values", startsWithCollection1.isEmpty());
        Assert.assertEquals("Should have correct number of values", 3, startsWithCollection1.size());
        for (Method m : DummyClass.class.getMethods()) {
            if (m.getName().startsWith("me")) {
                Assert.assertTrue("Should have found value [" + m.getName() + "]", startsWithCollection1.contains(m));
            }
        }

        final Collection<Method> startsWithCollection2 = new ArrayList<>();
        CollectionUtil.filter(Arrays.asList(DummyClass.class.getMethods()), new StartsWithMethodFilter(), "" + System.currentTimeMillis(), startsWithCollection2);
        Assert.assertTrue("Should have no values", startsWithCollection2.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_filter_nullArray() {
        CollectionUtil.filter((Method[]) null, new EndsWithMethodFilter(), "foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_filter_array_nullFilter() {
        CollectionUtil.filter(new Method[0], null, "foo");
    }

    @Test
    public void test_filter_array() {
        final Collection<Method> endsWithCollection1 = CollectionUtil.filter(DummyClass.class.getMethods(), new EndsWithMethodFilter(), "One");
        Assert.assertFalse("Should have values", endsWithCollection1.isEmpty());
        Assert.assertEquals("Should have 1 value", 1, endsWithCollection1.size());
        Assert.assertEquals("Should have value", "methodOne", ((Method) endsWithCollection1.toArray()[0]).getName());

        final Collection<Method> endsWithCollection2 = CollectionUtil.filter(DummyClass.class.getMethods(), new EndsWithMethodFilter(), "" + System.currentTimeMillis());
        Assert.assertTrue("Should have no values", endsWithCollection2.isEmpty());

        final Collection<Method> startsWithCollection1 = CollectionUtil.filter(DummyClass.class.getMethods(), new StartsWithMethodFilter(), "me");
        Assert.assertFalse("Should have values", startsWithCollection1.isEmpty());
        Assert.assertEquals("Should have correct number of values", 3, startsWithCollection1.size());
        for (Method m : DummyClass.class.getMethods()) {
            if (m.getName().startsWith("me")) {
                Assert.assertTrue("Should have found value [" + m.getName() + "]", startsWithCollection1.contains(m));
            }
        }

        final Collection<Method> startsWithCollection2 = CollectionUtil.filter(DummyClass.class.getMethods(), new StartsWithMethodFilter(), "" + System.currentTimeMillis());
        Assert.assertTrue("Should have no values", startsWithCollection2.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_void_filter_array_nullResults() {
        CollectionUtil.filter((Method[]) null, new EndsWithMethodFilter(), "foo", null);
    }

    @Test
    public void test_void_filter_array() {
        final Collection<Method> endsWithCollection1 = new ArrayList<>();

        CollectionUtil.filter(DummyClass.class.getMethods(), new EndsWithMethodFilter(), "One", endsWithCollection1);
        Assert.assertFalse("Should have values", endsWithCollection1.isEmpty());
        Assert.assertEquals("Should have 1 value", 1, endsWithCollection1.size());
        Assert.assertEquals("Should have value", "methodOne", ((Method) endsWithCollection1.toArray()[0]).getName());

        final Collection<Method> endsWithCollection2 = new ArrayList<>();
        CollectionUtil.filter(DummyClass.class.getMethods(), new EndsWithMethodFilter(), "" + System.currentTimeMillis(), endsWithCollection2);
        Assert.assertTrue("Should have no values", endsWithCollection2.isEmpty());

        final Collection<Method> startsWithCollection1 = new ArrayList<>();

        CollectionUtil.filter(DummyClass.class.getMethods(), new StartsWithMethodFilter(), "me", startsWithCollection1);
        Assert.assertFalse("Should have values", startsWithCollection1.isEmpty());
        Assert.assertEquals("Should have correct number of values", 3, startsWithCollection1.size());
        for (Method m : DummyClass.class.getMethods()) {
            if (m.getName().startsWith("me")) {
                Assert.assertTrue("Should have found value [" + m.getName() + "]", startsWithCollection1.contains(m));
            }
        }

        final Collection<Method> startsWithCollection2 = new ArrayList<>();
        CollectionUtil.filter(DummyClass.class.getMethods(), new StartsWithMethodFilter(), "" + System.currentTimeMillis(), startsWithCollection2);
        Assert.assertTrue("Should have no values", startsWithCollection2.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_contains_nullCollection() {
        CollectionUtil.filter((Collection) null, new EndsWithMethodFilter(), "foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_contains_collection_nullFilter() {
        CollectionUtil.filter(new TreeSet(), null, "foo");
    }

    @Test
    public void test_contains_collection() {
        Assert.assertTrue("Should contain method one", CollectionUtil.contains(Arrays.asList(DummyClass.class.getMethods()), new EndsWithMethodFilter(), "One"));
        Assert.assertFalse("Should NOT contain the method", CollectionUtil.contains(Arrays.asList(DummyClass.class.getMethods()), new EndsWithMethodFilter(), "" + System.currentTimeMillis()));
        Assert.assertTrue("Should contain methods name tarting with 'me'", CollectionUtil.contains(Arrays.asList(DummyClass.class.getMethods()), new StartsWithMethodFilter(), "me"));
        Assert.assertFalse("Should NOT contain any methods", CollectionUtil.contains(Arrays.asList(DummyClass.class.getMethods()), new StartsWithMethodFilter(), "" + System.currentTimeMillis()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_contains_array_nullCollection() {
        CollectionUtil.filter((Method[]) null, new EndsWithMethodFilter(), "foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_contains_array_nullFilter() {
        CollectionUtil.filter(new Method[0], null, "foo");
    }

    @Test
    public void test_contains_array() {
        Assert.assertTrue("Should contain method one", CollectionUtil.contains(DummyClass.class.getMethods(), new EndsWithMethodFilter(), "One"));
        Assert.assertFalse("Should NOT contain the method", CollectionUtil.contains(DummyClass.class.getMethods(), new EndsWithMethodFilter(), "" + System.currentTimeMillis()));
        Assert.assertTrue("Should contain methods name tarting with 'me'", CollectionUtil.contains(DummyClass.class.getMethods(), new StartsWithMethodFilter(), "me"));
        Assert.assertFalse("Should NOT contain any methods", CollectionUtil.contains(DummyClass.class.getMethods(), new StartsWithMethodFilter(), "" + System.currentTimeMillis()));
    }
}
