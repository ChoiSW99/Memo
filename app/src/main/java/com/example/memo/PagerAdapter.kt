package com.example.memo


import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class MyAdapter(fa: FragmentActivity?, var mCount: Int) : FragmentStateAdapter(
    fa!!
) {
    override fun createFragment(position: Int): Fragment {
        return when (getRealPosition(position)){
            0 -> MemoFragment1()
            1 -> MemoFragment2()
            2 -> MemoFragment3()
            else -> MemoFragment4()
        }
    }

    override fun getItemCount(): Int {
        return 2000
    }

    fun getRealPosition(position: Int): Int {
        return position % mCount
    }
}