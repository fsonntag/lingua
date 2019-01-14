/*
 * Copyright 2018-2019 Peter M. Stahl pemistahl@googlemail.com
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

package com.github.pemistahl.lingua.api

import com.github.pemistahl.lingua.api.Language.ARABIC
import com.github.pemistahl.lingua.api.Language.BELARUSIAN
import com.github.pemistahl.lingua.api.Language.BULGARIAN
import com.github.pemistahl.lingua.api.Language.CROATIAN
import com.github.pemistahl.lingua.api.Language.CZECH
import com.github.pemistahl.lingua.api.Language.Companion.getByIsoCode
import com.github.pemistahl.lingua.api.Language.DANISH
import com.github.pemistahl.lingua.api.Language.DUTCH
import com.github.pemistahl.lingua.api.Language.ENGLISH
import com.github.pemistahl.lingua.api.Language.ESTONIAN
import com.github.pemistahl.lingua.api.Language.FINNISH
import com.github.pemistahl.lingua.api.Language.FRENCH
import com.github.pemistahl.lingua.api.Language.GERMAN
import com.github.pemistahl.lingua.api.Language.HUNGARIAN
import com.github.pemistahl.lingua.api.Language.ITALIAN
import com.github.pemistahl.lingua.api.Language.LATIN
import com.github.pemistahl.lingua.api.Language.LATVIAN
import com.github.pemistahl.lingua.api.Language.LITHUANIAN
import com.github.pemistahl.lingua.api.Language.PERSIAN
import com.github.pemistahl.lingua.api.Language.POLISH
import com.github.pemistahl.lingua.api.Language.PORTUGUESE
import com.github.pemistahl.lingua.api.Language.ROMANIAN
import com.github.pemistahl.lingua.api.Language.RUSSIAN
import com.github.pemistahl.lingua.api.Language.SPANISH
import com.github.pemistahl.lingua.api.Language.SWEDISH
import com.github.pemistahl.lingua.api.Language.TURKISH
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LanguageTest {

    @Test
    fun `assert that number of currently supported languages is correct`() {
        assertThat(Language.values().count()).`as`("wrong number of supported languages").isEqualTo(26)
    }

    @Test
    fun `assert that unknown language is excluded from detection`() {
        assertThat(Language.UNKNOWN.isExcludedFromDetection).isTrue()
    }

    @Test
    fun `assert that certain languages support Latin alphabet`() {
        assertThat(Language.values().filter { it.hasLatinAlphabet })
            .`as`("incorrect or missing language supporting Latin alphabet")
            .containsExactlyInAnyOrder(
                CROATIAN, CZECH, DANISH, DUTCH, ENGLISH, ESTONIAN, FINNISH,
                FRENCH, GERMAN, HUNGARIAN, ITALIAN, LATIN, LATVIAN, LITHUANIAN,
                POLISH, PORTUGUESE, ROMANIAN, SPANISH, SWEDISH, TURKISH
            )
    }

    @Test
    fun `assert that certain languages support Cyrillic alphabet`() {
        assertThat(Language.values().filter { it.hasCyrillicAlphabet })
            .`as`("incorrect or missing language supporting Cyrillic alphabet")
            .containsExactlyInAnyOrder(BELARUSIAN, BULGARIAN, RUSSIAN)
    }

    @Test
    fun `assert that certain languages support Arabic alphabet`() {
        assertThat(Language.values().filter { it.hasArabicAlphabet })
            .`as`("incorrect or missing language supporting Arabic alphabet")
            .containsExactlyInAnyOrder(ARABIC, PERSIAN)
    }

    @ParameterizedTest
    @CsvSource(
        "ar, ARABIC",     "be, BELARUSIAN", "bg, BULGARIAN", "hr, CROATIAN", "cs, CZECH",
        "da, DANISH",     "nl, DUTCH",      "en, ENGLISH",   "et, ESTONIAN", "fi, FINNISH",
        "fr, FRENCH",     "de, GERMAN",     "hu, HUNGARIAN", "it, ITALIAN",  "la, LATIN",
        "lv, LATVIAN",    "lt, LITHUANIAN", "fa, PERSIAN",   "pl, POLISH",   "pt, PORTUGUESE",
        "ro, ROMANIAN",   "ru, RUSSIAN",    "es, SPANISH",   "sv, SWEDISH",  "tr, TURKISH",
        "<unk>, UNKNOWN", "qjd7e, UNKNOWN"
    )
    fun `assert that correct language is returned for iso code`(isoCode: String, language: Language) {
        assertThat(getByIsoCode(isoCode)).isEqualTo(language)
    }

    @Test
    fun `assert that iso codes for tests are correct`() {
        assertThat(Language.getIsoCodesForTests())
            .`as`("iso codes for language reports incorrect or missing")
            .containsExactlyInAnyOrder(
                "ar", "be", "bg", "hr", "cs", "da", "nl", "en",
                "et", "fi", "fr", "de", "hu", "it", "lv", "lt",
                "fa", "pl", "pt", "ro", "ru", "es", "sv", "tr"
            )
            .doesNotContain("la", "<unk>")
    }

}