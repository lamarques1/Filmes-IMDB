package com.example.filmesimdb.moviedetails

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmesimdb.R
import com.example.filmesimdb.moviedetails.adapter.EpisodeListAdapter
import com.example.filmesimdb.moviedetails.adapter.MovieDetailsAdapter
import com.example.filmesimdb.moviedetails.model.MovieDetails
import com.example.filmesimdb.moviedetails.model.Season
import com.example.filmesimdb.utils.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_spinner.*

class MovieDetailsView : BaseActivity(),
    MovieDetailsContract.View {

    private lateinit var toolbar : Toolbar

    private lateinit var imdbID : String
    private lateinit var txtTitle : TextView
    private lateinit var txtYear : TextView
    private lateinit var txtRunTime : TextView
    private lateinit var ratingBar : RatingBar
    private lateinit var txtGenre : TextView
    private lateinit var txtPlot : TextView
    private lateinit var txtDirector : TextView
    private lateinit var txtWriter : TextView
    private lateinit var txtActors : TextView
    private lateinit var txtReleased : TextView
    private lateinit var txtLanguage : TextView
    private lateinit var txtCountry : TextView
    private lateinit var txtAwards : TextView
    private lateinit var txtProdution : TextView
    private lateinit var imgPoster : ImageView

    private lateinit var layoutDetails : LinearLayout
    private lateinit var layoutEpisodes : LinearLayout

    private lateinit var tabLayout : TabLayout
    private lateinit var moreDetails : TextView
    private lateinit var moreDetailsLayout : LinearLayout

    private lateinit var adapter : MovieDetailsAdapter
    private lateinit var recyclerViewRatings : RecyclerView

    private lateinit var spinner : Spinner
    private lateinit var adapterEpisode: EpisodeListAdapter
    private lateinit var recyclerViewEpisodes: RecyclerView

    private var selecSeason = ""

    private lateinit var presenter : MovieDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        imdbID = intent.getStringExtra("imdbID")!!

        setPresenter()
        initViews()
        initListeners()

        toolbar.title = "Details"
        setSupportActionBar(toolbar)
        showProgress(true)

        presenter.onLoadMovieDetails(imdbID)

    }

    override fun setPresenter() {
        presenter = MovieDetailsPresenter(this)
    }

    override fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px)

        txtTitle = findViewById(R.id.txtTitle)
        txtYear = findViewById(R.id.txtYear)
        txtRunTime = findViewById(R.id.txtRunTime)
        ratingBar = findViewById(R.id.ratingBar)
        txtGenre = findViewById(R.id.txtGenre)
        txtPlot = findViewById(R.id.txtPlot)
        txtDirector = findViewById(R.id.txtDirector)
        txtWriter = findViewById(R.id.txtWriter)
        txtActors = findViewById(R.id.txtActors)
        txtReleased = findViewById(R.id.txtReleased)
        txtLanguage = findViewById(R.id.txtLanguage)
        txtCountry = findViewById(R.id.txtCountry)
        txtAwards = findViewById(R.id.txtAwards)
        txtProdution = findViewById(R.id.txtProdution)
        imgPoster = findViewById(R.id.imgPoster)
        recyclerViewRatings = findViewById(R.id.recyclerViewRatings)

        spinner = findViewById(R.id.spinner)
        layoutEpisodes = findViewById(R.id.episodes_layout)
        recyclerViewEpisodes = findViewById(R.id.recyclerViewEpisodes)

        layoutDetails = findViewById(R.id.layout_movie_details)
        tabLayout = findViewById(R.id.tabLayoutDetails)
        moreDetails = findViewById(R.id.more_details_area)
        moreDetailsLayout = findViewById(R.id.more_details_layout)
    }

    override fun initListeners() {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        //TODO: Refatorar
        //TODO: Utilizar fragments para os conteudos das tabs
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tabItem: TabLayout.Tab) {
                when(tabItem.position){
                    0 -> {
                        if (layoutEpisodes.visibility == View.VISIBLE)
                            layoutEpisodes.visibility = View.GONE
                        else
                            layoutEpisodes.visibility = View.VISIBLE
                    }
                    1 -> {
                        if (moreDetailsLayout.visibility == View.VISIBLE)
                            moreDetailsLayout.visibility = View.GONE
                        else
                            moreDetailsLayout.visibility = View.VISIBLE
                    }
                }

            }

            override fun onTabUnselected(tabItem: TabLayout.Tab) {
                when(tabItem.position){
                    0 -> {
                        moreDetailsLayout.visibility = View.VISIBLE
                        layoutEpisodes.visibility = View.GONE
                    }
                    1 -> {
                        moreDetailsLayout.visibility = View.GONE
                        layoutEpisodes.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabSelected(tabItem: TabLayout.Tab) {
                when(tabItem.position){
                    0 -> {
                        moreDetailsLayout.visibility = View.GONE
                        layoutEpisodes.visibility = View.VISIBLE
                    }
                    1 -> {
                        moreDetailsLayout.visibility = View.VISIBLE
                        layoutEpisodes.visibility = View.GONE
                    }
                }
            }
        })

        //TODO: Inserir spinner listener
    }

    /**
     * Exibe os detalhes do filme recebidos do presenter
     */
    override fun displayMovieDetails(movie : MovieDetails) {

        toolbar.title = movie.title

        txtTitle.text = movie.title
        txtYear.text = movie.year
        txtRunTime.text = movie.runtime
        ratingBar.rating = (movie.imdbRating/2).toFloat()
        txtGenre.text = movie.genre
        txtPlot.text = movie.plot
        txtDirector.text = movie.director
        txtWriter.text = movie.writer
        txtActors.text = movie.actors
        txtReleased.text = movie.released
        txtLanguage.text = movie.language
        txtCountry.text = movie.country
        txtAwards.text = movie.awards
        txtProdution.text = movie.production

        val posterUri = Uri.parse(movie.poster)
        val colorDrawable = ColorDrawable(0xFFFFFF)
        Picasso.get()
            .load(posterUri)
            .fit().centerCrop()
            .placeholder(colorDrawable)
            .into(imgPoster)

        recyclerViewRatings.visibility = View.VISIBLE
        recyclerViewRatings.layoutManager = LinearLayoutManager(this)
        adapter = MovieDetailsAdapter(
            applicationContext,
            movie.ratings
        )
        recyclerViewRatings.adapter = adapter

        layoutDetails.visibility = View.VISIBLE
        showProgress(false)
    }

    /**
     * Exibe as temporadas recebidas do presenter
     */
    override fun displaySeasonInfo(season: Season) {
        val numSeasons = 1..season.totalSeasons
        val seasons = mutableListOf<String>()
        numSeasons.forEach { seasons.add("Season $it") }

        val arrayAdapter = ArrayAdapter<String>(this, R.layout.item_spinner, seasons)
        spinner.adapter = arrayAdapter

        layoutEpisodes.visibility = View.VISIBLE
        recyclerViewEpisodes.layoutManager = LinearLayoutManager(this)
        adapterEpisode = EpisodeListAdapter(applicationContext, season.episodes)
        recyclerViewEpisodes.adapter = adapterEpisode
    }

    /**
     * Caso o resultado for do tipo serie, exibe o tabLayout com as opções de
     * mais detalhes ou listar episódios. Senão, fixa a exibição de mais detalhes.
     */
    override fun showTabs(show: Boolean) {
        if (show){
            spinner.visibility = View.VISIBLE
            tabLayout.visibility = View.VISIBLE
            moreDetails.visibility = View.GONE
            moreDetailsLayout.visibility = View.GONE
        }else{
            spinner.visibility = View.GONE
            tabLayout.visibility = View.GONE
            moreDetails.visibility = View.VISIBLE
            moreDetailsLayout.visibility = View.VISIBLE
        }
    }


/*    fun showTab(tabItem: TabLayout.Tab, show: Boolean){
        when(tabItem.position){
             0 -> {
                 if (show)
                     layoutEpisodes.visibility = View.VISIBLE
                 else
                     layoutEpisodes.visibility = View.GONE
             }
             1 -> {
                 if (show)
                     layoutDetails.visibility = View.VISIBLE
                 else
                     layoutDetails.visibility = View.GONE
             }
        }
    }*/

    /**
     * Caso a busca falhe, exibe uma mensagem de erro
     * @param errorId - Id da string de erro para exibição
     */
    override fun displayErrorMessage(errorId: Int) {
        Toast.makeText(this, errorId, Toast.LENGTH_LONG).show()
        showProgress(false)
    }
}
