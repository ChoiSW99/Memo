package com.example.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MemoFragment4 : Fragment() {
    private lateinit var recyclerView : RecyclerView

    var db: AppDatabase? = null
    var memosList = ArrayList<DataMemo>() //메모들이 저장될 곳

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //초기화
        db = AppDatabase.getInstance(this.requireContext())

        //이전에 저장한 내용 모두 불러와서 추가하기
        val savedMemos = db!!.dataDao().getEtc()
        if(savedMemos.isNotEmpty()){
            memosList.addAll(savedMemos)
        }

        val rootView = inflater.inflate(R.layout.fragment_memo4, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView4) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CustomAdapter(requireContext(), memosList)

        return rootView
    }
}