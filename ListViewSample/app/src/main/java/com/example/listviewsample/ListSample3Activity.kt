package com.example.listviewsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_list_sample_3.*

class ListSample3Activity : AppCompatActivity() {

    private lateinit var menuList: List<Map<String, Any>>
    companion object {
        private val FROM = arrayOf("name", "price")
        private val TO = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_sample_3)
        menuList = createTeishokuList()
        val adapter = SimpleAdapter(this, menuList, R.layout.row, FROM, TO)
        lvMenu.adapter = adapter
        lvMenu.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position) as Map<String, Any>
            val intent = Intent(this, MenuThanksActivity::class.java)
            val menuName = item["name"].toString()
            val menuPrice = item["price"].toString()
            intent.putExtra("menuName", menuName)
            intent.putExtra("menuPrice", menuPrice.toInt())
            intent.putExtra("menuPrice", menuPrice + "円")
            startActivity(intent)
        }
    }

    private fun createTeishokuList(): List<Map<String, Any>> {
        val menuList = mutableListOf<Map<String, Any>>()
        var menu = mutableMapOf<String, Any>()
        menu["name"] = "唐揚げ定食"
        menu["price"] = "800"
        menu["desc"] = "若鳥の唐揚げにサラダ、ご飯とお味噌汁が付きます。"
        menuList.add(menu)
        menu = mutableMapOf()
        menu["name"] = "ハンバーグ定食"
        menu["price"] = "850"
        menu["desc"] = "手捏ねハンバーグにサラダ、ご飯とお味噌汁が付きます。"
        menuList.add(menu)
        return menuList
    }

    private fun createCurryList(): List<Map<String, Any>> {
        val menuList = mutableListOf<Map<String, Any>>()
        var menu = mutableMapOf<String, Any>()
        menu["name"] = "ビーフカレー"
        menu["price"] = "520"
        menu["desc"] = "特選のスパイスをきかせた国産ビーフ100%のカレーです。"
        menuList.add(menu)
        menu = mutableMapOf()
        menu["name"] = "ポークカレー"
        menu["price"] = "420"
        menu["desc"] = "特選のスパイスをきかせた国産ポーク100%のカレーです。"
        menuList.add(menu)
        return menuList
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_optiona_menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId
        menuList = when (itemId) {
            R.id.menuListOptionTeishoku -> createTeishokuList()
            R.id.menuListOptionCurry -> createCurryList()
            else -> createTeishokuList()
        }
        val adapter = SimpleAdapter(this, menuList, R.layout.row, FROM, TO)
        lvMenu.adapter = adapter

        return super.onOptionsItemSelected(item)
    }
}
