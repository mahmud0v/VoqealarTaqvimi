package uz.tuit.voqealartaqvimi

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Event")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var date:String,
    var eventName:String,
    var eventIntervalTime:String
)