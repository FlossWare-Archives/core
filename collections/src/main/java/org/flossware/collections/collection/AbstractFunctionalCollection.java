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
import java.util.Iterator;
import org.flossware.common.AbstractCommonBase;

/**
 * Abstract base class for functional sets.
 *
 * @author sfloess
 */
public abstract class AbstractFunctionalCollection<V> extends AbstractCommonBase implements FunctionalCollection<V> {

    /**
     * Default constructor.
     */
    protected AbstractFunctionalCollection() {
    }

    /**
     * Return the set we will use.
     *
     * @return the set to be used.
     */
    protected abstract Collection<V> getCollection();

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalCollection<V> addF(final V value) {
        add(value);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalCollection<V> removeF(final V value) {
        remove(value);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalCollection<V> addAllF(final Collection<? extends V> collection) {
        addAll(collection);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalCollection<V> removeAllF(final Collection<?> collection) {
        removeAll(collection);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return getCollection().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return getCollection().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final Object o) {
        return getCollection().contains(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<V> iterator() {
        return getCollection().iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return getCollection().toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(final T[] ts) {
        return getCollection().toArray(ts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(final V e) {
        return getCollection().add(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(final Object o) {
        return getCollection().remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(final Collection<?> clctn) {
        return getCollection().containsAll(clctn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(final Collection<? extends V> clctn) {
        return getCollection().addAll(clctn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(final Collection<?> clctn) {
        return getCollection().retainAll(clctn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(final Collection<?> clctn) {
        return getCollection().removeAll(clctn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        getCollection().clear();
    }
}
