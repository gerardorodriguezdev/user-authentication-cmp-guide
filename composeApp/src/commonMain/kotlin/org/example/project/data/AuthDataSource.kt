package org.example.project.data

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
    override suspend fun isUserLoggedIn(): Boolean = isUserLoggedInResult
    override suspend fun login(username: String, password: String): Boolean = loginResult
    override suspend fun logout(): Boolean = logoutResult
}