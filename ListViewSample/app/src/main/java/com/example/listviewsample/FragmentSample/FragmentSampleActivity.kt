package com.example.listviewsample.FragmentSample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.listviewsample.R

class FragmentSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_sample)

        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                MenuListFragment()
            )
            fragmentTransaction.commit()
        }

    }
}
