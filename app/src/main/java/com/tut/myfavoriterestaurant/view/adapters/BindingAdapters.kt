package com.tut.myfavoriterestaurant.view.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tut.myfavoriterestaurant.R


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context).applyDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.ic_fastfood)
                    .error(R.drawable.ic_fastfood)
            ).load(it).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("app:favourite")
    fun setFavourite(imageView: ImageView, favourite: Boolean?) {
        favourite?.let {
            val res = if (it) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            Glide.with(imageView.context).load(res).into(imageView)
        }

    }
}