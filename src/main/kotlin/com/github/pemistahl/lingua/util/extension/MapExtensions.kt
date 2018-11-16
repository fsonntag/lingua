/*
 * Copyright 2018 Peter M. Stahl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.pemistahl.lingua.util.extension

import com.github.pemistahl.lingua.model.Ngram
import org.apache.commons.math3.fraction.Fraction

internal fun <T : Ngram> Map<T, Fraction>.inverse(): Map<Fraction, Set<String>> {
    val result = mutableMapOf<Fraction, MutableSet<String>>()
    for ((key, value) in this) {
        result.computeIfAbsent(value) { mutableSetOf() }.add(key.toString())
    }
    return result
}