package org.d3if3121.wordz.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Upsert
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {
    @Upsert
    suspend fun upsertWords(dataModel: Words)

    @Delete
    suspend fun deleteWords(dataModel: Words)

    @Query("SELECT * FROM words")
    fun getAllRecords(): Flow<List<Words>>

    @Query("SELECT * FROM words ORDER BY meaning ASC")
    fun getWordsOrderedByMeaning(): Flow<List<Words>>

    @Query("SELECT * FROM words ORDER BY words ASC")
    fun  getWordsOrderedByWords(): Flow<List<Words>>

    @Query("SELECT * FROM words ORDER BY dateAdded")
    fun getWordsOrderedByDateAdded(): Flow<List<Words>>

    @Query("SELECT * FROM words WHERE meaning < :number")
    fun filterPriorityLessThan(number: Int): Flow<List<Words>>

    @Query("SELECT * FROM words WHERE meaning > :number")
    fun filterPriorityGreaterThan(number: Int): Flow<List<Words>>

    @RawQuery(observedEntities = [Words::class])
    fun query(query: SupportSQLiteQuery): Flow<List<Words>>
}
