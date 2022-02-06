package com.example.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner

class AddActivity : AppCompatActivity() {

    var db: AppDatabase? = null
    var memosList = ArrayList<DataMemo>() //메모들이 저장될 곳

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //스피너
        val spinner: Spinner = findViewById(R.id.addSpinner)

        spinner.adapter = ArrayAdapter.createFromResource(this, R.array.spinnerArray, android.R.layout.simple_spinner_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        //초기화
        db = AppDatabase.getInstance(this)

        //이전에 저장한 내용 모두 불러와서 추가하기
        val savedMemos = db!!.dataDao().getAll()
        if(savedMemos.isNotEmpty()){
            memosList.addAll(savedMemos)
        }
    }


    /*메뉴바*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    /*메뉴바에 저장버튼*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.saveBtn -> { saveBtn() } /* 저장 버튼 클릭 시 */
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    /* 저장버튼 클릭시 */
    private fun saveBtn(): Boolean {
        Log.d("saveBtn", "클릭")

        /*이 곳에 제목 or 내용을 수정할 수 있게 db에 반영 해야함.*/
        val newMemoTitle = findViewById<EditText>(R.id.addTitle).text.toString()
        val newMemoContents = findViewById<EditText>(R.id.addContents).text.toString()
        val memoImportance = findViewById<Spinner>(R.id.addSpinner).selectedItem.toString()

        //메모 추가 시 DB에 저장되어야함.
        Log.d("스피너 ",memoImportance)
        /*memosList.add(DataMemo(0, memoImportance, newMemoTitle, newMemoContents))*/
        Log.d("saveBtn", "새 메모 저장")
        db!!.dataDao().insert(DataMemo(0,memoImportance, newMemoTitle, newMemoContents))


        finish()
        return true
    }
}