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

import java.lang.reflect.Field;
import org.flossware.reflect.klass.BaseClass;
import org.flossware.reflect.klass.ChildClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the FieldAccessibleMgr class.
 *
 * @author sfloess
 */
public class FieldAccessibleMgrTest {

    @Test
    public void test_getAccessibles() {
        final Field[] baseFields = new FieldAccessibleMgr().getAccessibles(BaseClass.class);

        Assert.assertEquals("Should be correct number of fields", baseFields.length, BaseClass.class.getDeclaredFields().length);

        for (int index = 0; index < baseFields.length; index++) {
            Assert.assertEquals("Should be same field", baseFields[index], BaseClass.class.getDeclaredFields()[index]);
        }

        final Field[] childFields = new FieldAccessibleMgr().getAccessibles(ChildClass.class);

        Assert.assertEquals("Should be correct number of fields", childFields.length, ChildClass.class.getDeclaredFields().length);

        for (int index = 0; index < childFields.length; index++) {
            Assert.assertEquals("Should be same field", childFields[index], ChildClass.class.getDeclaredFields()[index]);
        }
    }
}
