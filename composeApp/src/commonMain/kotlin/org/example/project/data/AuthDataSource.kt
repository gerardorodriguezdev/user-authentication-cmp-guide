package org.example.project.data

import kotlinx.coroutines.delay

interface AuthDataSource {
    suspend fun isUserLoggedIn(): Boolean
    suspend fun login(username: String, password: String): Boolean
    suspend fun logout(): Boolean
}

/**
 * For simplicity, this implementation simulates a remote server to handle the auth we will be needing
 */
class FakeAuthDataSource : AuthDataSource {
    private var isUserLoggedIn = false

    override suspend fun isUserLoggedIn(): Boolean {
        delay(1_000)
        return isUserLoggedIn
    }

    override suspend fun login(username: String, password: String): Boolean {
        delay(1_000)
        val isLoginValid = username == VALID_USERNAME && password == VALID_PASSWORD

        if (isLoginValid) {
            isUserLoggedIn = true
        }

        return isLoginValid
    }

    override suspend fun logout(): Boolean {
        delay(1_000)
        isUserLoggedIn = false
        return true
    }

    private companion object {
        const val VALID_USERNAME = "Username"
        const val VALID_PASSWORD = "Password"
    }
}