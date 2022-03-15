package com.newstore.favqs.features.account.signup.mapper

import com.newstore.favqs.features.account.signup.model.SignupModel
import com.newstore.favqs.features.account.signup.network.SignupResponse
import com.newstore.network.mapper.BaseMapper

class SignupMapper : BaseMapper<SignupResponse, SignupModel> {

    override fun map(remote: SignupResponse): SignupModel {
        return SignupModel(remote.userToken)
    }
}