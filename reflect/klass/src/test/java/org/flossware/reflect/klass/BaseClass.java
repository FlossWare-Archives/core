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
 * A base class for testing...
 *
 * @author sfloess
 */
@GeneralAnnotation
public class BaseClass {

    @FieldOneAnnotation
    @GeneralAnnotation
    private final Integer i;

    @FieldTwoAnnotation
    @GeneralAnnotation
    private final Double d;

    @ConstructorOneAnnotation
    @ConstructorTwoAnnotation
    @GeneralAnnotation
    BaseClass() {
        this.i = 0;
        this.d = (double) 0;
    }

    @ConstructorOneAnnotation
    @GeneralAnnotation
    BaseClass(Integer i) {
        this.i = i;
        this.d = (double) 0;
    }

    @ConstructorTwoAnnotation
    @GeneralAnnotation
    BaseClass(Double d) {
        this.i = 0;
        this.d = d;
    }

    @MethodOneAnnotation
    @GeneralAnnotation
    Integer getInt() {
        return i;
    }

    @MethodTwoAnnotation
    @GeneralAnnotation
    Double getDouble() {
        return d;
    }
}
