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
import org.flossware.reflect.klass.BaseClass;
import org.flossware.reflect.klass.ChildClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ConstructorAccessibleMgr class.
 *
 * @author sfloess
 */
public class ConstructorAccessibleMgrTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_getAccessibles_null() {
        new ConstructorAccessibleMgr().getAccessibles(null);
    }

    @Test
    public void test_getAccessibles() {
        final Constructor[] baseConstructors = new ConstructorAccessibleMgr().getAccessibles(BaseClass.class);

        Assert.assertEquals("Should be correct number of constructors", baseConstructors.length, BaseClass.class.getDeclaredConstructors().length);

        for (int index = 0; index < baseConstructors.length; index++) {
            Assert.assertEquals("Should be same constructor", baseConstructors[index], BaseClass.class.getDeclaredConstructors()[index]);
        }

        final Constructor[] childConstructors = new ConstructorAccessibleMgr().getAccessibles(ChildClass.class);

        Assert.assertEquals("Should be correct number of constructors", childConstructors.length, ChildClass.class.getDeclaredConstructors().length);

        for (int index = 0; index < childConstructors.length; index++) {
            Assert.assertEquals("Should be same constructor", childConstructors[index], ChildClass.class.getDeclaredConstructors()[index]);
        }
    }
}
