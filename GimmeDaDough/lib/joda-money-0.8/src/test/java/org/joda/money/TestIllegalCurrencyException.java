/*
 *  Copyright 2009-2013 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.money;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * Test IllegalCurrencyException.
 */
@Test
public class TestIllegalCurrencyException {

    //-----------------------------------------------------------------------
    // new (String)
    //-----------------------------------------------------------------------
    public void test_String() {
        IllegalCurrencyException test = new IllegalCurrencyException("PROBLEM");
        assertEquals(test.getMessage(), "PROBLEM");
        assertEquals(test.getCause(), null);
    }

    public void test_String_nullString() {
        IllegalCurrencyException test = new IllegalCurrencyException(null);
        assertEquals(test.getMessage(), null);
        assertEquals(test.getCause(), null);
    }

}
