package com.example.weatherappproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CitiesFragment : Fragment() {
    private lateinit var dbHelper: CityDatabaseHelper
    private lateinit var cityList: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = CityDatabaseHelper(requireContext())

        val listView = view.findViewById<ListView>(R.id.lvCities)
        cityList = dbHelper.getAllCities()

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            cityList
        )
        listView.adapter = adapter

        // 🌟 点击城市后查询天气
        listView.setOnItemClickListener { parent, view, position, id ->
            val cityName = cityList[position]
            fetchWeatherForCity(cityName)
        }
    }

    private fun fetchWeatherForCity(cityName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getCurrentWeather(cityName)
                }

                val weatherText = getString(
                    R.string.result_format,
                    response.name,
                    response.main.temp,
                    response.weather[0].description
                )

                Toast.makeText(requireContext(), weatherText, Toast.LENGTH_LONG).show()

                // 或者切换到主页显示
                // switchToHomeFragmentWithCity(cityName)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), getString(R.string.error_network), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
}