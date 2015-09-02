/*
 * Copyright 2015, The Querydsl Team (http://www.querydsl.com/team)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.querydsl.maven;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.types.Type;

/**
 * {@code TypeMapping} customizes the mapping from table + column to a type.
 *
 * @author tiwe
 *
 */
public class TypeMapping implements Mapping {

    public String table;

    public String column;

    public String type;

    @Override
    public void apply(Configuration configuration) {
        try {
            Class<?> typeClass = Class.forName(type);
            if (Type.class.isAssignableFrom(typeClass)) {
                configuration.register(table, column, (Type<?>) typeClass.newInstance());
            } else {
                configuration.register(table, column, typeClass);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
