package com.newstore.favqs.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.newstore.favqs.features.account.logout.ui.LogoutHost
import com.newstore.favqs.features.account.profile.ProfileHost
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.details.ui.QUOTE_ID_KEY
import com.newstore.favqs.features.quote.details.ui.QuoteDetailsHost
import com.newstore.favqs.features.quote.list.ui.QUOTE_SEARCH_KEY
import com.newstore.favqs.features.quote.list.ui.QuoteListHost
import com.newstore.favqs.navigation.arguments.retainParam
import com.newstore.favqs.navigation.composable
import com.newstore.favqs.navigation.dialog

const val QUOTE_GRAPH_ROUTE = "QuoteRoute"

fun NavGraphBuilder.quoteGraph() {

    navigation(QuoteListHost.route, route = QUOTE_GRAPH_ROUTE) {

        composable(QuoteListHost) {
            val searchParams = it.retainParam<QuoteSearchParams>(QUOTE_SEARCH_KEY)
            QuoteListHost(searchParams ?: QuoteSearchParams.default())
        }

        composable(QuoteDetailsHost) {
            val quoteId = it.retainParam<Long>(QUOTE_ID_KEY)
            QuoteDetailsHost(quoteId)
        }

        dialog(ProfileHost) {
            ProfileHost()
        }

        dialog(LogoutHost) {
            LogoutHost()
        }
    }
}