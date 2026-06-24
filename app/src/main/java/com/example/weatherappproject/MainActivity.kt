package com.example.weatherappproject // ⚠️ 确保包名一致

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🌟 1. 在加载 UI 前，先应用保存的语言
        applySavedLanguage()

        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val btnSwitchLang = findViewById<Button>(R.id.btnSwitchLang)

        // 默认加载首页
        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> fragment = HomeFragment()
                R.id.nav_cities -> fragment = CitiesFragment()
            }
            if (fragment != null) {
                loadFragment(fragment)
            }
            true
        }

        // 🌟 2. 设置语言切换按钮的点击事件
        btnSwitchLang.setOnClickListener {
            val sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE)
            val currentLang = sharedPref.getString("lang", "ru") ?: "ru"

            // 切换语言：如果是俄语就切中文，如果是中文就切俄语
            val newLang = if (currentLang == "ru") "zh" else "ru"

            // 保存新语言
            sharedPref.edit().putString("lang", newLang).apply()

            // 重建 Activity 以刷新所有 UI 和 Fragment
            recreate()
        }
    }

    // 🌟 辅助方法：读取并应用保存的语言
    private fun applySavedLanguage() {
        val sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val langCode = sharedPref.getString("lang", "ru") ?: "ru"

        // 获取当前系统的语言，如果和保存的不一致，才需要切换
        val currentConfigLang = resources.configuration.locales[0].language
        if (currentConfigLang != langCode) {
            setLocale(langCode)
        }
    }

    // 🌟 辅助方法：修改系统 Locale
    private fun setLocale(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        // 更新资源 (兼容旧版本 API)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}