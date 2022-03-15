package com.newstore.favqs.features.account.logout.mapper

import com.newstore.favqs.features.account.logout.model.LogoutModel
import com.newstore.favqs.features.account.logout.network.LogoutResponse
import com.newstore.network.mapper.BaseMapper

class LogoutMapper : BaseMapper<LogoutResponse, LogoutModel> {

    override fun map(remote: LogoutResponse): LogoutModel {
        return LogoutModel(remote.message)
    }
}