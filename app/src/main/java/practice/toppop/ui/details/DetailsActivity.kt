package practice.toppop.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*
import practice.toppop.R
import practice.toppop.data.model.NetworkResponse
import practice.toppop.data.model.chart.ChartData
import practice.toppop.ui.ViewModelProviderFactory
import practice.toppop.util.hide
import practice.toppop.util.makeStatusBarTransparent
import practice.toppop.util.show
import practice.toppop.util.snackbar
import javax.inject.Inject

class DetailsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var detailsAdapter: DetailsAdapter


    companion object {
        const val DETAILS = "DETAILS"
        fun newInstance(context: Context, chartData: ChartData): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DETAILS, chartData)
            return intent
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        makeStatusBarTransparent()
        initToolbar()

        viewModel = ViewModelProvider(this, providerFactory).get(DetailsViewModel::class.java)
        val data = intent.getParcelableExtra<ChartData>(DETAILS)
        if (data != null) {
            setUi(data)
            viewModel.getAlbum(data.album.id)
            subscribeObservers()
        }

    }

    private fun subscribeObservers() {
        viewModel.album.observe(this, Observer {
            detailsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = detailsAdapter
                detailsAdapter.setData(it.data)
            }
        })
        viewModel.errorHandling.observe(this, Observer {
            when (it.status) {
                NetworkResponse.NetworkStatus.LOADING -> detailsProgressBar.show()
                NetworkResponse.NetworkStatus.SUCCESS -> detailsProgressBar.hide()
                NetworkResponse.NetworkStatus.ERROR -> {
                    detailsProgressBar.hide()
                    window.decorView.rootView.snackbar("Error: ${it.message}")
                }
            }
        })
    }

    private fun setUi(data: ChartData) {
        picasso.load(data.album.coverBig)
            .into(albumDetailsImageView)

        albumNameDetailsTextView.text = data.album.title
        artistSongDetailsTextView.text = "${data.artist.name}  -  ${data.title}"
    }

    private fun initToolbar() {
        setSupportActionBar(detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = null
    }
}
