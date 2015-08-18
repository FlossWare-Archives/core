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
package org.flossware.collections.map;

import java.util.Map;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the AbstractFunctionalMap base class.
 *
 * @author sfloess
 */
public class AbstractFunctionalMapTest {

    class StubFunctionalMap extends AbstractFunctionalMap<String, String> {

        Map<String, String> map = new TreeMap();

        @Override
        protected Map<String, String> getMap() {
            return map;
        }
    }

    private StubFunctionalMap functionalMap;

    @Before
    public void init() {
        functionalMap = new StubFunctionalMap();
    }

    @Test
    public void test_putF() {
        functionalMap.putF("key1", "val1").putF("key2", "val2").putF("key3", "val3");

        Assert.assertEquals("Should have three elements", 3, functionalMap.size());
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key2"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key1"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key3"));
        Assert.assertNotNull("Should be no elements", functionalMap.isEmpty());
    }

    @Test
    public void test_removeF() {
        functionalMap.putF("key1", "val1").putF("key2", "val2").putF("key3", "val3");
        functionalMap.removeF("key1").removeF("key3").removeF("key2").removeF("9");

        Assert.assertTrue("Should be no elements", functionalMap.isEmpty());
    }

    @Test
    public void test_putAllF() {
        TreeMap map1 = new TreeMap();
        map1.put("key1", "val1");
        map1.put("key2", "val2");

        TreeMap map2 = new TreeMap();
        map2.put("key3", "val3");
        map2.put("key4", "val4");

        functionalMap.putAllF(map1).putAllF(map2);
        Assert.assertEquals("Should be elements", map1.size() + map2.size(), functionalMap.size());

        Assert.assertNotNull("Should have found the element", functionalMap.remove("key2"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key4"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key3"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key1"));

        Assert.assertTrue("Should be no elements", functionalMap.isEmpty());
    }
}
