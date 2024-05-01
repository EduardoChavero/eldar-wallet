package com.example.eldarwalletchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflate = navHostFragment.navController.navInflater
        val navGraphInflate = inflate.inflate(R.navigation.app_navigation)

        navHostFragment.navController.setGraph(navGraphInflate, intent.extras)
        val navController = navHostFragment.navController
    }



}