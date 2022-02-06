package com.example.memo

import androidx.room.*

@Dao
interface DataDao {
    @Query("SELECT * FROM DataMemo")
    fun getAll() : List<DataMemo>

    @Query("SELECT * FROM DataMemo WHERE importance='일상' ")
    fun getDaily() : List<DataMemo>

    @Query("SELECT * FROM DataMemo WHERE importance='중요' ")
    fun getImportance() : List<DataMemo>

    @Query("SELECT * FROM DataMemo WHERE importance='기타' ")
    fun getEtc() : List<DataMemo>

    @Insert
    fun insertAll(vararg memos : DataMemo)

    @Insert
    fun insert(memo : DataMemo)

    @Query("UPDATE DataMemo SET importance = :imp, title=:ti, contents=:con WHERE id = :userId")
    fun updateUserById(userId: Long, imp:String, ti:String, con:String): Int

    @Query("DELETE FROM DataMemo WHERE id = :userId")
    fun deleteUserById(userId: Long): Int
}