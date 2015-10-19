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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.flossware.core.xml.NameType;
import org.flossware.core.xml.ObjectFactory;
import org.flossware.core.xml.PersonType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the JaxbSerialization class.
 *
 * @author sfloess
 */
public class TestJaxbSerialization {

    @Test(expected = JAXBException.class)
    public void test_constructor_class_map_badClass() throws JAXBException {
        new JaxbSerialization(getClass(), Collections.EMPTY_MAP);
    }

    @Test
    public void test_constructor_class_map() throws JAXBException {
        Assert.assertNotNull("Should have a JAXBContext", new JaxbSerialization(ObjectFactory.class, Collections.EMPTY_MAP).getJaxbContext());
    }

    @Test(expected = JAXBException.class)
    public void test_constructor_class_badClass() throws JAXBException {
        new JaxbSerialization(getClass());
    }

    @Test
    public void test_constructor_object() throws JAXBException {
        Assert.assertNotNull("Should have an object factory", new JaxbSerialization(new ObjectFactory()).getObjectFactory());

        final ObjectFactory factory = new ObjectFactory();

        Assert.assertSame("Should have an object factory", factory, new JaxbSerialization(factory).getObjectFactory());
        Assert.assertNotNull("Should have a JAXBContext", new JaxbSerialization(new ObjectFactory()).getJaxbContext());
        Assert.assertFalse("Should have factory methods", new JaxbSerialization(new ObjectFactory()).getJaxbFactoryMap().isEmpty());
    }

    @Test
    public void test_constructor_class() throws JAXBException {
        Assert.assertNotNull("Should have an object factory", new JaxbSerialization(ObjectFactory.class).getObjectFactory());
        Assert.assertNotNull("Should have a JAXBContext", new JaxbSerialization(ObjectFactory.class).getJaxbContext());
        Assert.assertFalse("Should have factory methods", new JaxbSerialization(ObjectFactory.class).getJaxbFactoryMap().isEmpty());
    }

    @Test
    public void test_createUnmarshaller() throws JAXBException {
        Assert.assertNotNull("Should have an unmarshaller", new JaxbSerialization(ObjectFactory.class).createUnmarshaller());
    }

    @Test
    public void test_createMarshaller_boolean() throws JAXBException {
        final JaxbSerialization jaxbSerialization = new JaxbSerialization(ObjectFactory.class);
        final Marshaller unformattedMarshaller = jaxbSerialization.createMarshaller(false);
        final Marshaller formttedMarshallerDefault = jaxbSerialization.createMarshaller();
        final Marshaller formttedMarshaller = jaxbSerialization.createMarshaller(true);

        Assert.assertNotNull("Should have an unformatted marshaller", unformattedMarshaller);
        Assert.assertEquals("Property should be false", Boolean.FALSE, unformattedMarshaller.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT));

        Assert.assertNotNull("Should have an formatted marshaller", formttedMarshallerDefault);
        Assert.assertEquals("Property should be false", Boolean.TRUE, formttedMarshallerDefault.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT));

        Assert.assertNotNull("Should have an formatted marshaller", formttedMarshaller);
        Assert.assertEquals("Property should be false", Boolean.TRUE, formttedMarshaller.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT));
    }

    @Test
    public void test_marshall_unmarshall_JAXBElement() throws JAXBException {
        final NameType name = new NameType();
        name.setFirst(System.currentTimeMillis() + "first");
        name.setLast("last" + System.currentTimeMillis());

        final PersonType person = new PersonType();
        person.setId(Long.MIN_VALUE);
        person.setAge(Integer.SIZE);
        person.setName(name);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final JaxbSerialization jaxbSerialization = new JaxbSerialization(ObjectFactory.class);
        final ObjectFactory factory = new ObjectFactory();
        jaxbSerialization.marshal(factory.createPerson(person), baos);

        final byte[] marshalledPerson = baos.toByteArray();
        Assert.assertNotEquals("Should have data", 0, marshalledPerson.length);

        final ByteArrayInputStream bais = new ByteArrayInputStream(marshalledPerson);

        final PersonType comparePerson = jaxbSerialization.unmarshal(bais);

        Assert.assertEquals("Should have same id", person.getId(), comparePerson.getId());
        Assert.assertEquals("Should have same age", person.getAge(), comparePerson.getAge());
        Assert.assertEquals("Should have same first name", person.getName().getFirst(), comparePerson.getName().getFirst());
        Assert.assertEquals("Should have same last name", person.getName().getLast(), comparePerson.getName().getLast());
    }

    @Test
    public void test_marshall_unmarshall_Object() throws JAXBException {
        final NameType name = new NameType();
        name.setFirst(System.currentTimeMillis() + "first");
        name.setLast("last" + System.currentTimeMillis());

        final PersonType person = new PersonType();
        person.setId(Long.MIN_VALUE);
        person.setAge(Integer.SIZE);
        person.setName(name);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final JaxbSerialization jaxbSerialization = new JaxbSerialization(ObjectFactory.class);

        jaxbSerialization.marshal(person, baos);

        final byte[] marshalledPerson = baos.toByteArray();
        Assert.assertNotEquals("Should have data", 0, marshalledPerson.length);

        final ByteArrayInputStream bais = new ByteArrayInputStream(marshalledPerson);

        final PersonType comparePerson = jaxbSerialization.unmarshal(bais);

        Assert.assertEquals("Should have same id", person.getId(), comparePerson.getId());
        Assert.assertEquals("Should have same age", person.getAge(), comparePerson.getAge());
        Assert.assertEquals("Should have same first name", person.getName().getFirst(), comparePerson.getName().getFirst());
        Assert.assertEquals("Should have same last name", person.getName().getLast(), comparePerson.getName().getLast());
    }
}
