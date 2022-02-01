package com.example.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val context: Context, private val dataList: ArrayList<dataVo>) :
    RecyclerView.Adapter<CustomAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userPhoto = itemView.findViewById<ImageView>(R.id.userImg)
        private val userName = itemView.findViewById<TextView>(R.id.userNameTxt)
        private val userPay = itemView.findViewById<TextView>(R.id.payTxt)
        private val userAddress: TextView = itemView.findViewById<TextView>(R.id.addressTxt)


        fun bind(dataVo: dataVo, context: Context) {
                //사진 처리
            if (dataVo.photo != "") {
                val resourceId =
                    context.resources.getIdentifier(dataVo.photo, "drawable", context.packageName)
                        if (resourceId > 0) {
                            userPhoto.setImageResource(resourceId)
                        } else {
                            userPhoto.setImageResource(R.mipmap.ic_launcher_round)
                        }
            } else {
                userPhoto.setImageResource(R.mipmap.ic_launcher_round)
            }
            //TextView에 데이터 세팅
            userName.text = dataVo.name
            userPay.text = dataVo.pay.toString()
            userAddress.text = dataVo.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item, parent, false)
            return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}