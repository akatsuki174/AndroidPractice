package com.example.listviewsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_sample_2.*

class ListSample2Activity : AppCompatActivity() {

    private val clickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
        val dialogFragment = OrderConfirmDialogFragment()
        dialogFragment.show(supportFragmentManager, "OrderConfirmDialogFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_sample_2)
        lvMenu.onItemClickListener =clickListener

        val menuList = arrayListOf<String>()
        menuList.add("唐揚げ定食")
        menuList.add("ハンバーグ定食")
        menuList.add("生姜焼き定食")
        menuList.add("ステーキ定食")
        menuList.add("野菜炒め定食")
        menuList.add("とんかつ定食")
        menuList.add("ミンチかつ定食")
        menuList.add("チキンカツ定食")
        menuList.add("コロッケ定食")
        menuList.add("焼き魚定食")
        menuList.add("焼肉定食")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuList)
        lvMenu.adapter = adapter
    }
}
