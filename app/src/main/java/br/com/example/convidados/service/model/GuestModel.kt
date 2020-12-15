package br.com.example.convidados.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entidade
@Entity(tableName = "Guest")//Sera salva no banco de dados - Usando API room para criação do banco de dados
class GuestModel{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0

    @ColumnInfo(name = "name")
    var name:String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = true
}