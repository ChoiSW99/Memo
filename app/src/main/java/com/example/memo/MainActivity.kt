package com.example.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.addBtn -> {
                addBtn()
            } /*'추가' 버튼 클릭 시 액티비티 전환*/
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun addBtn() : Boolean{
        val intent = Intent(this, AddActivity::class.java) /*addBtn 클릭시 addActivity로 전환*/

        startActivity(intent)
        return true
    }

}