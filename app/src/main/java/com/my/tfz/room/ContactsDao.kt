package com.my.tfz.room

import androidx.room.*
import com.my.tfz.bean.ConstactBean
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contacts)

    @Insert
    suspend fun insterContacts(contacts: List<ConstactBean>)

    @Update
    suspend fun updataContact(contact: Contacts)

    @Delete
    suspend fun deleteContact(contact: Contacts)

    @Query("SELECT * FROM contact ORDER BY name ASC")
    suspend fun loadAllData():Flow<List<Contacts>>


}