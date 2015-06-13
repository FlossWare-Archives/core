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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.flossware.common.IntegrityUtil;
import org.flossware.util.MapUtil;

/**
 * Utility class for Jaxb related work.
 *
 * @param <O> the object factory type.
 *
 * @author Scot P. Floess
 */
public class JaxbUtil<O> {

    private final O objectFactory;

    private final JAXBContext jaxbContext;
    
    private final Map properties;

    private O getObjectFactory() {
        return objectFactory;
    }

    private JAXBContext getJaxbContext() {
        return jaxbContext;
    }
    
    private Map getProperties() {
        return properties;
    }

    public JaxbUtil(final O objectFactory, final Map properties) throws JAXBException {
        this.objectFactory = IntegrityUtil.ensure(objectFactory, "Must have an object factory!");
        this.jaxbContext = JAXBContext.newInstance(objectFactory.getClass().getPackage().getName(), objectFactory.getClass().getClassLoader());
        this.properties = properties;
    }
    
    public JaxbUtil(final O objectFactory, final boolean isFormatted) throws JAXBException {
        this(objectFactory, MapUtil.put(Marshaller.JAXB_FORMATTED_OUTPUT, true));
    }
    
    public JaxbUtil(final O objectFactory) throws JAXBException {
        this(objectFactory, Collections.EMPTY_MAP);
    }
}
