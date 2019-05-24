package com.example.lifecyclesample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class LifeCycleSubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_sub)
    }
}
