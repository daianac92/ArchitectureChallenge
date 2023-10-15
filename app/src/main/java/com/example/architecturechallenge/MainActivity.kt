package com.example.architecturechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.architecturechallenge.data.MoviesRepository
import com.example.architecturechallenge.data.local.LocalDataSource
import com.example.architecturechallenge.data.local.MoviesDataBase
import com.example.architecturechallenge.data.remote.RemoteDataSource
import com.example.architecturechallenge.ui.presentation.HomeScreen

class MainActivity : ComponentActivity() {

    // ver de hacer esto en application o por DI
    private lateinit var db: MoviesDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            db = Room.databaseBuilder(
                applicationContext,
                MoviesDataBase::class.java, "movies-database"
            ).build()

            val repository = MoviesRepository(
                localDataSource = LocalDataSource(db.moviesDao()),
                remoteDataSource = RemoteDataSource()
            )
            HomeScreen(repository)
        }
    }
}
