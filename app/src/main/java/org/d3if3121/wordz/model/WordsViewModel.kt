package org.d3if3121.wordz.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn


class WordsViewModel(
    private val dao: WordsDao
): ViewModel() {

    val _state = MutableStateFlow(WordsState())
    private val isSortedDateAdded = MutableStateFlow(true)
    private var wordsList =
        isSortedDateAdded.flatMapLatest { sort ->
            if (sort) {
                dao.getWordsOrderedByDateAdded()
            } else {
                dao.getWordsOrderedByWords()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state =
        combine(_state, isSortedDateAdded, wordsList) { state, isSortedDateAdded, wordsList ->
            state.copy(
                wordsList = wordsList
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WordsState())

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: WordsEvents){
        when (event) {
            is WordsEvents.DeleteWords -> {
                viewModelScope.launch {
                    dao.deleteWords(event.words)
                }
            }
            is WordsEvents.SaveWords -> {
                val words = Words(
                    words = state.value.words.value,
                    meaning = state.value.meaning.value,
                    notes = state.value.notes.value,
                    dateAdded = state.value.dateAdded.value

                )

                viewModelScope.launch {
                    dao.upsertWords(words)
                }

                _state.update{
                    it.copy(
                        words = mutableStateOf(""),
                        meaning = mutableStateOf(""),
                        notes = mutableStateOf(""),
                        dateAdded = mutableStateOf("")
                    )
                }
            }
            else -> {}
        }
    }
}