package org.d3if3121.wordz.model

import android.icu.text.CaseMap.Title

sealed interface WordsEvents {

    data class DeleteWords(val words: Words): WordsEvents

    data class SaveWords(
        val words: String,
        val meaning: String,
        val notes: String,
        val dateAdded: String
    ): WordsEvents

}