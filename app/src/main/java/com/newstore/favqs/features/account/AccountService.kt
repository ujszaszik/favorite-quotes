package com.newstore.favqs.features.account

import com.newstore.favqs.data.TOKEN_HEADER
import com.newstore.favqs.features.account.login.model.LoginModel
import com.newstore.favqs.features.account.login.network.LoginRequest
import com.newstore.favqs.features.account.logout.model.LogoutModel
import com.newstore.favqs.features.account.signup.model.SignupModel
import com.newstore.favqs.features.account.signup.network.SignupRequest
import com.newstore.network.call.Resource
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccountService {

    @Headers(TOKEN_HEADER)
    @POST("session")
    suspend fun loginUser(@Body request: LoginRequest): Resource<LoginModel>

    @Headers(TOKEN_HEADER)
    @DELETE("session")
    suspend fun logoutUser(): Resource<LogoutModel>

    @Headers(TOKEN_HEADER)
    @POST("users")
    suspend fun signupUser(@Body request: SignupRequest): Resource<SignupModel>
}