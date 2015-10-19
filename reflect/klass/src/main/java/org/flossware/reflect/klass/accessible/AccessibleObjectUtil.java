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
package org.flossware.reflect.klass.accessible;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import org.flossware.common.IntegrityUtil;

/**
 * Utility class for filtering classes based upon constructor, field or method.
 *
 * @author sfloess
 */
public class AccessibleObjectUtil {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(AccessibleObjectUtil.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     *
     * /**
     * Filter a class for a value.
     *
     * @param <T> the type of accessible object this filter operates upon.
     *
     * @param klass the class to filter.
     * @param accessibleObjectMgr can retrieve accessible objects from klass.
     *
     * @return accessible objects that meet the filter.
     */
    public static <T extends AccessibleObject> Collection<T> getAllAccessibles(final Class klass, final AccessibleObjectMgr<T> accessibleObjectMgr) {
        return getAllAccessibles(klass, accessibleObjectMgr, new ArrayList<T>());
    }

    /**
     * Filter a class for a value.
     *
     * @param <T> the type of accessible object this filter operates upon.
     *
     * @param klass the class to filter.
     * @param accessibleObjectMgr can retrieve accessible objects from klass.
     * @param results a collection to add the results of the filter to.
     *
     * @return the results.
     */
    public static <T extends AccessibleObject> Collection<T> getAllAccessibles(final Class klass, final AccessibleObjectMgr<T> accessibleObjectMgr, final Collection<T> results) {
        IntegrityUtil.ensure(accessibleObjectMgr, "Must have an accessible object mgr");
        IntegrityUtil.ensure(results, 0, "Must have a collection");

        if (null == klass) {
            return results;
        }

        results.addAll(Arrays.asList(accessibleObjectMgr.getAccessibles(klass)));

        return getAllAccessibles(klass.getSuperclass(), accessibleObjectMgr, results);
    }

    /**
     * Default constructor not allowed.
     */
    private AccessibleObjectUtil() {
    }
}
