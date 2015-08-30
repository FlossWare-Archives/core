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

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ObjectUtil class.
 *
 * @author Scot P. Floess
 */
public class ObjectUtilTest {

    private static class StubClass {
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPackage_Class_null() {
        ObjectUtil.getPackage((Class) null);
    }

    @Test
    public void test_getPackage_Class() {
        Assert.assertEquals("Should be correct package", StubClass.class.getPackage().getName(), ObjectUtil.getPackage(StubClass.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPackage_Object_null() {
        ObjectUtil.getPackage((Object) null);
    }

    @Test
    public void test_getPackage_Object() {
        Assert.assertEquals("Should be correct package", StubClass.class.getPackage().getName(), ObjectUtil.getPackage(new StubClass()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getClass_null() {
        ObjectUtil.getClass((Object) null);
    }

    @Test
    public void test_getClass_Object() {
        Assert.assertEquals("Should be correct class", StubClass.class, ObjectUtil.getClass(new StubClass()));
    }
}
