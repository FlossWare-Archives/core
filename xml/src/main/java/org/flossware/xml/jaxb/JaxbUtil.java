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

import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.flossware.common.IntegrityUtil;
import org.flossware.util.ObjectUtil;

/**
 * Utility class for Jaxb related work.
 *
 * @author Scot P. Floess
 */
public class JaxbUtil {

    /**
     * Create a new JAXB context.
     *
     * @param objectFactoryClass the object factory class generated via xjc.
     * @param properties provider-specific properties.
     *
     * @return a JAXBContext
     *
     * @throws JAXBException if any problems arise create the context.
     */
    public static JAXBContext createJaxbContext(final Class objectFactoryClass, final Map properties) throws JAXBException {
        return JAXBContext.newInstance(ObjectUtil.getPackage(objectFactoryClass), objectFactoryClass.getClassLoader(), properties);
    }

    /**
     * Create a JAXB Context.
     *
     * @param <T>
     * @param objectFactoryClass
     *
     * @return
     *
     * @throws JAXBException
     */
    public static <T> JAXBContext createJaxbContext(final Class<T> objectFactoryClass) throws JAXBException {
        return JAXBContext.newInstance(ObjectUtil.getPackage(objectFactoryClass), objectFactoryClass.getClassLoader());
    }

    /**
     * Return the element's declared type class.
     *
     * @param <T> the type of the class for the element's declared type.
     * @param element the JAXB element.
     *
     * @return the class for the element's declared type.
     */
    public static <T> Class<T> getDeclaredTypeClass(final JAXBElement<T> element) {
        return IntegrityUtil.ensure(element, "Must have a JAXB element!").getDeclaredType();
    }

    /**
     * If this is a JAXB Element, return true or false if not.
     *
     * @param obj use to compare to see if it's a JAXBElement.
     *
     * @return true if a JAXBElement or false if not.
     */
    public static boolean isJaxbElement(final Object obj) {
        return null != obj && obj instanceof JAXBElement;
    }

    /**
     * Return the value for the JAXBElement in the appropriate type.
     *
     * @param <T> the type to convert to.
     *
     * @param jaxbElement the JAXBElement to get data from.
     *
     * @return the type cast JAXBElement's value.
     */
    public static <T> T computeValue(final JAXBElement jaxbElement) {
        return (T) IntegrityUtil.ensure(jaxbElement, "JAXBElement cannot be null!").getValue();
    }

    /**
     * Determine which value to return - if obj is a JAXBElement, we will use its getValue(), otherwise we assume that obj is of
     * type T.
     *
     * @param <T> the type of object to return.
     *
     * @param obj to examine if it's a JAXBElement (and if so return its getValue()), or the object itself.
     *
     * @return either the JAXBElement's getValue() or obj cast as a T.
     */
    public static <T> T computeValue(final Object obj) {
        if (isJaxbElement(obj)) {
            return computeValue((JAXBElement) obj);
        }

        return (T) obj;
    }
}
