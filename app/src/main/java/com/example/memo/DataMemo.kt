package com.example.memo


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataMemo(
    @PrimaryKey(autoGenerate = true) val id : Long,
    @ColumnInfo(name="importance") var importance : String, //중요도
    @ColumnInfo(name="title") var title: String?, //메모 제목
    @ColumnInfo(name="contents")var contents: String? //메모 내용
)
