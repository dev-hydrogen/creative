/*
 * This file is part of uracle, licensed under the MIT license
 *
 * Copyright (c) 2021 Unnamed Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package team.unnamed.uracle.lang;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import team.unnamed.uracle.PackMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;

/**
 * Represents a set of translations for a specific
 * language. Custom language additions require a
 * {@link LanguageEntry} being added to the {@link PackMeta}
 * instance of the resource pack
 *
 * @since 1.0.0
 */
public class Language implements Keyed, Examinable {

    private final Key key;
    @Unmodifiable private final Map<String, String> translations;

    public Language(
            Key key,
            Map<String, String> translations
    ) {
        requireNonNull(translations, "translations");
        this.key = requireNonNull(key, "key");
        // create a copy and wrap into a unmodifiable map to
        // avoid modifications
        this.translations = unmodifiableMap(new HashMap<>(translations));
    }

    /**
     * Returns the language JSON file location inside
     * assets/&lt;namespace&gt;/lang
     *
     * @return The language resource location
     */
    @Override
    public @NotNull Key key() {
        return key;
    }

    /**
     * Returns an unmodifiable map containing all the translations for this
     * language, where the key is the translation key (yeah) and the value is
     * the actual translation, in example, there could be a translation for the
     * Stone block
     *
     * <p>"block.minecraft.stone" -> "Stone"</p>
     *
     * @return The language translations
     */
    public @Unmodifiable Map<String, String> translations() {
        return translations;
    }

    @Override
    public @NotNull Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(
                ExaminableProperty.of("key", key),
                ExaminableProperty.of("translations", translations)
        );
    }

    @Override
    public String toString() {
        return examine(StringExaminer.simpleEscaping());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return key.equals(language.key)
                && translations.equals(language.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, translations);
    }

}
