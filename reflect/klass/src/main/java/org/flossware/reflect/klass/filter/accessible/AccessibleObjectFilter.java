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
package org.flossware.reflect.klass.filter.accessible;

import java.lang.reflect.AccessibleObject;
import org.flossware.reflect.klass.filter.ClassFilter;

/**
 * Abstract base class for filtering on annotations.
 *
 * @author sfloess
 *
 * @param <T> the type thats filterable on this class.
 * @param <V> the type of accessible object to be filtered.
 */
public interface AccessibleObjectFilter<T extends AccessibleObject, V> extends ClassFilter<T, V> {
}
