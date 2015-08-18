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
package org.flossware.collections.map;

import java.util.Map;

/**
 * Represents a "functional" map which allows us to extend the core functions with values that return self so we can chain calls.
 * Similar to what one can do with StringBuilder. For example: functionalMap.putF("1", "2").putF("3", "4")
 *
 * @author sfloess
 */
public interface FunctionalMap<K, V> extends Map<K, V> {

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key the key associated with the value.
     * @param value the value for the key.
     *
     * @return self.
     */
    FunctionalMap<K, V> putF(K key, V value);

    /**
     * Copies all the specified mappings to self.
     *
     * @param map the map to copy.
     *
     * @return self.
     */
    FunctionalMap<K, V> putAllF(Map<? extends K, ? extends V> map);

    /**
     * Remote a key.
     *
     * @param key the key to remove.
     *
     * @return self.
     */
    FunctionalMap<K, V> removeF(K key);
}
