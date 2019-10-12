package com.example.listviewsample.FragmentSample


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import com.example.listviewsample.R

/**
 * A simple [Fragment] subclass.
 */
class MenuListFragment : Fragment() {

    private var isLayoutXLarge = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        val menuList: List<Map<String, Any>>? = createTeishokuList()

        val from = arrayOf("name", "price")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)

        val adapter = SimpleAdapter(activity, menuList, android.R.layout.simple_list_item_2, from, to)
        val listMenu = view.findViewById<ListView>(R.id.listMenu)
        listMenu.adapter = adapter
        listMenu.setOnItemClickListener { parent, _, position, _ ->
            @Suppress("UNCHECKED_CAST")
            val item = parent.getItemAtPosition(position) as Map<String, Any>
            order(item)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val orderCompletedFrame = activity?.findViewById<View>(R.id.orderCompleteFrame)
        if (orderCompletedFrame == null) {
            isLayoutXLarge = false
        }
    }

    private fun createTeishokuList(): List<Map<String, Any>> {
        val menuList = mutableListOf<Map<String, Any>>()
        var menu = mutableMapOf<String, Any>()
        menu["name"] = "唐揚げ定食"
        menu["price"] = "800"
        menuList.add(menu)
        menu = mutableMapOf()
        menu["name"] = "ハンバーグ定食"
        menu["price"] = "850"
        menuList.add(menu)
        return menuList
    }

    private fun order(menu: Map<String, Any>) {
        val menuName = menu["name"].toString()
        val menuPrice = menu["price"].toString()

        val bundle = Bundle()
        bundle.putString("menuName", menuName)
        bundle.putString("menuPrice", menuPrice)

        if (isLayoutXLarge) {
            val manager = fragmentManager
            val transaction = manager?.beginTransaction()
            val orderCompletedFragment = OrderCompletedFragment()
            orderCompletedFragment.arguments = bundle
            transaction?.replace(R.id.orderCompleteFrame, orderCompletedFragment)
            transaction?.commit()
        } else {
            val intent = Intent(activity, OrderCompletedActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

}
