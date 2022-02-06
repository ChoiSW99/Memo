package com.example.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3


class MainActivity : AppCompatActivity() {
    private var mPager: ViewPager2? = null
    private var pagerAdapter: FragmentStateAdapter? = null
    private val num_page = 4
    private var mIndicator: CircleIndicator3? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ViewPager2
        mPager = findViewById(R.id.viewPager2)

        //ViewPager2 Adapter
        pagerAdapter = MyAdapter(this, num_page)
        mPager?.adapter = pagerAdapter
        //Indicator
        mIndicator = findViewById(R.id.indicator)
        mIndicator?.setViewPager(mPager)
        mIndicator?.createIndicators(num_page, 0)
        //ViewPager Setting
        mPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mPager?.currentItem = 0
        mPager?.offscreenPageLimit = 3
        mPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (positionOffsetPixels == 0) {
                    mPager?.currentItem = position
                }
            }

            override fun onPageSelected(position: Int) {
                Log.d("페이지 =>>> ","{$position}")
                super.onPageSelected(position)
                mIndicator?.animatePageSelected(position % num_page)
            }

        })
        val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
        val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()
        mPager?.setPageTransformer(ViewPager2.PageTransformer { page, position ->
            val myOffset = position * -(2 * pageOffset + pageMargin)
            if (mPager?.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(mPager!!) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -myOffset
                } else {
                    page.translationX = myOffset
                }
            }
        })
    }

    //다시 메인액티비티로 돌아옴
    override fun onResume(){
        super.onResume()

        //ViewPager2 Adapter
        mPager?.adapter = pagerAdapter
        //Indicator
        mIndicator = findViewById(R.id.indicator)
        mIndicator?.setViewPager(mPager)
        mIndicator?.createIndicators(num_page, 0)
        //ViewPager Setting
        mPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mPager?.currentItem = 0
        mPager?.offscreenPageLimit = 3
        mPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (positionOffsetPixels == 0) {
                    mPager?.currentItem = position
                }
            }

            override fun onPageSelected(position: Int) {
                Log.d("페이지 =>>> ","{$position}")
                super.onPageSelected(position)
                mIndicator?.animatePageSelected(position % num_page)
            }

        })
        val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
        val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()
        mPager?.setPageTransformer(ViewPager2.PageTransformer { page, position ->
            val myOffset = position * -(2 * pageOffset + pageMargin)
            if (mPager?.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(mPager!!) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -myOffset
                } else {
                    page.translationX = myOffset
                }
            }
        })
    }


    /*메뉴바*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }
    /*메뉴바에 추가버튼*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.addBtn -> { addBtn() } /*'추가' 버튼 클릭 시 addActivity 로 전환*/
            else ->{ super.onOptionsItemSelected(item) }
        }
    }

    private fun addBtn() : Boolean{
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)

        return true
    }

}