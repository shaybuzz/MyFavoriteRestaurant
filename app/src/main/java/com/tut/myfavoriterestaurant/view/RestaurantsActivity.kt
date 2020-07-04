package com.tut.myfavoriterestaurant.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tut.myfavoriterestaurant.R
import com.tut.myfavoriterestaurant.view.adapters.RestaurantsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class RestaurantsActivity : AppCompatActivity() {

    private lateinit var adapter: RestaurantsAdapter
    private lateinit var viewModel: RestaurantsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            RestaurantsViewModelFactory(application)
        ).get(RestaurantsViewModel::class.java)

        viewModel.restaurants.observe(this, Observer {
            adapter.update(it)
        })
        initList()
    }

    private fun initList() {
        adapter = RestaurantsAdapter()
        adapter.updateFavourite = { id: Int, favourite: Boolean ->
            viewModel.updateFavourite(id, favourite)
        }
        recyclerView.adapter = adapter
    }
}