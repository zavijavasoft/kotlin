/*
 * Copyright 2010-2015 JetBrains s.r.o.
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

package org.jetbrains.kotlin.idea.highlighter

import com.intellij.codeInsight.highlighting.HighlightUsagesHandler
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import org.jetbrains.kotlin.test.InTextDirectivesUtils
import kotlin.test.assertEquals

public abstract class AbstractHighlightExitPointsTest : LightCodeInsightFixtureTestCase() {
    public fun doTest(testDataPath: String) {
        myFixture.configureByFile(testDataPath)
        HighlightUsagesHandler.invoke(myFixture.getProject(), myFixture.getEditor(), myFixture.getFile());

        val text = myFixture.getFile().getText()
        val expectedToBeHighlighted = InTextDirectivesUtils.findLinesWithPrefixesRemoved(text, "//HIGHLIGHTED:")
        val highlighters = myFixture.getEditor().getMarkupModel().getAllHighlighters()
        val actual = highlighters.map { text.substring(it.getStartOffset(), it.getEndOffset()) }
        assertEquals(expectedToBeHighlighted, actual)
    }
}