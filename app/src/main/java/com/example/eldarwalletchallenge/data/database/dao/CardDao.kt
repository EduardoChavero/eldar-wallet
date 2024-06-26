package com.example.eldarwalletchallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwalletchallenge.data.database.entities.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM cards_table WHERE ownerId = :userId")
    suspend fun getUserCards(userId: Long): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewCard(card: CardEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cards: List<CardEntity>)

    @Query("DELETE FROM cards_table")
    suspend fun removeAllCards()

}