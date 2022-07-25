package com.example.movie.view.fragment.home.viewpager

import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.helper.showToast
import com.example.movie.view.fragment.home.HomeAdapter
import com.example.movie.view.fragment.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    var page = 1

    private val nowplayingAdapter = HomeAdapter()
    private val topratedAdapter = HomeAdapter()
    private val popularAdapter = HomeAdapter()

    override val bindingInflater: (LayoutInflater) -> FragmentMovieBinding
        get() = FragmentMovieBinding::inflate

    override fun initialization() {
        setupView()
    }

    override fun observeViewModel() {
        viewModel.getNowPlaying(page)
        viewModel.nowPlaying.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        nowplayingAdapter.addData(data)
                        binding.layoutNowPlaying.apply {
                            shimmerPoster.root.visibility = GONE
                            rvPoster.visibility = VISIBLE
                        }
                    } else
                        Log.e("rvKosong", "getMovie: $data")
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                       showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutNowPlaying.shimmerPoster.root.startShimmerAnimation()
                }
            }
        }

        viewModel.getTopRated(page)
        viewModel.topRated.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        topratedAdapter.addData(data)
                        binding.layoutTopRated.apply {
                            shimmerPoster.root.visibility = GONE
                            rvPoster.visibility = VISIBLE
                        }
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutTopRated.shimmerPoster.root.startShimmerAnimation()
                }
            }
        }

        viewModel.getPopular(page)
        viewModel.popular.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        popularAdapter.addData(data)
                        binding.layoutPopular.apply {
                            showToast(view?.context, "$data")
                            shimmerPoster.root.visibility = GONE
                            rvPoster.visibility = VISIBLE
                        }
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutPopular.shimmerPoster.root.startShimmerAnimation()
                }
            }
        }

    }

    private fun setupView() {
        binding.layoutNowPlaying.apply {
            txtCategory.text = getString(R.string.movie_category_nowplaying)
            rvPoster.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = nowplayingAdapter
            }
        }
        binding.layoutTopRated.apply {
            txtCategory.text = getString(R.string.movie_category_toprated)
            rvPoster.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = topratedAdapter
            }
        }
        binding.layoutPopular.apply {
            txtCategory.text = getString(R.string.movie_category_popular)
            rvPoster.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = popularAdapter
            }
        }

    }
}