/*
 * Copyright (C) 2015 Scot P. Floess
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

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.flossware.collections.map.DefaultFunctionalMap;
import org.flossware.collections.map.FunctionalMap;
import org.flossware.common.IntegrityUtil;

/**
 * Serialization for XML.
 *
 * @param <O> the object factory type.
 *
 * @author Scot P. Floess
 */
public class JaxbSerialization<O> {

    /**
     * The object factory generated from JAXb
     */
    private final O objectFactory;

    /**
     * The context based upon the object factory.
     */
    private final JAXBContext jaxbContext;

    private O getObjectFactory() {
        return objectFactory;
    }

    public JaxbSerialization(final O objectFactory, final FunctionalMap properties) throws JAXBException {
        this.objectFactory = IntegrityUtil.ensure(objectFactory, "Must have an object factory!");
        this.jaxbContext = JaxbUtil.createJaxbContext(objectFactory, properties);
    }

    public JaxbSerialization(final O objectFactory, final boolean isFormatted) throws JAXBException {
        this(objectFactory, new DefaultFunctionalMap(new HashMap()).putF(Marshaller.JAXB_FORMATTED_OUTPUT, true));
    }

    public JaxbSerialization(final O objectFactory) throws JAXBException {
        this(objectFactory, new DefaultFunctionalMap(new HashMap()));
    }

    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    public Unmarshaller createUnmarshaller() throws JAXBException {
        return getJaxbContext().createUnmarshaller();
    }

    public Marshaller createMarshaller() throws JAXBException {
        return getJaxbContext().createMarshaller();
    }

    /**
     * Unmarshal from a file.
     */
    public <T> T unmarshal(final File file) throws JAXBException {
        return JaxbUtil.computeValue(createUnmarshaller().unmarshal(file));
    }

    /**
     * Unmarshal from a file.
     */
    public <T> T unmarshal(final File file, final Class<T> type) throws JAXBException {
        return unmarshal(new StreamSource(file), type);
    }

    /**
     * Unmarshal from an input stream.
     */
    public <T> T unmarshal(final InputStream inputStream) throws JAXBException {
        return JaxbUtil.computeValue(createUnmarshaller().unmarshal(inputStream));
    }

    /**
     * Unmarshal from an input stream.
     */
    public <T> T unmarshal(final InputStream inputStream, final Class<T> type) throws JAXBException {
        return unmarshal(new StreamSource(inputStream), type);
    }

    /**
     * Unmarshal from a reader.
     */
    public <T> T unmarshal(final Reader reader) throws JAXBException {
        return JaxbUtil.computeValue(createUnmarshaller().unmarshal(reader));
    }

    /**
     * Unmarshal from a reader.
     */
    public <T> T unmarshal(final Reader reader, final Class<T> type) throws JAXBException {
        return unmarshal(new StreamSource(reader), type);
    }

    /**
     * Unmarshal from a source.
     */
    public <T> T unmarshal(final Source source) throws JAXBException {
        return JaxbUtil.computeValue(createUnmarshaller().unmarshal(source));
    }

    /**
     * Unmarshal from a source.
     */
    public <T> T unmarshal(final Source source, final Class<T> type) throws JAXBException {
        return createUnmarshaller().unmarshal(source, type).getValue();
    }

    /**
     * Marshal to a file.
     */
    public void marshal(final JAXBElement element, final File file) throws JAXBException {
        createMarshaller().marshal(element, file);
    }

    /**
     * Marshal to an output stream.
     */
    public void marshal(final JAXBElement element, final OutputStream outputStream) throws JAXBException {
        createMarshaller().marshal(element, outputStream);
    }

    /**
     * Marshal to a writer.
     */
    public void marshal(final JAXBElement element, final Writer writer) throws JAXBException {
        createMarshaller().marshal(element, writer);
    }

    /**
     * Marshal to a file. Use the ObjectFactory if it has a create method to generate a JAXBElement.
     */
    public void marshal(final Object object, final File file) throws JAXBException {
        createMarshaller().marshal(object, file);
    }

    /**
     * Marshal to an output stream. Use the ObjectFactory if it has a create method to generate a JAXBElement.
     */
    public void marshal(final Object object, final OutputStream outputStream) throws JAXBException {
        createMarshaller().marshal(object, outputStream);
    }

    /**
     * Marshal to a writer. Use the ObjectFactory if it has a create method to generate a JAXBElement.
     */
    public void marshal(final Object object, final Writer writer) throws JAXBException {
        createMarshaller().marshal(object, writer);
    }
}
