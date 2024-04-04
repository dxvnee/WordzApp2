package org.d3if3121.wordz.model

import androidx.room.Entity
import androidx.room.PrimaryKey


//POJO
@Entity
data class Words(
    val words: String,
    val meaning: String,
    val notes: String,
    val dateAdded: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

enum class Operator {
    GREATER_THAN,
    LESS_THAN
}

enum class Sort {
    TEXT,
    PRIORITY
}

enum class Order {
    ASC,
    DESC
}
