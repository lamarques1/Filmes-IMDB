package com.example.filmesimdb.movielist

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmesimdb.R
import com.example.filmesimdb.movielist.adapter.MovieListAdapter
import com.example.filmesimdb.movielist.model.Movie
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListView : AppCompatActivity(), MovieListContract.View {

    private lateinit var toolbar : Toolbar

    private lateinit var etTitle : EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSearch : ImageView
    private lateinit var adapter : MovieListAdapter
    private lateinit var presenter : MovieListPresenter

    private lateinit var tabLayout: TabLayout
    private lateinit var textView: TextView
    private var searchType = "Movie"
    private var mQuery = ""

    private lateinit var mSearch : MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)


        setPresenter(this)
        initViews()
        initListeners()

        toolbar.title = "IMDb Search"
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        mSearch = menu!!.findItem(R.id.app_bar_search)

        val mSearchView = mSearch.actionView as SearchView

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotEmpty()!!){
                    mQuery = query!!
                }
                presenter.resetPage()
                presenter.onLoadMovies(query, searchType)
                mSearchView.onHoverChanged(false)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        return true
    }

    override fun setPresenter(view: MovieListContract.View) {
        presenter = MovieListPresenter(view)
    }

    override fun initViews() {
        toolbar = findViewById(R.id.toolbar)

        etTitle = findViewById(R.id.etTitle)
        btnSearch = findViewById(R.id.btnSearch)
        recyclerView = findViewById(R.id.recyclerViewMovies)

        textView = findViewById(R.id.textView)
        tabLayout = findViewById(R.id.tabLayout)
    }

    /**
     * Ao pesquisar, carrega o resultado por meio do presenter
     */
    override fun initListeners() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
                return
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                return
            }

            override fun onTabSelected(p0: TabLayout.Tab) {
                Log.d("Teste", p0.position.toString())
                searchType = p0.text.toString()
                if (mQuery.isNotEmpty()){
                    presenter.resetPage()
                    presenter.onLoadMovies(mQuery, searchType)
                }

            }
        })

        btnSearch.setOnClickListener {
            presenter.resetPage()
            presenter.onLoadMovies(etTitle.text.toString(), searchType)
        }
    }

    /**
     * Exibe a lista de filmes recebida do presenter
     */
    override fun displayMovies(movies: List<Movie>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            MovieListAdapter(applicationContext, movies)
        recyclerView.adapter = adapter

        /**
         * Scroll Infinito
         * Ao chegar no fim da lista, realiza outra request com a pagina seguinte
         */
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                adapter.setOnBottomReachedListener(object :
                    MovieListAdapter.OnBottomReachedListener {
                    override fun onBottomReached(position: Int) {
                        presenter.onLoadMovies(etTitle.text.toString(), searchType)
                    }
                })
            }
        })
    }

    override fun getAdapter() : MovieListAdapter{
        return adapter
    }

    /**
     * Caso a busca falhe, exibe uma mensagem de erro
     * @param errorId - Id da string de erro para exibição
     */
    override fun displayErrorMessage(errorId: Int) {
        Toast.makeText(this, errorId, Toast.LENGTH_SHORT).show()
    }
}

