package com.example.claimantapp.data.local

import androidx.room.Dao
import androidx.room.Upsert


@Dao
interface UserDataDao {
    @Upsert
    suspend fun upsertData(userData: UserData)
}