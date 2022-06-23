package uz.tuit.voqealartaqvimi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class EventAdapter : RecyclerView.Adapter<EventViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<EventEntity>() {
        override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity) =
            oldItem == newItem


    }

     val differ = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val eventNameView: TextView = holder.itemView.findViewById(R.id.event_text_view)
        val timeTextView: TextView = holder.itemView.findViewById(R.id.event_time)
        eventNameView.text = differ.currentList[position].eventName
        timeTextView.text = differ.currentList[position].eventIntervalTime
    }

    override fun getItemCount() = differ.currentList.size
}