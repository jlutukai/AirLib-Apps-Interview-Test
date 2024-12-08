package com.airlibs.data.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.airlibs.data.models.entities.UserDetailsEntity
import com.airlibs.data.sources.local.daos.auth.UserDao
import com.airlibs.data.sources.local.database.AirLibsDatabase
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named
import kotlin.test.Test

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class UserDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AirLibsDatabase
    private lateinit var userDao: UserDao


    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AirLibsDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = database.userDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun test_save_user_details() = runTest {
        val user = UserDetailsEntity(id = 1, email = "example@email.com")
        val personId = userDao.insert(user)
        assertEquals(1L, personId)
    }
}