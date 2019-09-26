package com.example.listviewsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_sample_3.*

class ListSample3Activity : AppCompatActivity() {

    private var menuList: List<Map<String, Any>>? = null
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
            @Suppress("UNCHECKED_CAST")
            val item = parent.getItemAtPosition(position) as Map<String, Any>
            order(item)
        }
        registerForContextMenu(lvMenu)
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

    private fun order(menu: Map<String, Any>) {
        val menuName = menu["name"].toString()
        val menuPrice = menu["price"].toString()
        val intent = Intent(this, MenuThanksActivity::class.java)
        intent.putExtra("menuName", menuName)
        intent.putExtra("menuPrice", menuPrice + "円")
        startActivity(intent)
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

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val info = item?.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val menu = menuList?.get(listPosition)
        val itemId = item?.itemId
        when (itemId) {
            R.id.menuListContextDesc -> {
                val desc = menu?.get("desc") as String
                Toast.makeText(this, desc, Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder -> {
                menu?.let { order(it) }
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        menu?.setHeaderTitle(R.string.menuListContextHeader)
    }
}
