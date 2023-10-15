package com.example.architecturechallenge.data

import com.example.architecturechallenge.data.local.LocalDataSource
import com.example.architecturechallenge.data.remote.RemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.whenever

class MoviesRepositoryTest {

    private val localDataSource: LocalDataSource = mock()

    private val remoteDataSource = mock<RemoteDataSource>()

    lateinit var repository: MoviesRepository

    @Before
    fun setup() {
        repository = MoviesRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `When DB is empty, server is called`() {
        runBlocking {
            whenever(localDataSource.count()).thenReturn(0)
            repository.requestMovies()
            verify(remoteDataSource).getMovies()
        }
    }

    @Test
    fun `When DB is empty, movies is saved into DB`() {
        runBlocking {
            whenever(localDataSource.count()).thenReturn(0)
            whenever(remoteDataSource.getMovies()).thenReturn(listOf(MovieModel(1, "", "", "")))

            repository.requestMovies()
            verify(localDataSource).insertAll(any())
        }
    }

    @Test
    fun `When DB is not empty, server is not called`() {
        runBlocking {
            whenever(localDataSource.count()).thenReturn(5)

            repository.requestMovies()

            verify(remoteDataSource, never()).getMovies()
        }
    }


}



