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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.flossware.common.IntegrityUtil;

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

    private O getObjectFactory() {
        return objectFactory;
    }

    private JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    public JaxbUtil(final O objectFactory) throws JAXBException {
        this.objectFactory = IntegrityUtil.ensure(objectFactory, "Must have an object factory!");
        this.jaxbContext = JAXBContext.newInstance(objectFactory.getClass().getPackage().getName(), objectFactory.getClass().getClassLoader());
    }
}
