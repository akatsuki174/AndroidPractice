package com.example.listviewsample.FragmentSample


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.example.listviewsample.R

/**
 * A simple [Fragment] subclass.
 */
class OrderCompletedFragment : Fragment() {

    private var isLayoutXLarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val manager = fragmentManager
        val menuListFragment = manager?.findFragmentById(R.id.fragmentMenuList)
        if (menuListFragment == null) {
            isLayoutXLarge = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var extras: Bundle? = Bundle()
        if (isLayoutXLarge) {
            extras = arguments
        } else {
            extras = activity?.intent?.extras
        }
        val view = inflater.inflate(R.layout.fragment_order_completed, container, false)
        val intent = activity?.intent

        var menuName = ""
        var menuPrice = ""
        if (extras != null) {
            menuName = extras.getString("menuName") ?: ""
            menuPrice = extras.getString("menuPrice") ?: ""
        }
        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice

        val btBackButton = view.findViewById<Button>(R.id.btBackButton)
        btBackButton.setOnClickListener {
            if (isLayoutXLarge) {
                val manager = fragmentManager
                val transaction = manager?.beginTransaction()
                transaction?.remove(this)
                transaction?.commit()
            } else {
                activity?.finish()
            }
        }
        return view
    }

}
