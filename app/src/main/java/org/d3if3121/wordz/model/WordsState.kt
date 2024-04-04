package org.d3if3121.wordz.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WordsState(
    val wordsList: List<Words> = emptyList(),
    val words: MutableState<String> = mutableStateOf(""),
    val meaning: MutableState<String> = mutableStateOf(""),
    val notes: MutableState<String> = mutableStateOf(""),
    val dateAdded: MutableState<String> = mutableStateOf(""),

    )