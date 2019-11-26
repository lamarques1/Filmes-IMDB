package com.example.filmesimdb.movielist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmesimdb.R
import com.example.filmesimdb.movielist.adapter.MovieListAdapter
import com.example.filmesimdb.movielist.model.Movie
import com.example.filmesimdb.utils.BaseActivity
import com.google.android.material.tabs.TabLayout

class MovieListView : BaseActivity(), MovieListContract.View {

    private lateinit var toolbar : Toolbar

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MovieListAdapter
    private lateinit var presenter : MovieListPresenter

    private lateinit var tabLayout : TabLayout
    private var searchType = "Movie"
    private var mQuery = ""

    private lateinit var empty_state : View
    private lateinit var mSearch : MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)


        setPresenter(this)
        initViews()
        initListeners()

        empty_state.visibility = View.VISIBLE
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
                    mQuery = query
                }
                hideKeyboard(mSearchView)
                showProgress(true)
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

        recyclerView = findViewById(R.id.recyclerViewMovies)
        empty_state = findViewById(R.id.empty_state)

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
                searchType = p0.text.toString()
                if (mQuery.isNotEmpty()){
                    presenter.resetPage()
                    showProgress(true)
                    presenter.onLoadMovies(mQuery, searchType)
                }

            }
        })
    }

    /**
     * Exibe a lista de filmes recebida do presenter
     */
    override fun displayMovies(movies: List<Movie>) {
        empty_state.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter =
            MovieListAdapter(applicationContext, movies)
        recyclerView.adapter = adapter

        showProgress(false)

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
                        presenter.onLoadMovies(mQuery, searchType)
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
        recyclerView.visibility = View.GONE
        empty_state.visibility = View.VISIBLE
        showProgress(false)
        Toast.makeText(this, errorId, Toast.LENGTH_SHORT).show()
    }
}

