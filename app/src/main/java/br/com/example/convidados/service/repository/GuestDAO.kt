package br.com.example.convidados.service.repository

import androidx.room.*
import br.com.example.convidados.service.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(save: GuestModel): Long

    @Update
    fun update(save: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent(): List<GuestModel>

}