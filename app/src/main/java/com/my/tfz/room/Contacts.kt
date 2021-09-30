package com.my.tfz.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contacts(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var position: String,
    var project: String,
    var department: String,
    var platform: String,
    var initials: Char,
    var sex: Char,
)
