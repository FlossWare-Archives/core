/*
 * Copyright (C) 2015 sfloess
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
package org.flossware.collections.collection;

import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the AbstractFunctionalCollection base class.
 *
 * @author sfloess
 */
public class DefaultFunctionalCollectionTest {

    private DefaultFunctionalCollection<String> functionalCollection;

    @Before
    public void init() {
        functionalCollection = new DefaultFunctionalCollection(new TreeSet());
    }

    @Test
    public void test_addF() {
        functionalCollection.addF("9").addF("7").addF("6");

        Assert.assertEquals("Should have three elements", 3, functionalCollection.size());
        Assert.assertTrue("Should have found the element", functionalCollection.remove("7"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("9"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("6"));
        Assert.assertTrue("Should be no elements", functionalCollection.isEmpty());
    }

    @Test
    public void test_removeF() {
        functionalCollection.addF("9").addF("7").addF("6");
        functionalCollection.removeF("1").removeF("7").removeF("6").removeF("9");

        Assert.assertTrue("Should be no elements", functionalCollection.isEmpty());
    }

    @Test
    public void test_addAllF() {
        TreeSet set1 = new TreeSet();
        set1.add("1");
        set1.add("2");

        TreeSet set2 = new TreeSet();
        set2.add("3");
        set2.add("4");

        functionalCollection.addAllF(set1).addAllF(set2);
        Assert.assertEquals("Should be elements", set1.size() + set2.size(), functionalCollection.size());

        Assert.assertTrue("Should have found the element", functionalCollection.remove("2"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("4"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("3"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("1"));
        Assert.assertTrue("Should be no elements", functionalCollection.isEmpty());
    }

    @Test
    public void test_removeAllF() {
        TreeSet set1 = new TreeSet();
        set1.add("1");
        set1.add("2");

        TreeSet set2 = new TreeSet();
        set2.add("3");
        set2.add("4");

        functionalCollection.addAllF(set1).addAllF(set2);
        Assert.assertEquals("Should be elements", set1.size() + set2.size(), functionalCollection.size());

        functionalCollection.removeAllF(set1).addAllF(set2);
        Assert.assertEquals("Should be elements", set2.size(), functionalCollection.size());

        functionalCollection.removeAllF(set1);
        Assert.assertEquals("Should be elements", set2.size(), functionalCollection.size());

        functionalCollection.removeAllF(set2);
        Assert.assertTrue("Should be no elements", functionalCollection.isEmpty());
    }
}
