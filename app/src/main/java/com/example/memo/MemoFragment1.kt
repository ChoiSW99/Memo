package com.example.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MemoFragment1 : Fragment() {
    private lateinit var recyclerView : RecyclerView

    var userList = arrayListOf<dataVo>(
        dataVo("11", "test1", "전주시", 30000000,"user_img_01"),
        dataVo("홍길동", "test2", "서울시",10000000, "user_img_02"),
        dataVo("김영수", "test3", "광주시", 20000000, "user_img_03"),
        dataVo("IU", "test1", "전주시", 30000000,"user_img_01"),
        dataVo("홍길동", "test2", "서울시",10000000, "user_img_02"),
        dataVo("김영수", "test3", "광주시", 20000000, "user_img_03"),
        dataVo("IU", "test1", "전주시", 30000000,"user_img_01"),
        dataVo("홍길동", "test2", "서울시",10000000, "user_img_02"),
        dataVo("김영수", "test3", "광주시", 20000000, "user_img_03"),
        dataVo("IU", "test1", "전주시", 30000000,"user_img_01"),
        dataVo("홍길동", "test2", "서울시",10000000, "user_img_02"),
        dataVo("김영수", "test3", "광주시", 20000000, "user_img_03")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView = inflater.inflate(R.layout.fragment_memo1, container, false)


        recyclerView = rootView.findViewById(R.id.recyclerView1) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CustomAdapter(requireContext(), userList)

        return rootView
    }

}


/*

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val mAdapter = CustomAdapter(this, userList)
    recycler_view.adapter = mAdapter

    val layout = LinearLayoutManager(this)
    recycler_view.layoutManager = layout
    recycler_view.setHasFixedSize(true)
}*/
