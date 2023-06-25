package com.example.unittest.data.cache.core

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.unittest.domain.cache.entities.CoinEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class CoinsDaoTest {


    @get: Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var database: AppDatabase
    private lateinit var coinsDao: CoinsDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        coinsDao = database.coinsDao()
    }


    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertCoin_returnsTrue() = runTest(UnconfinedTestDispatcher()) {
        val coins = listOf(CoinEntity("gsd", "gsdfg", "gsdgdf", "gsdfg", "gsdgds", "gedfas"))
        launch { coinsDao.insertCoins(coins)}
        assertEquals(coins, coinsDao.getCoinsList())
    }


    @Test
    fun emptyDb_returnsEmptyList() = runTest(UnconfinedTestDispatcher()) {
        assertEquals(emptyList<CoinEntity>(), coinsDao.getCoinsList())
    }


}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
