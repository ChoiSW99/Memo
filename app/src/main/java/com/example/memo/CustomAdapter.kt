package com.example.memo

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//recyclerview adapter
class CustomAdapter(private val context: Context, private val dataList: ArrayList<DataMemo>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var datas = mutableListOf<DataMemo>()

    interface OnItemClickListener{
        fun onItemClick(v:View, pos : Int)

    }

    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item, parent, false)
        return ViewHolder(view).listen { position, type ->
            Log.d("뷰홀더 내 아이템 터치", "터치" + datas[position].title)

            if(position != RecyclerView.NO_POSITION) {
                //터치 시 수행할 일 작성
                Log.d("뷰홀더 내 아이템 터치", "터치" + datas[position].title)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])

    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val memoTitle = itemView.findViewById<TextView>(R.id.memoTitle)
        private val memoContents = itemView.findViewById<TextView>(R.id.memoContents)

        fun bind(dataMemo: DataMemo) {

            //view_item.xml의 TextView들에 데이터 세팅
            memoTitle.text = dataMemo.title
            memoContents.text = dataMemo.contents

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                itemView.setOnClickListener {
                    Intent(context, Edit_DeleteActivity::class.java).apply {
                        putExtra("memoId", dataMemo.id)
                        putExtra("memoImportance", dataMemo.importance)
                        putExtra("memoTitle", dataMemo.title)
                        putExtra("memoContents", dataMemo.contents)
                        putExtra("memoContents", dataMemo.contents)
                        putExtra("memoPosition", pos)

                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.run { context.startActivity(this) }
                    listener?.onItemClick(itemView,pos)
                }
            }
        }

    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            Log.d("저장된거 반영?","??")

            event.invoke(getAdapterPosition(), itemViewType)
        }
        return this
    }
}