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
package org.flossware.reflect.klass.accessible;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import org.flossware.reflect.klass.BaseClass;
import org.flossware.reflect.klass.ChildClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the abstract constructor class.
 *
 * @author sfloess
 */
public class AccessibleObjectUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_getAllAccessibles_null_AccessibleObjectMgr() {
        AccessibleObjectUtil.getAllAccessibles(null, null);
    }

    @Test
    public void test_getAllAccessibles_noClass() {
        final Collection<Field> noFields = AccessibleObjectUtil.getAllAccessibles(null, new FieldAccessibleMgr());
        Assert.assertTrue("Should be no fields", noFields.isEmpty());

        final Collection<Method> noMethods = AccessibleObjectUtil.getAllAccessibles(null, new MethodAccessibleMgr());
        Assert.assertTrue("Should be no methods", noMethods.isEmpty());

        final Collection<Constructor> noConstructors = AccessibleObjectUtil.getAllAccessibles(null, new ConstructorAccessibleMgr());
        Assert.assertTrue("Should be no constructos", noConstructors.isEmpty());
    }

    @Test
    public void test_getAllAccessibles_fields() {
        final Collection<Field> baseAccessibles = AccessibleObjectUtil.getAllAccessibles(BaseClass.class, new FieldAccessibleMgr());
        Assert.assertEquals("Should be correct number of fields", baseAccessibles.size(), BaseClass.class.getDeclaredFields().length);

        int index = 0;
        for (final Field accessible : baseAccessibles) {
            Assert.assertEquals("Should be same field", accessible, BaseClass.class.getDeclaredFields()[index++]);
        }

        final Collection<Field> allAccessibles = AccessibleObjectUtil.getAllAccessibles(ChildClass.class, new FieldAccessibleMgr());
        Assert.assertEquals("Should be correct number of fields", allAccessibles.size(), BaseClass.class.getDeclaredFields().length + ChildClass.class.getDeclaredFields().length);

        for (final Field accessible : BaseClass.class.getDeclaredFields()) {
            Assert.assertTrue("Should have found field", allAccessibles.remove(accessible));
        }

        for (final Field accessible : ChildClass.class.getDeclaredFields()) {
            Assert.assertTrue("Should have found field", allAccessibles.remove(accessible));
        }

        Assert.assertTrue("Should have found all fields", allAccessibles.isEmpty());
    }

    @Test
    public void test_getAllAccessibles_methods() {
        final Collection<Method> baseAccessibles = AccessibleObjectUtil.getAllAccessibles(BaseClass.class, new MethodAccessibleMgr());
        Assert.assertEquals("Should be correct number of methods", baseAccessibles.size(), Object.class.getDeclaredMethods().length + BaseClass.class.getDeclaredMethods().length);

        final Collection<Method> allAccessibles = AccessibleObjectUtil.getAllAccessibles(ChildClass.class, new MethodAccessibleMgr());
        Assert.assertEquals("Should be correct number of methods", allAccessibles.size(), Object.class.getDeclaredMethods().length + BaseClass.class.getDeclaredMethods().length + ChildClass.class.getDeclaredMethods().length);

        for (final Method accessible : Object.class.getDeclaredMethods()) {
            Assert.assertTrue("Should have found method", allAccessibles.remove(accessible));
        }

        for (final Method accessible : BaseClass.class.getDeclaredMethods()) {
            Assert.assertTrue("Should have found method", allAccessibles.remove(accessible));
        }

        for (final Method accessible : ChildClass.class.getDeclaredMethods()) {
            Assert.assertTrue("Should have found method", allAccessibles.remove(accessible));
        }

        Assert.assertTrue("Should have found all methods", allAccessibles.isEmpty());
    }

    @Test
    public void test_getAllAccessibles_constructors() {
        final Collection<Constructor> baseAccessibles = AccessibleObjectUtil.getAllAccessibles(BaseClass.class, new ConstructorAccessibleMgr());
        Assert.assertEquals("Should be correct number of constructors", baseAccessibles.size(), Object.class.getDeclaredConstructors().length + BaseClass.class.getDeclaredConstructors().length);

        final Collection<Constructor> allAccessibles = AccessibleObjectUtil.getAllAccessibles(ChildClass.class, new ConstructorAccessibleMgr());
        Assert.assertEquals("Should be correct number of constructors", allAccessibles.size(), Object.class.getDeclaredConstructors().length + BaseClass.class.getDeclaredConstructors().length + ChildClass.class.getDeclaredConstructors().length);

        for (final Constructor accessible : Object.class.getDeclaredConstructors()) {
            Assert.assertTrue("Should have found constructor", allAccessibles.remove(accessible));
        }

        for (final Constructor accessible : BaseClass.class.getDeclaredConstructors()) {
            Assert.assertTrue("Should have found constructor", allAccessibles.remove(accessible));
        }

        for (final Constructor accessible : ChildClass.class.getDeclaredConstructors()) {
            Assert.assertTrue("Should have found constructor", allAccessibles.remove(accessible));
        }

        Assert.assertTrue("Should have found all constructors", allAccessibles.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getAllAccessibles_NullAccessibleMgr() {
        AccessibleObjectUtil.getAllAccessibles(null, null, new ArrayList<Field>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getAllAccessibles_NullCollection() {
        AccessibleObjectUtil.getAllAccessibles(null, new FieldAccessibleMgr(), null);
    }

    @Test
    public void test_getAllAccessibles_Collection_NullClass() {
        final Collection<Field> noFields = AccessibleObjectUtil.getAllAccessibles(null, new FieldAccessibleMgr(), new ArrayList<Field>());
        Assert.assertTrue("Should be no fields", noFields.isEmpty());

        final Collection<Method> noMethods = AccessibleObjectUtil.getAllAccessibles(null, new MethodAccessibleMgr(), new ArrayList<Method>());
        Assert.assertTrue("Should be no methods", noMethods.isEmpty());

        final Collection<Constructor> noConstructors = AccessibleObjectUtil.getAllAccessibles(null, new ConstructorAccessibleMgr(), new ArrayList<Constructor>());
        Assert.assertTrue("Should be no constructos", noConstructors.isEmpty());
    }

    @Test
    public void test_getAllAccessibles_Collection_fields() {
        final Collection<Field> baseAccessibles = new ArrayList<>();

        AccessibleObjectUtil.getAllAccessibles(BaseClass.class, new FieldAccessibleMgr(), baseAccessibles);
        Assert.assertEquals("Should be correct number of fields", baseAccessibles.size(), BaseClass.class.getDeclaredFields().length);

        int index = 0;
        for (final Field accessible : baseAccessibles) {
            Assert.assertEquals("Should be same field", accessible, BaseClass.class.getDeclaredFields()[index++]);
        }

        final Collection<Field> allAccessibles = new ArrayList<>();

        AccessibleObjectUtil.getAllAccessibles(ChildClass.class, new FieldAccessibleMgr(), allAccessibles);
        Assert.assertEquals("Should be correct number of fields", allAccessibles.size(), BaseClass.class.getDeclaredFields().length + ChildClass.class.getDeclaredFields().length);

        for (final Field accessible : BaseClass.class.getDeclaredFields()) {
            Assert.assertTrue("Should have found field", allAccessibles.remove(accessible));
        }

        for (final Field accessible : ChildClass.class.getDeclaredFields()) {
            Assert.assertTrue("Should have found field", allAccessibles.remove(accessible));
        }

        Assert.assertTrue("Should have found all fields", allAccessibles.isEmpty());
    }

    @Test
    public void test_getAllAccessibles_Collection_methods() {
        final Collection<Method> baseAccessibles = new ArrayList<>();

        AccessibleObjectUtil.getAllAccessibles(BaseClass.class, new MethodAccessibleMgr(), baseAccessibles);
        Assert.assertEquals("Should be correct number of methods", baseAccessibles.size(), Object.class.getDeclaredMethods().length + BaseClass.class.getDeclaredMethods().length);

        final Collection<Method> allAccessibles = new ArrayList<>();
        AccessibleObjectUtil.getAllAccessibles(ChildClass.class, new MethodAccessibleMgr(), allAccessibles);
        Assert.assertEquals("Should be correct number of methods", allAccessibles.size(), Object.class.getDeclaredMethods().length + BaseClass.class.getDeclaredMethods().length + ChildClass.class.getDeclaredMethods().length);

        for (final Method accessible : Object.class.getDeclaredMethods()) {
            Assert.assertTrue("Should have found method", allAccessibles.remove(accessible));
        }

        for (final Method accessible : BaseClass.class.getDeclaredMethods()) {
            Assert.assertTrue("Should have found method", allAccessibles.remove(accessible));
        }

        for (final Method accessible : ChildClass.class.getDeclaredMethods()) {
            Assert.assertTrue("Should have found method", allAccessibles.remove(accessible));
        }

        Assert.assertTrue("Should have found all methods", allAccessibles.isEmpty());
    }

    @Test
    public void test_getAllAccessibles_Collection_constructors() {
        final Collection<Constructor> baseAccessibles = new ArrayList<>();
        AccessibleObjectUtil.getAllAccessibles(BaseClass.class, new ConstructorAccessibleMgr(), baseAccessibles);
        Assert.assertEquals("Should be correct number of constructors", baseAccessibles.size(), Object.class.getDeclaredConstructors().length + BaseClass.class.getDeclaredConstructors().length);

        final Collection<Constructor> allAccessibles = new ArrayList<>();
        AccessibleObjectUtil.getAllAccessibles(ChildClass.class, new ConstructorAccessibleMgr(), allAccessibles);
        Assert.assertEquals("Should be correct number of constructors", allAccessibles.size(), Object.class.getDeclaredConstructors().length + BaseClass.class.getDeclaredConstructors().length + ChildClass.class.getDeclaredConstructors().length);

        for (final Constructor accessible : Object.class.getDeclaredConstructors()) {
            Assert.assertTrue("Should have found constructor", allAccessibles.remove(accessible));
        }

        for (final Constructor accessible : BaseClass.class.getDeclaredConstructors()) {
            Assert.assertTrue("Should have found constructor", allAccessibles.remove(accessible));
        }

        for (final Constructor accessible : ChildClass.class.getDeclaredConstructors()) {
            Assert.assertTrue("Should have found constructor", allAccessibles.remove(accessible));
        }

        Assert.assertTrue("Should have found all constructors", allAccessibles.isEmpty());
    }
}
