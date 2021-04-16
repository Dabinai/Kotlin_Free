package com.junxin.freepaykotlin.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.bean.BannerBean
import com.junxin.freepaykotlin.view.RoundImageView
import com.youth.banner.adapter.BannerAdapter

class MyBannerAdapter(mDatas: List<BannerBean.DataBean?>?) :
    BannerAdapter<BannerBean.DataBean, MyBannerAdapter.BannerViewHolder>(mDatas) {

    inner class BannerViewHolder(var imageView: RoundImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        var imageView = RoundImageView(parent?.context)
        imageView.setLayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return BannerViewHolder(imageView)
    }

    override fun onBindView(
        holder: BannerViewHolder?,
        data: BannerBean.DataBean?,
        position: Int,
        size: Int
    ) {
        var options = RequestOptions().fallback(R.mipmap.banner_main_one)
            .error(R.mipmap.banner_main_one)
        Glide.with(holder!!.imageView.context).load(data!!.img).apply(options)
            .into(holder!!.imageView)
    }
}