package com.tungtt.vtm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tungtt.vtm.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    companion object {
        val BUNDLE_USER_MODEL = "bundle_user_model"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(intent.extras))
                .commitNow()
        }
    }
}