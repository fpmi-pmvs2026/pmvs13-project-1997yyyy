\# WeatherApp - Погодное приложение



\## Description

Приложение для просмотра погоды в различных городах мира с использованием API OpenWeatherMap. 

Поддерживает поиск городов, добавление в избранное, многоязычный интерфейс (русский и китайский языки). 

Реализовано хранение данных в SQLite базе данных и многопоточность с использованием Kotlin Coroutines.



\## Installation

1\. Клонируйте репозиторий: `git clone <your-repo-url>`

2\. Откройте проект в Android Studio

3\. Замените API\_KEY в файле WeatherApiService.kt на ваш ключ OpenWeatherMap

4\. Соберите и запустите приложение командой Build → Make Project



\## Usage

1\. Введите название города в поле поиска

2\. Нажмите "Найти погоду" для получения актуальной информации о погоде

3\. Нажмите "Добавить в избранное" для сохранения города в базу данных

4\. Переключайтесь между языками (русский/китайский) с помощью кнопки в правом верхнем углу

5\. Просматривайте список избранных городов во вкладке "Города"



\## Contributing

\*\*Автор:\*\* yaoxin



\*\*Реализованные функции:\*\*

\- Многооконный интерфейс с Fragment и BottomNavigationView

\- Интеграция с OpenWeatherMap API (Retrofit + Gson)

\- Локальное хранение данных с SQLite (CityDatabaseHelper)

\- Многоязычная поддержка (RU/ZH) с переключением интерфейса

\- Многопоточность с Kotlin Coroutines

\- Модульные тесты (JUnit)

\- UI тесты (Espresso)



\## Screenshots
<img width="1104" height="499" alt="image" src="https://github.com/user-attachments/assets/a7175679-2706-423c-b04c-a8171d5e4c44" />
<img width="1069" height="481" alt="image" src="https://github.com/user-attachments/assets/56d74f61-2665-4984-986b-924c18623495" />
<img width="460" height="1032" alt="image" src="https://github.com/user-attachments/assets/a2924e31-355f-49e2-9f18-39f5fd63b4e9" />
<img width="450" height="750" alt="image" src="https://github.com/user-attachments/assets/c4f4c395-4581-4881-911d-5bd90ba14436" />



# pmvs13-project-1997yyyy
