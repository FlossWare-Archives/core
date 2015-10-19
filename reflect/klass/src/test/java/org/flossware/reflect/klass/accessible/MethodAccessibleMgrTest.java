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

import java.lang.reflect.Method;
import org.flossware.reflect.klass.BaseClass;
import org.flossware.reflect.klass.ChildClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the MethodAccessibleMgr class.
 *
 * @author sfloess
 */
public class MethodAccessibleMgrTest {

    @Test
    public void test_getAccessibles() {
        final Method[] baseMethods = new MethodAccessibleMgr().getAccessibles(BaseClass.class);

        Assert.assertEquals("Should be correct number of methods", baseMethods.length, BaseClass.class.getDeclaredMethods().length);

        for (int index = 0; index < baseMethods.length; index++) {
            Assert.assertEquals("Should be same method", baseMethods[index], BaseClass.class.getDeclaredMethods()[index]);
        }

        final Method[] childMethods = new MethodAccessibleMgr().getAccessibles(ChildClass.class);

        Assert.assertEquals("Should be correct number of methods", childMethods.length, ChildClass.class.getDeclaredMethods().length);

        for (int index = 0; index < childMethods.length; index++) {
            Assert.assertEquals("Should be same method", childMethods[index], ChildClass.class.getDeclaredMethods()[index]);
        }
    }
}
