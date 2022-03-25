package com.newstore.favqs.features.account.login.mapper

import com.newstore.favqs.features.account.login.model.LoginModel
import com.newstore.favqs.features.account.login.network.LoginResponse
import com.newstore.network.mapper.BaseMapper

class LoginMapper : BaseMapper<LoginResponse, LoginModel> {

    override fun map(remote: LoginResponse): LoginModel {
        return LoginModel(remote.userToken)
    }
}