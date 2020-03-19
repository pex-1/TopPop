package practice.toppop.ui.chart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_chart.view.*
import practice.toppop.R
import practice.toppop.data.model.chart.ChartData
import javax.inject.Inject

class ChartAdapter @Inject constructor(private val clickListener: OnArtistClicked, private val picasso: Picasso) :RecyclerView.Adapter<ChartAdapter.ChartViewHolder>() {

    private var chart = listOf<ChartData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        return ChartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chart, parent, false))
    }

    override fun getItemCount(): Int = chart.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        val single = chart[position]

        with(holder.itemView){
            positionItemTextView.text = single.position.toString()
            artistItemTextView.text = single.artist.name
            songItemTextView.text = single.title
            durationItemTextView.text = "${(single.duration/60).toString().padStart(2,'0')}:${(single.duration%60).toString().padStart(2,'0')}"
            picasso.load(single.album.coverMedium)
                .placeholder(R.drawable.ic_album)
                .into(artistItemImageView)

            rootView.setOnClickListener{
                clickListener.onClick(single)
            }

        }
    }

    fun setData(newChart: List<ChartData>){
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(ChartItemDiffCallback(chart, newChart))
        chart = newChart
        diffResult.dispatchUpdatesTo(this)
    }

    class ChartItemDiffCallback(private val oldChartList: List<ChartData>, private val newChartList: List<ChartData>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldChartList[oldItemPosition].id == newChartList[newItemPosition].id
        }

        override fun getOldListSize(): Int = oldChartList.size

        override fun getNewListSize(): Int = newChartList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldChartList[oldItemPosition] == newChartList[newItemPosition]
        }

    }
    inner class ChartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    interface OnArtistClicked{
        fun onClick(chartData: ChartData)
    }
}