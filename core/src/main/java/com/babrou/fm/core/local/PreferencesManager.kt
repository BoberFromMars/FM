package com.babrou.fm.core.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

// Extension to create DataStore instance
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user_preferences"
)

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val appContext: Context,
) : IPreferencesManager {

    private val dataStore = appContext.dataStore
    
    // Scope for caching flows as StateFlows
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    
    // Cached StateFlows for synchronous access
    private val cachedAccessToken: StateFlow<String?> = dataStore.data
        .map { preferences -> preferences[Keys.ACCESS_TOKEN] }
        .stateIn(scope, SharingStarted.Eagerly, null)
    
    private val cachedRefreshToken: StateFlow<String?> = dataStore.data
        .map { preferences -> preferences[Keys.REFRESH_TOKEN] }
        .stateIn(scope, SharingStarted.Eagerly, null)
    
    private val cachedTokenType: StateFlow<String?> = dataStore.data
        .map { preferences -> preferences[Keys.TOKEN_TYPE] }
        .stateIn(scope, SharingStarted.Eagerly, null)

    private val cachedUserId: StateFlow<Int?> = dataStore.data.map { preferences -> preferences[Keys.USER_ID] }
        .stateIn(scope, SharingStarted.Eagerly, null)

    private val cachedAccountId: StateFlow<Int?> = dataStore.data.map { preferences -> preferences[Keys.ACCOUNT_ID] }
        .stateIn(scope, SharingStarted.Eagerly, null)
    
    private val cachedUUID: StateFlow<String?> = dataStore.data
        .map { preferences -> preferences[Keys.UUID] }
        .stateIn(scope, SharingStarted.Eagerly, null)

    // Synchronous getters (use cached StateFlow values)
    override fun getAccessToken(): String? = cachedAccessToken.value
    
    override fun getRefreshToken(): String? = cachedRefreshToken.value
    
    override fun getTokenType(): String? = cachedTokenType.value

    override fun getUserId(): Int? = cachedUserId.value

    override fun getAccountId(): Int? = cachedAccountId.value

    override fun getUUID(): String? = cachedUUID.value
    
    override fun hasUser(): Boolean = cachedUserId.value != null

    // Async setters (DataStore operations)
    override suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[Keys.ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[Keys.REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun setTokenType(tokenType: String) {
        dataStore.edit { preferences ->
            preferences[Keys.TOKEN_TYPE] = tokenType
        }
    }

    override suspend fun setUserId(userId: Int) {
        dataStore.edit { preferences -> preferences[Keys.USER_ID] = userId }
    }

    override suspend fun setAccountId(accountId: Int) {
        dataStore.edit { preferences -> preferences[Keys.ACCOUNT_ID] = accountId }
    }

    override suspend fun setUUID(uuid: String) {
        dataStore.edit { preferences ->
            preferences[Keys.UUID] = uuid
        }
    }

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    // Flow-based reactive access
    override val accessTokenFlow: Flow<String?>
        get() = dataStore.data.map { it[Keys.ACCESS_TOKEN] }
    
    override val refreshTokenFlow: Flow<String?>
        get() = dataStore.data.map { it[Keys.REFRESH_TOKEN] }
    
    override val tokenTypeFlow: Flow<String?>
        get() = dataStore.data.map { it[Keys.TOKEN_TYPE] }

    override val userIdFlow: Flow<Int?>
        get() = dataStore.data.map { it[Keys.USER_ID] }

    override val accountIdFlow: Flow<Int?>
        get() = dataStore.data.map { it[Keys.ACCOUNT_ID] }

    override val uuidFlow: Flow<String?>
        get() = dataStore.data.map { it[Keys.UUID] }

    private object Keys {
        val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
        val REFRESH_TOKEN = stringPreferencesKey("key_refresh_token")
        val TOKEN_TYPE = stringPreferencesKey("key_token_type")
        val USER_ID = intPreferencesKey("key_user_id")
        val ACCOUNT_ID = intPreferencesKey("key_account_id")
        val UUID = stringPreferencesKey("key_uuid")
    }
}