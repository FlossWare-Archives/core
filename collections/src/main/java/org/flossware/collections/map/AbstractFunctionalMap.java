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
package org.flossware.collections.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.flossware.common.AbstractCommonBase;

/**
 * Abstract base class for functional maps.
 *
 * @author Scot P. Floess
 *
 * @param <K> the key.
 * @param <V> the type to cache.
 */
public abstract class AbstractFunctionalMap<K, V> extends AbstractCommonBase implements FunctionalMap<K, V> {

    /**
     * Default constructor.
     */
    protected AbstractFunctionalMap() {
    }

    /**
     * Return the map we will use.
     *
     * @return the map to be used.
     */
    protected abstract Map<K, V> getMap();

    /**
     * {@inheritDoc}
     */
    public FunctionalMap<K, V> putF(final K key, final V value) {
        put(key, value);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalMap<K, V> putAllF(final Map<? extends K, ? extends V> map) {
        putAll(map);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalMap<K, V> removeF(final K key) {
        remove(key);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return getMap().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return getMap().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(final Object o) {
        return getMap().containsKey(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(final Object o) {
        return getMap().containsValue(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(final Object o) {
        return getMap().get(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(final K k, final V v) {
        return getMap().put(k, v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object o) {
        return getMap().remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        getMap().putAll(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        getMap().clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<K> keySet() {
        return getMap().keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<V> values() {
        return getMap().values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return getMap().entrySet();
    }
}
