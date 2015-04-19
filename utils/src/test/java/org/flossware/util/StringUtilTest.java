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
package org.flossware.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the StringUtil class.
 *
 * @author Scot P. Floess
 */
public class StringUtilTest {

    @Test
    public void test_concat() {
        Assert.assertEquals("Should be same string", "", StringUtil.concat(""));
        Assert.assertEquals("Should be same string", "foo", StringUtil.concat("foo"));
        Assert.assertEquals("Should be same string", "foobar", StringUtil.concat("foo", "bar"));
        Assert.assertEquals("Should be same string", "foobartheta", StringUtil.concat("foo", "bar", "theta"));
    }

    @Test
    public void test_concatWithSeparator() {
        Assert.assertEquals("Should be same string", "", StringUtil.concatWithSeparator("/", ""));
        Assert.assertEquals("Should be same string", "foo", StringUtil.concatWithSeparator("/", "foo"));
        Assert.assertEquals("Should be same string", "foo/bar", StringUtil.concatWithSeparator("/", "foo", "bar"));
        Assert.assertEquals("Should be same string", "foo/bar/theta", StringUtil.concatWithSeparator("/", "foo", "bar", "theta"));

        Assert.assertEquals("Should be same string", "/", StringUtil.concatWithSeparator(true, "/", ""));
        Assert.assertEquals("Should be same string", "foo/", StringUtil.concatWithSeparator(true, "/", "foo"));
        Assert.assertEquals("Should be same string", "foo/bar/", StringUtil.concatWithSeparator(true, "/", "foo", "bar"));
        Assert.assertEquals("Should be same string", "foo/bar/theta/", StringUtil.concatWithSeparator(true, "/", "foo", "bar", "theta"));
    }
}
