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

/**
 * Returns attributes on a class. Attributes are things accessible objects or annotations.
 *
 * @author sfloess
 *
 * @param <T> the type of attributes.
 */
public interface AccessibleObjectMgr<T> {

    /**
     * Retrieve attributes from a class.
     *
     * @param klass is the klass to get it's attributes.
     *
     * @return the attributes for the class.
     */
    T[] getAccessibles(Class klass);
}
