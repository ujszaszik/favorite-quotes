package com.newstore.network.converter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonWriter
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import retrofit2.Converter

class QuotesRequestBodyConverter<T : Any>(private val adapter: JsonAdapter<T>) :
    Converter<T, RequestBody> {

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer = JsonWriter.of(buffer)
        adapter.toJson(writer, value)
        return buffer.readByteString().toRequestBody(MEDIA_TYPE)
    }

    companion object {
        private val MEDIA_TYPE: MediaType = "application/json; charset=UTF-8".toMediaType()
    }
}