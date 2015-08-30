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
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * Serialization for XML. This is a utility class so you don't have to instantiate a JAXBContext when marshalling/unmarshalling and
 * also provides casting when unmarshalling.
 *
 * @author Scot P. Floess
 */
public class JaxbSerialization {

    /**
     * The context based upon the object factory.
     */
    private final JAXBContext jaxbContext;

    /**
     * Creates and stores a JAXBContext.
     *
     * @param objectFactoryClass the class of the object factory generated with xjc.
     * @param properties the properties to set on the JAXBContext created.
     *
     * @throws JAXBException if any problems arise creating the JAXBContext.
     */
    public JaxbSerialization(final Class objectFactoryClass, final Map properties) throws JAXBException {
        this.jaxbContext = JaxbUtil.createJaxbContext(objectFactoryClass, properties);
    }

    /**
     * Creates and stores a JAXBContext.
     *
     * @param objectFactoryClass the class of the object factory generated with xjc.
     *
     * @throws JAXBException if any problems arise creating the JAXBContext.
     */
    public JaxbSerialization(final Class objectFactoryClass) throws JAXBException {
        this.jaxbContext = JaxbUtil.createJaxbContext(objectFactoryClass);
    }

    /**
     * Return the JAXBContext we created at construct time.
     *
     * @return the JAXBContext we created at construct time.
     */
    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    /**
     * Create an unmarshaller from the internal JAXBContext (created at construct time).
     *
     * @return an unmarshaller from the internal JAXBContext (created at construct time).
     *
     * @throws JAXBException if any problems arise creating the Unmarshaller.
     */
    public Unmarshaller createUnmarshaller() throws JAXBException {
        return getJaxbContext().createUnmarshaller();
    }

    /**
     * Unmarshal from a file.
     */
    public <T> T unmarshal(final File file) throws JAXBException {
        return JaxbUtil.computeValue(createUnmarshaller().unmarshal(file));
    }

    /**
     * Unmarshal from a file as a specific type.
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
     * Unmarshal from an input stream as a specific type.
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
     * Unmarshal from a reader as a specific type.
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
     * Unmarshal from a source as a specific type.
     */
    public <T> T unmarshal(final Source source, final Class<T> type) throws JAXBException {
        return createUnmarshaller().unmarshal(source, type).getValue();
    }

    /**
     * Create an marshaller from the internal JAXBContext (created at construct time). If isFormatted is true, marshalling will
     * format the XML.
     *
     * @param isFormatted is a flag if true will format XML upon serialization.
     *
     * @return a marshaller from the internal JAXBContext (created at construct time).
     *
     * @throws JAXBException if any problems arise creating the Unmarshaller.
     */
    public Marshaller createMarshaller(boolean isFormatted) throws JAXBException {
        return JaxbUtil.setProperty(getJaxbContext().createMarshaller(), Marshaller.JAXB_FORMATTED_OUTPUT, isFormatted);
    }

    /**
     * Create a marshaller from the internal JAXBContext (created at construct time). Marshalling will for formatted XML.
     *
     * @return a marshaller who will format XML.
     *
     * @throws JAXBException if any problems arise creating the marshaller.
     */
    public Marshaller createMarshaller() throws JAXBException {
        return createMarshaller(true);
    }

    /**
     * Marshal to a file. If isFormatted is true, the XML will be marshalled formatted.
     */
    public void marshal(final JAXBElement element, final File file, final boolean isFormatted) throws JAXBException {
        createMarshaller(isFormatted).marshal(element, file);
    }

    /**
     * Marshal to a file in formatted XML.
     */
    public void marshal(final JAXBElement element, final File file) throws JAXBException {
        marshal(element, file, true);
    }

    /**
     * Marshal to an output stream. If isFormatted is true, the XML will be marshalled formatted.
     */
    public void marshal(final JAXBElement element, final OutputStream outputStream, final boolean isFormatted) throws JAXBException {
        createMarshaller(isFormatted).marshal(element, outputStream);
    }

    /**
     * Marshal to an output stream in formatted XML.
     */
    public void marshal(final JAXBElement element, final OutputStream outputStream) throws JAXBException {
        marshal(element, outputStream, true);
    }

    /**
     * Marshal to a writer. If isFormatted is true, the XML will be marshalled formatted.
     */
    public void marshal(final JAXBElement element, final Writer writer, final boolean isFormatted) throws JAXBException {
        createMarshaller(isFormatted).marshal(element, writer);
    }

    /**
     * Marshal to a writer in formatted XML.
     */
    public void marshal(final JAXBElement element, final Writer writer) throws JAXBException {
        marshal(element, writer, true);
    }

    /**
     * Marshal to a file. If isFormatted is true, the XML will be marshalled formatted.
     */
    public void marshal(final Object object, final File file, final boolean isFormatted) throws JAXBException {
        createMarshaller(isFormatted).marshal(object, file);
    }

    /**
     * Marshal to a file in formatted XML.
     */
    public void marshal(final Object object, final File file) throws JAXBException {
        marshal(object, file, true);
    }

    /**
     * Marshal to an output stream. If isFormatted is true, the XML will be marshalled formatted.
     */
    public void marshal(final Object object, final OutputStream outputStream, final boolean isFormatted) throws JAXBException {
        createMarshaller(isFormatted).marshal(object, outputStream);
    }

    /**
     * Marshal to an output stream in formatted XML.
     */
    public void marshal(final Object object, final OutputStream outputStream) throws JAXBException {
        marshal(object, outputStream, true);
    }

    /**
     * Marshal to a writer in formatted XML. If isFormatted is true, the XML will be marshalled formatted.
     */
    public void marshal(final Object object, final Writer writer, final boolean isFormatted) throws JAXBException {
        createMarshaller(isFormatted).marshal(object, writer);
    }

    /**
     * Marshal to a writer in formatted XML.
     */
    public void marshal(final Object object, final Writer writer) throws JAXBException {
        marshal(object, writer, true);
    }
}
