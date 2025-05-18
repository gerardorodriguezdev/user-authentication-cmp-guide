package org.example.project.data

import kotlinx.coroutines.delay

interface AuthDataSource {
    suspend fun isUserLoggedIn(): Boolean
    suspend fun login(username: String, password: String): Boolean
    suspend fun logout(): Boolean
}

class FakeAuthDataSource(
    var isUserLoggedInResult: Boolean = false,
    var loginResult: Boolean = false,
    var logoutResult: Boolean = false,
) : AuthDataSource {
    override suspend fun isUserLoggedIn(): Boolean {
        delay(1_000)
        return isUserLoggedInResult
    }

    override suspend fun login(username: String, password: String): Boolean {
        delay(1_000)
        return loginResult
    }

    override suspend fun logout(): Boolean {
        delay(1_000)
        return logoutResult
    }
}