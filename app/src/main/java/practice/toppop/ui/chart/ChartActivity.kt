package practice.toppop.ui.chart

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_chart.*
import practice.toppop.R
import practice.toppop.data.model.NetworkResponse
import practice.toppop.data.model.chart.ChartData
import practice.toppop.ui.ViewModelProviderFactory
import practice.toppop.ui.details.DetailsActivity
import practice.toppop.util.VerticalSpacingItemDecoration
import practice.toppop.util.hide
import practice.toppop.util.show
import practice.toppop.util.snackbar
import java.util.*
import javax.inject.Inject

private const val NORMAL = 1
private const val ASCENDING = 2
private const val DESCENDING = 3

class ChartActivity : DaggerAppCompatActivity(), ChartAdapter.OnArtistClicked {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var viewModel: ChartViewModel

    @Inject
    lateinit var itemDecoration: VerticalSpacingItemDecoration

    @Inject
    lateinit var chartAdapter: ChartAdapter

    private var swipeRefresh = false

    private var chartList: List<ChartData> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)


        initToolbar()

        viewModel = ViewModelProvider(this, providerFactory).get(ChartViewModel::class.java)
        subscribeObservers()
        initRecyclerView()

        initSwipeRefresh()

    }

    private fun initToolbar(){
        setSupportActionBar(chartToolbar)
        supportActionBar?.title = null
    }

    private fun initSwipeRefresh() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            chartSwipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.progressBar, null))
        }
        chartSwipeRefreshLayout.setOnRefreshListener {
            swipeRefresh = true
            viewModel.getChart()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_normal -> sortChart(NORMAL)
            R.id.action_sort_asc -> sortChart(ASCENDING)
            R.id.action_sort_desc -> sortChart(DESCENDING)
        }
        return true
    }

    private fun sortChart(selection: Int) {
        if(chartList.isNotEmpty()){
            when(selection){
                NORMAL -> Collections.sort(chartList, kotlin.Comparator { c1, c2 -> c1.position.compareTo(c2.position) })
                ASCENDING -> Collections.sort(chartList, kotlin.Comparator { c1, c2 -> c1.title.toLowerCase(Locale.ROOT).compareTo(c2.title.toLowerCase(Locale.ROOT)) })
                DESCENDING -> Collections.sort(chartList, kotlin.Comparator { c1, c2 -> c2.title.toLowerCase(Locale.ROOT).compareTo(c1.title.toLowerCase(Locale.ROOT)) })
            }
            chartRecyclerView.scheduleLayoutAnimation()
            chartAdapter.notifyDataSetChanged()
        }
    }


    private fun initRecyclerView() {
        chartRecyclerView.apply {
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(context)
            adapter = chartAdapter

            layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
            scheduleLayoutAnimation()
        }
    }

    private fun subscribeObservers() {
        viewModel.chart.observe(this, Observer {
            if (it != null) {
                chartRecyclerView.scheduleLayoutAnimation()
                chartAdapter.setData(it)
                chartList = it
            }
        })

        viewModel.errorHandling.observe(this, Observer {
            when (it.status) {
                NetworkResponse.NetworkStatus.LOADING -> if (!swipeRefresh) chartProgressBar.show()
                NetworkResponse.NetworkStatus.SUCCESS -> hideProgress()
                NetworkResponse.NetworkStatus.ERROR -> {
                    hideProgress()
                    window.decorView.rootView.snackbar("Error: ${it.message}")
                }
            }
        })
    }

    private fun hideProgress() {
        chartProgressBar.hide()
        chartSwipeRefreshLayout.isRefreshing = false
    }

    override fun onClick(chartData: ChartData) {
        startActivity(DetailsActivity.newInstance(this, chartData))
    }
}
