package com.example.claimantapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [UserData::class],
    version=1)
abstract class UserDatabase:RoomDatabase() {
    abstract val dao:UserDataDao
}