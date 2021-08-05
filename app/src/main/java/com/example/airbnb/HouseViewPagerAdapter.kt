package com.example.airbnb

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class HouseViewPagerAdapter(val itemClicked: (HouseModel) -> Unit): ListAdapter<HouseModel, HouseViewPagerAdapter.ItemViewHolder>(differ) {

    inner class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(houseModel: HouseModel){
            val titleTextView = view.findViewById<TextView>(R.id.tv_title)
            val priceTextView = view.findViewById<TextView>(R.id.tv_price)
            val thumbnailImageView = view.findViewById<ImageView>(R.id.iv_thumbnail)

            titleTextView.text = houseModel.title
            priceTextView.text = houseModel.price

            view.setOnClickListener{
                itemClicked(houseModel)
            }

            Glide
                .with(thumbnailImageView.context)
                .load(houseModel.imgUrl)
                .transform(CenterCrop(), RoundedCorners(dpToPx(thumbnailImageView.context, 12)))   //이부분 1줄 안되네
                .into(thumbnailImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.item_house_detail_for_viewpager, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private fun dpToPx(context: Context, dp: Int): Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    companion object{
        val differ = object: DiffUtil.ItemCallback<HouseModel>(){
            override fun areItemsTheSame(oldItem: HouseModel, newItem: HouseModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HouseModel, newItem: HouseModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}