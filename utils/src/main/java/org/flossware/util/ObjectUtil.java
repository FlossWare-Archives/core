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
package org.flossware.util;

import java.util.logging.Logger;
import org.flossware.common.IntegrityUtil;

/**
 * Utility class for object functionality. Slightly silly in that these method are all easily callable. However, adding the
 * IntegrityUtil functionality for a little flair and not doing null checks.
 *
 * @author sfloess
 */
public class ObjectUtil {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(ObjectUtil.class.getName());

    /**
     * Default constructor not allowed.
     */
    private ObjectUtil() {
    }

    /**
     * Return the logger.
     */
    protected static Logger getLogger() {
        return logger;
    }

    /**
     * Compute the package for klass.
     *
     * @param klass the class for whom we desire a package.
     *
     * @return the package for klass.
     */
    public static String getPackage(final Class klass) {
        return IntegrityUtil.ensure(klass, "Must have a class!").getPackage().getName();
    }

    /**
     * Compute the package for object.
     *
     * @param object the object for whom we desire a package.
     *
     * @return the package for object.
     */
    public static String getPackage(final Object object) {
        return getPackage(IntegrityUtil.ensure(object, "Must have an object!").getClass());
    }

    /**
     * Return the class for object.
     *
     * @param <T> the type of class.
     * @param object the object for whom we desire a class.
     *
     * @return the class for object.
     */
    public static <T> Class<T> getClass(final Object object) {
        return (Class<T>) IntegrityUtil.ensure(object, "Must have an object!").getClass();
    }
}
