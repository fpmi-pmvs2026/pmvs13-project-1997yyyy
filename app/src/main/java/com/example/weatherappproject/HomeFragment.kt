package com.example.weatherappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var dbHelper: CityDatabaseHelper
    private var currentCity: String = "" // 保存当前搜索的城市名

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化数据库
        dbHelper = CityDatabaseHelper(requireContext())

        val etCity = view.findViewById<EditText>(R.id.etCitySearch)
        val btnSearch = view.findViewById<Button>(R.id.btnSearch)
        val btnAddFavorite = view.findViewById<Button>(R.id.btnAddToFavorites)
        val tvResult = view.findViewById<TextView>(R.id.tvWeatherResult)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        // 搜索按钮点击事件
        btnSearch.setOnClickListener {
            val city = etCity.text.toString().trim()
            if (city.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.error_city), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            currentCity = city // 保存当前城市名
            progressBar.visibility = View.VISIBLE
            btnAddFavorite.visibility = View.GONE // 先隐藏收藏按钮

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitClient.apiService.getCurrentWeather(city)
                    }

                    tvResult.text = getString(
                        R.string.result_format,
                        response.name,
                        response.main.temp,
                        response.weather[0].description
                    )

                    // 🌟 显示收藏按钮
                    btnAddFavorite.visibility = View.VISIBLE

                } catch (e: Exception) {
                    tvResult.text = getString(R.string.error_network)
                    btnAddFavorite.visibility = View.GONE
                } finally {
                    progressBar.visibility = View.GONE
                }
            }
        }

        // 🌟 收藏按钮点击事件
        btnAddFavorite.setOnClickListener {
            if (currentCity.isNotEmpty()) {
                // 在后台线程执行数据库操作
                CoroutineScope(Dispatchers.IO).launch {
                    val success = dbHelper.addCity(currentCity)

                    withContext(Dispatchers.Main) {
                        if (success) {
                            Toast.makeText(requireContext(), getString(R.string.msg_added_favorite), Toast.LENGTH_SHORT).show()
                            btnAddFavorite.visibility = View.GONE // 收藏成功后隐藏按钮
                        } else {
                            Toast.makeText(requireContext(), getString(R.string.msg_already_exists), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
}