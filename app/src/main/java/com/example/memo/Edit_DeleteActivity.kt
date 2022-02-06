package com.example.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlin.properties.Delegates

class Edit_DeleteActivity : AppCompatActivity() {
    var memoId by Delegates.notNull<Long>()
    lateinit var memoImportance : String
    lateinit var memoTitle : String
    private lateinit var memoContents : String
    private var memoPosition by Delegates.notNull<Int>()

    var db: AppDatabase? = null
    var memosList = ArrayList<DataMemo>() //메모들이 저장될 곳

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //룸DB 초기화
        db = AppDatabase.getInstance(this)

        //이전에 저장한 내용 모두 불러와서 추가하기
        val savedMemos = db!!.dataDao().getAll()
        if(savedMemos.isNotEmpty()){
            memosList.addAll(savedMemos)
        }

        //스피너
        val spinner: Spinner = findViewById(R.id.editSpinner)

        spinner.adapter = ArrayAdapter.createFromResource(this, R.array.spinnerArray, android.R.layout.simple_spinner_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        memoId = intent.getLongExtra("memoId", -1L)
        memoImportance = intent.getStringExtra("memoImportance").toString()
        memoTitle = intent.getStringExtra("memoTitle").toString()
        memoContents = intent.getStringExtra("memoContents").toString()
        memoPosition = intent.getIntExtra("memoPosition", -1)


        Log.d("중요도 : ",memoImportance)
        //전달된 값들로 세팅해줌
        when(memoImportance){
            "일상" -> findViewById<Spinner>(R.id.editSpinner).setSelection(0)
            "중요" -> findViewById<Spinner>(R.id.editSpinner).setSelection(1)
            "기타" -> findViewById<Spinner>(R.id.editSpinner).setSelection(2)
        }
        findViewById<TextView>(R.id.editTitle).text=memoTitle
        findViewById<TextView>(R.id.editContents).text=memoContents
    }

    /*메뉴바*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_delete, menu)
        return true
    }
    /*메뉴바에 수정버튼*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {

            R.id.editBtn -> { editBtn() } /*'수정' 버튼 클릭 시 액티비티 전환*/
            R.id.deleteBtn -> { deleteBtn() } /*'수정' 버튼 클릭 시 액티비티 전환*/
            else ->{ super.onOptionsItemSelected(item) }
        }
    }
    /*수정버튼 클릭시 수정(저장)*/
    private fun editBtn() : Boolean{
        val newMemoTitle = findViewById<EditText>(R.id.editTitle).text.toString()
        val newMemoContents = findViewById<EditText>(R.id.editContents).text.toString()
        val memoImportance = findViewById<Spinner>(R.id.editSpinner).selectedItem.toString()
        /*이 곳에 제목 or 내용을 수정할 수 있게 db에 반영 해야함.*/
        memosList[memoPosition] = (DataMemo(0, memoImportance, newMemoTitle, newMemoContents))
        Log.d("editBtn", "${memoImportance} , ${newMemoTitle}, ${newMemoContents}")
        db!!.dataDao().updateUserById(memoId,memoImportance,newMemoTitle,newMemoContents)

        finish()
        return true
    }

    /*삭제버튼 클릭시 삭제*/
    private fun deleteBtn() : Boolean{
        val newMemoTitle = findViewById<EditText>(R.id.editTitle).text.toString()
        val newMemoContents = findViewById<EditText>(R.id.editContents).text.toString()
        val memoImportance = findViewById<Spinner>(R.id.editSpinner).selectedItem.toString()
        /*이 곳에 제목 or 내용을 삭제할 수 있게 db에 반영 해야함.*/
        memosList[memoPosition] = (DataMemo(0, memoImportance, newMemoTitle, newMemoContents))
        Log.d("삭제버튼!! ", "${memoImportance} , ${newMemoTitle}, ${newMemoContents}")
        db!!.dataDao().deleteUserById(memoId)

        finish()
        return true
    }
}