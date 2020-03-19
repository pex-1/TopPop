package practice.toppop.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_details.view.*
import practice.toppop.R
import practice.toppop.data.model.album.Track
import javax.inject.Inject

class DetailsAdapter @Inject constructor() : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>(){

    private var trackList = listOf<Track>()

    inner class DetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_details, parent, false))
    }

    override fun getItemCount(): Int = trackList.size

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val track = trackList[position]

        with(holder.itemView){
            artistDetailsItemTextView.text = track.title
            songDetailsItemTextView.text = track.title
        }
    }

    fun setData(newTrackList: List<Track>){
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(ChartItemDiffCallback(trackList, newTrackList))
        trackList = newTrackList
        diffResult.dispatchUpdatesTo(this)
    }

    class ChartItemDiffCallback(private val oldTrackList: List<Track>, private val newTrackList: List<Track>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldTrackList[oldItemPosition].id == newTrackList[newItemPosition].id
        }

        override fun getOldListSize(): Int = oldTrackList.size

        override fun getNewListSize(): Int = newTrackList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldTrackList[oldItemPosition] == newTrackList[newItemPosition]
        }

    }
}