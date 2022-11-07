package com.sasoftbd.machine_learing_ml_kit.zone_Panel

import ViewPagerAdapter
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

import com.google.android.material.tabs.TabLayout
import com.sasoftbd.machine_learing_ml_kit.R
import com.sasoftbd.machine_learing_ml_kit.zone_Panel.ScannerActivity


class ZoneActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zone)
//
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        toolbar.visibility = View.GONE


        title = "Whatsapp"


        //supportActionBar?.hide()//for hide toolbar

        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tablayout)
        tabLayout.setupWithViewPager(viewPager)


        val currentNightMode = Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                setTheme(R.style.AppThemeDark)
                reset()
            } // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> {
                setTheme(R.style.AppTheme)
                reset()
            } // Night mode is active, we're using dark theme
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_zone, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> Toast.makeText(this, "Setting Clicked", Toast.LENGTH_SHORT).show()
            R.id.about -> Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show()
            R.id.info_zone -> Toast.makeText(this, "Info Clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun whenClickScanner(view: View) {
        val intent = Intent(this, ScannerActivity::class.java)
        startActivity(intent)
    }


    //write for theme reset
    fun reset(){
        val intheme = Intent(this, ZoneActivity::class.java)
        startActivity(intheme)

    }




}