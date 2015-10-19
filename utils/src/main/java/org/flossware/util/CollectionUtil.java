/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.flossware.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.common.Filter;
import org.flossware.common.IntegrityUtil;

/**
 *
 * Utility class for collections.
 *
 * @author sfloess
 *
 */
public class CollectionUtil {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(CollectionUtil.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Only add a value if <code>isToAdd</code> is true.
     *
     * @param <V> the type contained in <code>collection</code>.
     *
     * @param collection the collection to be added to.
     * @param value the value to add to collection.
     * @param isToAdd if true, <code>value</code> will be added to <code>collection</code>, false it will not be added.
     */
    public static <V> void addValue(final Collection<V> collection, final V value, final boolean isToAdd) {
        IntegrityUtil.ensure(collection, 0, "Must have a collection");

        if (isToAdd) {
            collection.add(value);
        }
    }

    /**
     * Turns toCopy into a list.
     *
     * @param <V> the type of data to copy.
     * @param toCopy the collection to copy.
     *
     * @return a new list.
     */
//    public static <V> V[] asArray(final Collection<V> toCopy) {
//        IntegrityUtil.ensure(toCopy, 0, "Must have a collection");
//
//        final V[] retVal = (V[]) Array.newInstance(toCopy.get, toCopy.size());
//        toCopy.
//                getLogger().log(Level.FINE, "Creating a copy of {0}", toCopy);
//
//        retVal.addAll(toCopy);
//
//        return retVal;
//    }
    /**
     * Turns toCopy into a list.
     *
     * @param <V> the type of data to copy.
     * @param toCopy the collection to copy.
     *
     * @return a new list.
     */
    public static <V> List<V> asList(final Collection<V> toCopy) {
        IntegrityUtil.ensure(toCopy, 0, "Must have a collection");

        final List<V> retVal = new LinkedList<>();

        getLogger().log(Level.FINE, "Creating a copy of {0}", toCopy);

        retVal.addAll(toCopy);

        return retVal;
    }

    /**
     * Turns toCopy into a set.
     *
     * @param <V> the type of data to copy.
     * @param toCopy the collection to copy.
     *
     * @return a new list.
     */
    public static <V> Set<V> asSet(final Collection<V> toCopy) {
        IntegrityUtil.ensure(toCopy, 0, "Must have a collection");

        final Set<V> retVal = new TreeSet<>();

        getLogger().log(Level.FINE, "Creating a copy of {0}", toCopy);

        retVal.addAll(toCopy);

        return retVal;
    }

    /**
     * Take a collection and sort it, returning a new collection of the sort.
     *
     * @param <V> the type to sort.
     *
     * @param toSort the collection to sort - will not be affected.
     * @param comparator will perform comparisons for sort order.
     *
     * @return a newly sorted collection.
     */
    public static <V> Collection<V> sort(final Collection<V> toSort, final Comparator<V> comparator) {
        IntegrityUtil.ensure(toSort, 0, "Must have a collection");
        IntegrityUtil.ensure(comparator, "Must have a comparator");

        final List<V> retVal = asList(toSort);

        getLogger().log(Level.FINE, "Sorting {0}", toSort);

        Collections.sort(retVal, comparator);

        return retVal;
    }

    /**
     * Filter a collection.
     *
     * @param <T> the type to filter upon.
     * @param <V> the value to find.
     *
     * @param collection is the collection to filter.
     * @param filter is the filter to apply.
     * @param value the value to search for.
     *
     * @return a collection of those items in the collection that satisfy the filter.
     *
     */
    public static <T, V> Collection<T> filter(final Collection<T> collection, final Filter<T, V> filter, final V value) {
        IntegrityUtil.ensure(collection, 0, "Must have a collection to filter");
        IntegrityUtil.ensure(filter, "Must have a filter");

        final List<T> retVal = new ArrayList(collection.size());

        for (final T toFilter : collection) {
            addValue(retVal, toFilter, filter.accept(toFilter, value));
        }

        return retVal;
    }

    /**
     * Filter a collection and store the results in <code>results</code>.
     *
     * @param <T> the type to filter upon.
     * @param <V> the value to find.
     *
     * @param collection is the collection to filter.
     * @param filter is the filter to apply.
     * @param value the value to search for.
     *
     * @param results the collection to store results in.
     */
    public static <T, V> void filter(final Collection<T> collection, final Filter<T, V> filter, final V value, final Collection<T> results) {
        IntegrityUtil.ensure(results, 0, "Must have a results collection");

        results.addAll(filter(collection, filter, value));
    }

    /**
     * Filter a collection.
     *
     * @param <T> the type to filter upon.
     * @param <V> the value to find.
     *
     * @param collection is the collection to filter.
     * @param filter is the filter to apply.
     * @param value the value to search for.
     *
     * @return a collection of those items in the collection that satisfy the filter.
     *
     */
    public static <T, V> Collection<T> filter(final T[] collection, final Filter<T, V> filter, final V value) {
        IntegrityUtil.ensure(collection, 0, "Must have a collection to filter");
        IntegrityUtil.ensure(filter, "Must have a filter");

        return filter(Arrays.asList(collection), filter, value);
    }

    /**
     * Filter a collection and store the results in <code>results</code>.
     *
     * @param <T> the type to filter upon.
     * @param <V> the value to find.
     *
     * @param collection is the collection to filter.
     * @param filter is the filter to apply.
     * @param value the value to search for.
     *
     * @param results the collection to store results in.
     */
    public static <T, V> void filter(final T[] collection, final Filter<T, V> filter, final V value, final Collection<T> results) {
        IntegrityUtil.ensure(results, 0, "Must have a results collection");

        results.addAll(filter(collection, filter, value));
    }

    /**
     * See if a collection meets a filter.
     *
     * @param <T> the type within the collection to search.
     * @param <V> the value to search for within the collection.
     *
     * @param toSearch the collection to iterate over to see if it contains a value.
     * @param filter the filter to apply to each object.
     * @param value using filter, determining if it is contained in <code>toSearch</code>.
     *
     * @return true if the collection meets the filter or false if not.
     */
    public static <T, V> boolean contains(final Collection<T> toSearch, final Filter<T, V> filter, final V value) {
        getLogger().log(Level.FINE, "Determining if [{0}] is found in {1}", new Object[]{value, toSearch});

        IntegrityUtil.ensure(toSearch, 0, "Must have a collection to filter");
        IntegrityUtil.ensure(filter, "Must have a filter");

        return !filter(toSearch, filter, value).isEmpty();
    }

    /**
     * See if a collection meets a filter.
     *
     * @param <T> the type within the collection to search.
     * @param <V> the value to search for within the collection.
     *
     * @param toSearch the collection to iterate over to see if it contains a value.
     * @param filter the filter to apply to each object.
     * @param value using filter, determining if it is contained in <code>toSearch</code>.
     *
     * @return true if the collection meets the filter or false if not.
     */
    public static <T, V> boolean contains(final T[] toSearch, final Filter<T, V> filter, final V value) {
        getLogger().log(Level.FINE, "Determining if [{0}] is found in {1}", new Object[]{value, toSearch});

        IntegrityUtil.ensure(toSearch, 0, "Must have a collection to filter");
        IntegrityUtil.ensure(filter, "Must have a filter");

        return contains(Arrays.asList(toSearch), filter, value);
    }
}
