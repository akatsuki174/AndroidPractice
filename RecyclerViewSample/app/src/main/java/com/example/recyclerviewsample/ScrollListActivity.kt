package com.example.recyclerviewsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import java.util.ArrayList
import java.util.HashMap

class ScrollListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)
        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        toolbarLayout.title = getString(R.string.toolbar_title)
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)

        val lvMenu = findViewById<RecyclerView>(R.id.lvMenu)
        val layout = LinearLayoutManager(this)
        lvMenu.layoutManager = layout
        val menuList = createTeishokuList()
        val adapter = RecyclerListAdapter(menuList)
        lvMenu.adapter = adapter
    }

    private fun createTeishokuList(): List<Map<String, Any>> {
        val menuList = ArrayList<Map<String, Any>>()
        menuList.add(createTeishoku("から揚げ定食", 800, "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("ハンバーグ定食", 850, "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("生姜焼き定食", 850, "すりおろし生姜を使った生姜焼きにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("ステーキ定食", 1000, "国産牛のステーキにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("野菜炒め定食", 750, "季節の野菜炒めにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("とんかつ定食", 900, "ロースとんかつにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("ミンチかつ定食", 850, "手ごねミンチカツにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("チキンカツ定食", 900, "ボリュームたっぷりチキンカツにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("コロッケ定食", 850, "北海道ポテトコロッケにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("焼き魚定食", 850, "鰆の塩焼きにサラダ、ご飯とお味噌汁が付きます。"))
        menuList.add(createTeishoku("焼肉定食", 950, "特性たれの焼肉にサラダ、ご飯とお味噌汁が付きます。"))
        return menuList
    }

    private fun createTeishoku(name: String, price: Int, desc: String): Map<String, Any> {
        val menu: MutableMap<String, Any> = HashMap()
        menu["name"] = name
        menu["price"] = price
        menu["desc"] = desc
        return menu
    }

    private inner class RecyclerListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvMenuName: TextView = itemView.findViewById(R.id.tvMenuName)
        val tvMenuPrice: TextView = itemView.findViewById(R.id.tvMenuPrice)
    }

    private inner class RecyclerListAdapter(private val listData: List<Map<String, Any>>) : RecyclerView.Adapter<RecyclerListViewHolder>() {
        override fun getItemCount(): Int {
            return listData.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            val inflater = LayoutInflater.from(this@ScrollListActivity)
            val view = inflater.inflate(R.layout.row, parent, false)
            return RecyclerListViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            val item = listData[position]
            val menuName = item["name"] as String?
            val menuPrice = item["price"] as Int?
            val menuPriceStr = menuPrice.toString()
            holder.tvMenuName.text = menuName
            holder.tvMenuPrice.text = menuPriceStr
        }
    }
}
