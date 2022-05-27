package com.than.covidapp_challengeschapter7.data

import com.than.covidapp_challengeschapter7.data.model.GetAllCountryCases
import com.than.covidapp_challengeschapter7.data.model.GetAllData
import com.than.covidapp_challengeschapter7.data.room.DatabaseHelper
import com.than.covidapp_challengeschapter7.data.room.FavoriteEntity
import com.than.covidapp_challengeschapter7.data.room.UserEntity
import com.than.covidapp_challengeschapter7.data.service.ApiHelper
import com.than.covidapp_challengeschapter7.data.service.ApiService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class RepositoryTest {
    private val user = UserEntity(null, "Sulthan", "sulthan@gmail.com", "than", "123", "no_image")
    private val favorite = FavoriteEntity(null, 1, "USA", 9238592, "http", 25452, 24522345,234523)
    // Collabolator
    private lateinit var apiService: ApiService
    private lateinit var apiHelper: ApiHelper
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var dataStoreManager: DataStoreManager

    // System Under Test
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        apiService = mockk()
        databaseHelper = mockk()
        dataStoreManager = mockk()
        apiHelper = ApiHelper(apiService)
        repository = Repository(apiHelper, databaseHelper, dataStoreManager)
    }

    @Test
    fun getAllCountryCases() : Unit = runBlocking{
        // Given
        val responseGetAllCountryCases = mockk<List<GetAllCountryCases>>()
        every {
            runBlocking {
                apiService.getAllDataCovid()
            }
        } returns responseGetAllCountryCases

        // When
        repository.getAllCountryCases()

        // Try
        verify {
            runBlocking {
                apiService.getAllDataCovid()
            }
        }
    }

    @Test
    fun getAllDataCases() : Unit = runBlocking {
        // Given
        val responseGetAllDataCases = mockk<GetAllData>()
        every {
            runBlocking {
                apiService.getAllData()
            }
        } returns responseGetAllDataCases

        // When
        repository.getAllDataCases()

        // Try
        verify {
            runBlocking {
                apiService.getAllData()
            }
        }
    }

    @Test
    fun insertFavorite(): Unit = runBlocking {
        // Given
        val responseInsertFavorite = 1L

        every {
            runBlocking {
                databaseHelper.addFavorite(favorite)
            }
        } returns responseInsertFavorite

        // When
        repository.insertFavorite(favorite)

        // Try
        verify {
            runBlocking {
                databaseHelper.addFavorite(favorite)
            }
        }
    }

    @Test
    fun getFavorite(): Unit = runBlocking {
        // Given
        val responseGetFavorite = mockk<FavoriteEntity>()
        every {
            runBlocking {
                databaseHelper.getFavorite(1, "USA")
            }
        } returns responseGetFavorite

        // When
        repository.getFavorite(1, "USA")

        // Try
        verify {
            runBlocking {
                databaseHelper.getFavorite(1, "USA")
            }
        }
    }

    @Test
    fun getAllFavoriteById(): Unit = runBlocking {
        // Given
        val responseGetAllFavoriteById = mockk<List<FavoriteEntity>>()
        every {
            runBlocking {
                databaseHelper.getAllFavoriteById(1)
            }
        } returns responseGetAllFavoriteById

        // When
        repository.getAllFavoriteById(1)

        // Try
        verify {
            runBlocking {
                databaseHelper.getAllFavoriteById(1)
            }
        }
    }

    @Test
    fun deleteFavorite(): Unit = runBlocking {
        // Given
        val responseDeleteFavorite = 1
        every {
            runBlocking {
                databaseHelper.deleteFavorite(1, "USA")
            }
        } returns responseDeleteFavorite

        // When
        repository.deleteFavorite(1, "USA")

        // Try
        verify {
            runBlocking {
                databaseHelper.deleteFavorite(1, "USA")
            }
        }
    }

    @Test
    fun setUserPref(): Unit = runBlocking {
        // Given
        val responseSetUserPref = mockk<Unit>()
        every {
            runBlocking {
                dataStoreManager.setUser(user)
            }
        } returns responseSetUserPref

        // When
        repository.setUserPref(user)

        // Try
        verify {
            runBlocking {
                dataStoreManager.setUser(user)
            }
        }
    }

    @Test
    fun getUserPref(): Unit = runBlocking {
        // Given
        val responseGetUserPref = mockk<Flow<UserEntity>>()
        every {
            runBlocking {
                dataStoreManager.getUser()
            }
        } returns responseGetUserPref

        // When
        repository.getUserPref()

        // Try
        verify {
            runBlocking {
                dataStoreManager.getUser()
            }
        }
    }

    @Test
    fun deleteUserPref(): Unit = runBlocking {
        // Given
        val responseDeleteUserPref = mockk<Unit>()
        every {
            runBlocking {
                dataStoreManager.deleteUser()
            }
        } returns responseDeleteUserPref

        // When
        repository.deleteUserPref()

        // Try
        verify {
            runBlocking {
                dataStoreManager.deleteUser()
            }
        }
    }

    @Test
    fun insertUser(): Unit = runBlocking {
        // Given
        val responseInsertUser = mockk<Unit>()
        every {
            runBlocking {
                databaseHelper.insertUser(user)
            }
        } returns responseInsertUser

        // When
        repository.insertUser(user)

        // Try
        verify {
            runBlocking {
                databaseHelper.insertUser(user)
            }
        }
    }

    @Test
    fun loginUser(): Unit = runBlocking {
        // Given
        val responseLoginUser = mockk<UserEntity>()
        every {
            runBlocking {
                databaseHelper.loginUser("than", "123")
            }
        } returns responseLoginUser

        // When
        repository.loginUser("than", "123")

        // Try
        verify {
            runBlocking {
                databaseHelper.loginUser("than", "123")
            }
        }
    }

    @Test
    fun updateUser(): Unit = runBlocking {
        // Given
        val responseUpdateUser = mockk<Unit>()
        every {
            runBlocking {
                databaseHelper.updateUser(user)
            }
        } returns responseUpdateUser

        // When
        repository.updateUser(user)

        // Try
        verify {
            runBlocking {
                databaseHelper.updateUser(user)
            }
        }
    }
}