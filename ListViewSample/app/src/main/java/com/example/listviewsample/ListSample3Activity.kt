package com.example.listviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ListSample3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_sample3)
    }

    private fun createTeishokuList(): List<Map<String, Any>> {
        val menuList = mutableListOf<Map<String, Any>>()
        var menu = mutableMapOf<String, Any>()
        menu.put("name", "唐揚げ定食")
        menu.put("price", "800")
        menu.put("desc", "若鳥の唐揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf()
        menu.put("name", "ハンバーグ定食")
        menu.put("price", "850")
        menu.put("desc", "手捏ねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        return menuList
    }
}
