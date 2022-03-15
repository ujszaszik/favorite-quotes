package com.newstore.network.converter

import com.newstore.extension.simpleName
import com.newstore.network.ApiErrorCodes
import com.newstore.network.call.Resource
import com.newstore.network.mapper.DataMappedFrom
import com.newstore.network.mapper.DataMappingException
import com.newstore.network.mapper.ResponseMapper
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import okio.ByteString
import okio.ByteString.Companion.decodeHex
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import timber.log.Timber
import java.lang.reflect.Type
import kotlin.reflect.KClass

class QuotesResponseBodyConverter<T, R>(private val moshi: Moshi, private val type: Type) :
    Converter<ResponseBody, Resource<R>> {

    @Suppress("UNCHECKED_CAST")
    override fun convert(value: ResponseBody): Resource<R> {
        val source = value.source()
        if (source.rangeEquals(0, UTF8_BOM)) {
            source.skip(UTF8_BOM.size.toLong())
        }
        val rawString = value.string()
        return try {
            if (hasErrorCode(rawString)) {
                Resource.error(parseErrorCodeFromJson(rawString))
            } else {
                val adapter = moshi.adapter(getResponseClass().java)
                val result = adapter.fromJson(rawString)
                val mapper = ResponseMapper<T, R>(result!! as T)
                val mappedResponse = mapper.mapResponse()
                Resource.success(mappedResponse)
            }
        } catch (e: Exception) {
            Timber.i(e, "Could not convert API response")
            Resource.error(getApiErrorCOde(rawString))
        }
    }

    private fun getApiErrorCOde(rawString: String): Int {
        return try {
            parseErrorCodeFromJson(rawString)
        } catch (e: Exception) {
            Timber.i(e, "Could not parse error code from API response")
            ApiErrorCodes.DEFAULT.code
        }
    }

    private fun hasErrorCode(rawString: String): Boolean {
        return try {
            JSONObject(rawString).getJSONArray("errorCodes")
            true
        } catch (exception: JSONException) {
            Timber.i("API response does not have error code")
            false
        }
    }

    private fun parseErrorCodeFromJson(rawString: String): Int {
        val json = JSONObject(rawString)
        return json.getInt("error_code")
    }

    private fun getResponseClass(): KClass<*> {
        return Class.forName(type.simpleName)
            .getAnnotation(DataMappedFrom::class.java)
            ?.mapperClass
            ?: throw DataMappingException.forUnableToExtractResponseClass(type.simpleName)
    }

    companion object {
        private val UTF8_BOM: ByteString = "EFBBBF".decodeHex()
    }
}