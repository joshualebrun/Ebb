package com.neubofy.reality.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.neubofy.reality.R
import com.neubofy.reality.utils.ThemeManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        ThemeManager.applyAccentTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_configure_blocker).setOnClickListener {
            startActivity(Intent(this, BlockActivity::class.java))
        }

        findViewById<Button>(R.id.btn_strict_mode).setOnClickListener {
            // Note: anti_uninstall is a fragment inside FragmentActivity or setup via StrictModeActivity
            startActivity(Intent(this, StrictModeActivity::class.java))
        }
    }
}
