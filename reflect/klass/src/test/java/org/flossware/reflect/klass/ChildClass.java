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
package org.flossware.reflect.klass;

/**
 * A child class for testing...
 *
 * @author sfloess
 */
public class ChildClass extends BaseClass {

    @FieldOneAnnotation
    @GeneralAnnotation
    private final Long l;

    @FieldTwoAnnotation
    @GeneralAnnotation
    private final String s;

    @ConstructorOneAnnotation
    @GeneralAnnotation
    ChildClass(Long l) {
        this.l = l;
        this.s = "";
    }

    @ConstructorTwoAnnotation
    @GeneralAnnotation
    ChildClass(String s) {
        this.l = (long) 0;
        this.s = s;
    }

    @MethodOneAnnotation
    @GeneralAnnotation
    Long getLong() {
        return l;
    }

    @MethodTwoAnnotation
    @GeneralAnnotation
    String getStr() {
        return s;
    }
}
