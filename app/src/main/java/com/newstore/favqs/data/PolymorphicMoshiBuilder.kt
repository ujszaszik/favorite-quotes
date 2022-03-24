package com.newstore.favqs.data

import com.newstore.favqs.features.quote.QuoteSearchType
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object PolymorphicMoshiBuilder {

    fun build(): Moshi {
        return Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(QuoteSearchType::class.java, "quoteSearchType")
                    .withSubtype(QuoteSearchType.All::class.java, "all")
                    .withSubtype(QuoteSearchType.Tag::class.java, "tag")
                    .withSubtype(QuoteSearchType.Keyword::class.java, "keyword")
                    .withDefaultValue(QuoteSearchType.All())
            )
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}