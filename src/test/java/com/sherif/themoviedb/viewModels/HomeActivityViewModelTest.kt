package com.sherif.themoviedb.viewModels

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.JUnitCore
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeActivityViewModelTest {

    val viewModel:HomeActivityViewModel = HomeActivityViewModel()

    @Before
    fun setUp() {

       // val viewModel = HomeActivityViewModel()

    }

    @After
    fun tearDown() {
    }

    @Test
    fun callTopRatedMoviesApi() {

       //assert( viewModel.callTopRatedMoviesApi().test().hasSubscription())
    }
}