package com.newstore.favqs.features.account

import com.newstore.favqs.data.ResourceFlow
import com.newstore.favqs.features.account.login.model.LoginModel
import com.newstore.favqs.features.account.login.network.LoginRequest
import com.newstore.favqs.features.account.login.network.LoginUserItem
import com.newstore.favqs.features.account.logout.model.LogoutModel
import com.newstore.favqs.features.account.signup.model.SignupModel
import com.newstore.favqs.features.account.signup.network.SignupRequest
import com.newstore.favqs.features.account.signup.network.SignupUserItem
import com.newstore.network.operation.networkFlow
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val service: AccountService
) {

    fun loginUser(username: String, password: String): ResourceFlow<LoginModel> {
        val request = LoginRequest(LoginUserItem(username, password))
        return networkFlow { service.loginUser(request) }
    }

    fun logoutCurrentUser(): ResourceFlow<LogoutModel> {
        return networkFlow { service.logoutUser() }
    }

    fun signupUser(username: String, email: String, password: String): ResourceFlow<SignupModel> {
        val requestItem = SignupUserItem(username, email, password)
        val request = SignupRequest(requestItem)
        return networkFlow { service.signupUser(request) }
    }

}