package com.example.intentsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuList = arrayListOf<Map<String, String>>()
        menuList.add(createMenu("唐揚げ定食", "850円"))
        menuList.add(createMenu("焼き魚定食", "800円"))
        menuList.add(createMenu("日替わり定食", "800円"))
        menuList.add(createMenu("とんかつ定食", "850円"))
        menuList.add(createMenu("ざるそば定食", "750円"))
        menuList.add(createMenu("天ぷら定食", "850円"))
        menuList.add(createMenu("親子丼定食", "850円"))
        menuList.add(createMenu("牛丼定食", "1000円"))
        menuList.add(createMenu("かき揚げ定食", "800円"))
        menuList.add(createMenu("豚丼定食", "850円"))
        menuList.add(createMenu("刺し身定食", "1000円"))
        menuList.add(createMenu("餃子定食", "800円"))

        val from: Array<String> = arrayOf("name", "price")
        val to: IntArray = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter = SimpleAdapter(this, menuList, android.R.layout.simple_list_item_2, from, to)
        lvMenu.adapter = adapter

        lvMenu.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Map<String, String>
            val intent = Intent(this, MenuThanksActivity::class.java)
            intent.putExtra("menuName", item["name"])
            intent.putExtra("menuPrice", item["price"])
            startActivity(intent)
        }
    }

    private fun createMenu(name: String, price: String): Map<String, String> {
        val map = hashMapOf<String, String>()
        map.put("name", name)
        map.put("price", price)
        return map
    }
}
