/*
 * Copyright (C) 2014 Scot P. Floess
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
package org.flossware.collections.cache;

/**
 * API for caching things.
 *
 * @author Scot P. Floess
 *
 * @param <K> the key.
 * @param <V> the type to cache.
 */
public interface Cache<K, V> extends ParamCache<K, V, K> {

    /**
     * Return an item from the cache. If not there, we will create it.
     *
     * @param key       the key.
     * @param parameter value(s) used to create if not found.
     *
     * @return the cached value.
     */
    V get(K key);
}
