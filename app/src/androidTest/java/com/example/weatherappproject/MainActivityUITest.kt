package com.example.weatherappproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testAppLaunches_Successfully() {
        // 测试应用成功启动，输入框可见
        onView(withId(R.id.etCitySearch))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSearchButton_Exists() {
        // 测试搜索按钮存在
        onView(withId(R.id.btnSearch))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testBottomNavigation_Exists() {
        // 测试底部导航存在
        onView(withId(R.id.bottom_navigation))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLanguageSwitchButton_Exists() {
        // 测试语言切换按钮存在
        onView(withId(R.id.btnSwitchLang))
            .check(matches(isDisplayed()))
    }
}