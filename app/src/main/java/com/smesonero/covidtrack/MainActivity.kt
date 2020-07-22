package com.smesonero.covidtrack

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smesonero.covidtrack.viewmodel.CovidViewModel

class MainActivity : AppCompatActivity() {


   lateinit var bottomNavigationView : BottomNavigationView

    lateinit var viewModel : CovidViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main)

        bottomNavigationView = findViewById(R.id.bttm_nav)
        var navHostFragment :NavHostFragment = this.getSupportFragmentManager().findFragmentById(R.id.nav_base_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)

        viewModel = ViewModelProviders.of(this).get(CovidViewModel::class.java)

    }

//    private fun loadInfo() {
//
//        viewModel.covidLIvedata.observe(this, Observer {
//
//            Log.e("MAINACTIVITY" , "observer, cambio, con sucessfull: "+it.isSuccessful + " "+it)
//        })
//    }
}