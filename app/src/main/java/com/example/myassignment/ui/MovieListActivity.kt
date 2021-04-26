package com.example.myassignment.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myassignment.adapter.ListMovieAdapter
import com.example.myassignment.dagger.component.ActivityComponent
import com.example.myassignment.dagger.component.DaggerActivityComponent
import com.example.myassignment.databinding.ActivityMoviewlistBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myassignment.App
import com.example.myassignment.R
import com.example.myassignment.utility.Utils
import javax.inject.Inject


/**
 * Created by Shabbir on 25/4/21$.
 */
class MovieListActivity : AppCompatActivity() {

    private var popularMoviesPage = 1
    lateinit var mBinding:ActivityMoviewlistBinding
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var movieCategory: String
    private lateinit var adapter: ListMovieAdapter

    @Inject
    lateinit var mUtils: Utils

    private val app: App
        get() = applicationContext as App

    private var component: ActivityComponent? = null

    private lateinit var viewModel: MovieListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviewlist)

        component = DaggerActivityComponent.builder().appComponent(app.appComponent).build()

        component?.inject(this)

        mBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_moviewlist
        )


        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        setMovieListToRV()
        callApi()


    }

    private fun getMovieList() {

        try {

            viewModel.init("", popularMoviesPage)
            viewModel.getResponse()?.observe(
                this,
                androidx.lifecycle.Observer { Response ->


                    if (Response != null) {

                        adapter.appendMovies(Response.networkMovie)


                    } else {
                        mUtils.showSnackbarLong("Something went wrong", mBinding.root)
                    }


                })
        } catch (e: Exception) {

            Log.e("Exeption-->", "" + e.toString())
            mUtils.showSnackbarLong(e.toString(), mBinding.root)

        }


    }

    private fun setMovieListToRV() {
        mBinding.recyclerview.adapter = ListMovieAdapter(mutableListOf())

        adapter = mBinding.recyclerview.adapter as ListMovieAdapter

        layoutManager =
            GridLayoutManager(mBinding.root.context, 2, GridLayoutManager.VERTICAL, false)

        mBinding.recyclerview.layoutManager = layoutManager

       /* viewModel.mutableLiveData?.observe(viewLifecycleOwner, Observer {
            adapter.appendMovies(it)
        })*/

        /*viewModel.navigateToSelectProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailFragment(it.id)
                )
                viewModel.displayPropertyDetailsCompleted()
            }
        })*/
    }

    private fun attachPopularMoviesOnScrollListener() {
        mBinding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    mBinding.recyclerview.removeOnScrollListener(this)
                    popularMoviesPage++
                   callApi()
                }
            }
        })
    }

    fun callApi(){

        if (mUtils.isOnline(this)) {

            getMovieList()
            attachPopularMoviesOnScrollListener()
        } else {
            mUtils.showSnackbarLong("Please check your internet connection", mBinding.root)
        }
        
    }


}