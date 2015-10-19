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
package org.flossware.reflect.klass.filter.annotation;

import java.lang.reflect.Constructor;
import org.flossware.reflect.klass.BaseClass;
import org.flossware.reflect.klass.ConstructorOneAnnotation;
import org.flossware.reflect.klass.ConstructorTwoAnnotation;
import org.flossware.reflect.klass.GeneralAnnotation;
import org.flossware.reflect.klass.MethodOneAnnotation;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ConstructorAnnotationFilter class.
 *
 * @author sfloess
 */
public class ConstructorAnnotationFilterTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_accept_null() {
        new ConstructorAnnotationFilter().accept(null, null);
    }

    @Test
    public void test_accept() throws Exception {
        final Constructor constructor = BaseClass.class.getDeclaredConstructor(Integer.class);

        Assert.assertFalse("Should not have annotation", new ConstructorAnnotationFilter().accept(constructor, MethodOneAnnotation.class));
        Assert.assertTrue("Should have annotation", new ConstructorAnnotationFilter().accept(constructor, GeneralAnnotation.class));
        Assert.assertTrue("Should have annotation", new ConstructorAnnotationFilter().accept(constructor, ConstructorOneAnnotation.class));
        Assert.assertFalse("Should not have annotation", new ConstructorAnnotationFilter().accept(constructor, ConstructorTwoAnnotation.class));
    }
}
