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
package org.flossware.collections.collection;

import java.util.Collection;
import java.util.Set;

/**
 * Represents a "functional" set which allows us to extend the core functions with values that return self so we can chain calls.
 * Similar to what one can do with StringBuilder. For example: functionalCollection.add("X").add("Y")
 *
 * @author sfloess
 */
public interface FunctionalCollection<V> extends Set<V> {

    /**
     * Adds the specified value to this set if it is not already present.
     *
     * @param value to be added to this set.
     *
     * @return self.
     */
    FunctionalCollection<V> addF(V value);

    /**
     * Removes value from the set if present.
     *
     * @param value to remove.
     *
     * @return self.
     */
    FunctionalCollection<V> removeF(V value);

    /**
     * Add all elements in the collection to self.
     *
     * @param collection the values to add.
     *
     * @return self.
     */
    FunctionalCollection<V> addAllF(Collection<? extends V> collection);

    /**
     * Remove all elements from collection found in self.
     *
     * @param collection the values to remove.
     *
     * @return self.
     */
    FunctionalCollection<V> removeAllF(Collection<?> collection);
}
