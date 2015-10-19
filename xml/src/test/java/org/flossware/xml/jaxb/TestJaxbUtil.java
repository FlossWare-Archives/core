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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import org.flossware.core.xml.NameType;
import org.flossware.core.xml.ObjectFactory;
import org.flossware.core.xml.PersonType;
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
    public void test_getFactoryMethods_null() throws JAXBException {
        JaxbUtil.getFactoryMethods(null);
    }

    @Test
    public void test_getFactoryMethods() throws JAXBException {
        final Collection<Method> methods = JaxbUtil.getFactoryMethods(ObjectFactory.class);

        Assert.assertFalse("Should have methods", methods.isEmpty());

        final Collection<Method> missingMethods = new ArrayList<>();

        for (final Method method : methods) {
            if (!"createName".equals(method.getName()) && !"createPerson".equals(method.getName())) {
                missingMethods.add(method);
            }
        }

        Assert.assertTrue("Should be no missing methods", missingMethods.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getFactoryMethodMap_null() throws JAXBException {
        JaxbUtil.getFactoryMethodMap(null);
    }

    @Test
    public void test_getFactoryMethodMap() throws JAXBException {
        final Map<Class, Method> methodMap = JaxbUtil.getFactoryMethodMap(ObjectFactory.class);

        Assert.assertEquals("Should be two methods", 2, methodMap.size());
        Assert.assertTrue("Should have found method", methodMap.keySet().contains(NameType.class));
        Assert.assertTrue("Should have found method", methodMap.keySet().contains(PersonType.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createObjectFactory_null() throws JAXBException {
        JaxbUtil.createObjectFactory(null);
    }

    @Test
    public void test_createObjectFactory() throws JAXBException {
        Assert.assertNotNull("Should have created an object factory", JaxbUtil.createObjectFactory(ObjectFactory.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createJaxbElement_Null_Map() throws JAXBException {
        JaxbUtil.createJaxbElement(this, null, this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createJaxbElement_Empty_Map() throws JAXBException {
        JaxbUtil.createJaxbElement(this, Collections.EMPTY_MAP, this);
    }

    @Test
    public void test_createJaxbElement() throws JAXBException {
        final PersonType pt = new PersonType();
        final JAXBElement element = JaxbUtil.createJaxbElement(new ObjectFactory(), JaxbUtil.getFactoryMethodMap(ObjectFactory.class), pt);

        Assert.assertNotNull("Should have created the element", element);
        Assert.assertEquals("Should be the same type", pt, element.getValue());
    }

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
        Assert.assertNotNull("Should have instantiated a JAXBContext", JaxbUtil.createJaxbContext(ObjectFactory.class));
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

    @Test(expected = IllegalArgumentException.class)
    public void test_setProperty_null() throws JAXBException {
        JaxbUtil.setProperty(null, Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    @Test
    public void test_setProperty() throws JAXBException {
        final JAXBContext jaxbContext = JaxbUtil.createJaxbContext(ObjectFactory.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();

        final Marshaller compareMarshaller = JaxbUtil.setProperty(marshaller, Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Assert.assertSame("Should be the same marshaller", marshaller, compareMarshaller);
        Assert.assertEquals("Property should be set", Boolean.TRUE, compareMarshaller.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT));
        Assert.assertNull("Property should not be set", compareMarshaller.getProperty(Marshaller.JAXB_SCHEMA_LOCATION));
    }
}
