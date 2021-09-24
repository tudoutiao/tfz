package com.my.tfz.util

import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.my.tfz.MainApplication
import com.my.tfz.R

class ImageUtil {

    var avatarRequestOptions: RequestOptions = RequestOptions.bitmapTransform(CircleCrop())
        .placeholder(R.drawable.ic_avatar_default)
        .error(R.drawable.ic_avatar_default)


    fun setCircleHead(url: String?, imageView: ImageView) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.ic_avatar_default)
            return
        }
        Glide.with(MainApplication.context).load(url)
            .apply(avatarRequestOptions).into(imageView)
    }



}