package com.tut.myfavoriterestaurant.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tut.myfavoriterestaurant.databinding.RestaurentItemLayoutBinding
import com.tut.myfavoriterestaurant.model.Restaurant

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    var updateFavourite: ((Int, Boolean) -> Unit)? = null

    private var restaurants: List<Restaurant> = mutableListOf()

    inner class RestaurantViewHolder(val binding: RestaurentItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    fun update(theRestaurants: List<Restaurant>) {
        restaurants = theRestaurants
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            RestaurentItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants.get(position)
        holder.binding.favourite.setOnClickListener {
            updateFavourite?.let {listener ->
                restaurant.isFavourite = !restaurant.isFavourite
                holder.binding.restaurant = restaurant
                holder.binding.executePendingBindings()
                listener(restaurant.id, restaurant.isFavourite)
            }
        }
        holder.binding.restaurant = restaurant
        holder.binding.executePendingBindings()
    }
}