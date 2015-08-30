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
package org.flossware.xml.jaxb;

import java.util.Collections;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import org.flossware.core.xml.ObjectFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the JaxbUtil class.
 *
 * @author sfloess
 */
public class TestJaxbUtil {

    private static class StubClass {
    }

    private static final QName TEST_QNAME = new QName("foo");
    private static final Class<StubClass> STUB_CLASS = StubClass.class;

    @Test(expected = IllegalArgumentException.class)
    public void test_createJaxbContext_Class_Map_null() throws JAXBException {
        JaxbUtil.createJaxbContext((Class) null, Collections.EMPTY_MAP);
    }

    @Test
    public void test_createJaxbContext_Class_Map() throws JAXBException {
        JaxbUtil.createJaxbContext(ObjectFactory.class, Collections.EMPTY_MAP);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createJaxbContext_Class_null() throws JAXBException {
        JaxbUtil.createJaxbContext((Class) null);
    }

    @Test
    public void test_createJaxbContext_Class() throws JAXBException {
        JaxbUtil.createJaxbContext(ObjectFactory.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getDeclaredTypeClass_null() {
        JaxbUtil.getDeclaredTypeClass(null);
    }

    @Test
    public void test_getDeclaredTypeClass() {
        Assert.assertEquals("Should be the correct class!", STUB_CLASS, JaxbUtil.getDeclaredTypeClass(new JAXBElement(TEST_QNAME, STUB_CLASS, new StubClass())));
    }

    @Test
    public void test_isJaxbElement() {
        Assert.assertFalse("Should not be a JAXBElement", JaxbUtil.isJaxbElement(null));
        Assert.assertFalse("Should not be a JAXBElement", JaxbUtil.isJaxbElement(TEST_QNAME));
        Assert.assertTrue("Should be a JAXBElement!", JaxbUtil.isJaxbElement(new JAXBElement(TEST_QNAME, STUB_CLASS, new StubClass())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_computeValue_JAXBElement_null() {
        JaxbUtil.computeValue((JAXBElement) null);
    }

    @Test
    public void test_computeValue_JAXBElement() {
        final StubClass stubClass = new StubClass();
        final JAXBElement jaxbElement = new JAXBElement(TEST_QNAME, STUB_CLASS, stubClass);

        Assert.assertSame("Should be the same value", stubClass, JaxbUtil.computeValue(jaxbElement));
        Assert.assertNotSame("Should not be the same value", stubClass, new JAXBElement(TEST_QNAME, STUB_CLASS, new StubClass()));
    }

    @Test
    public void test_computeValue_Object_null() {
        Assert.assertNull("Should be null", JaxbUtil.computeValue((Object) null));

        final StubClass stubClass = new StubClass();
        final Object object = new JAXBElement(TEST_QNAME, STUB_CLASS, stubClass);

        Assert.assertSame("Should be the same value", stubClass, JaxbUtil.computeValue(object));
    }
}
