package uz.tuit.voqealartaqvimi

import androidx.room.*

@Dao
interface EventDao {

    @Query("select *from Event where date =:date")
    fun getDateEvents(date:String): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEvent(event:EventEntity)

    @Update
    fun updateEvent(event:EventEntity)

    @Delete
    fun deleteEvent(event:EventEntity)





}