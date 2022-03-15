package com.newstore.network.mapper

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ResponseMapper<R, L>(private val data: R) {

    @Suppress("UNCHECKED_CAST")
    fun mapResponse(): L {
        var mapper = getMapperClass().createInstance()
        if (!isValidMapperClass(mapper)) {
            throw DataMappingException.forInvalidDataMapperClass(mapper.javaClass.canonicalName)
        }
        return try {
            mapper = mapper as BaseMapper<R, L>
            mapper.map(data!!)
        } catch (e: Exception) {
            throw DataMappingException.forUnableToUseDataMapper(e)
        }
    }

    private fun getMapperClass(): KClass<*> {
        val responseClass = data!!.javaClass
        return responseClass
            .getAnnotation(DataMapper::class.java)
            ?.mapperClass
            ?: throw DataMappingException.forNoDataMapperDefined(responseClass.canonicalName)
    }

    private fun isValidMapperClass(mapperClass: Any): Boolean {
        val validMapperInterface = BaseMapper::class.java.canonicalName
        return mapperClass.javaClass.interfaces.any { it.canonicalName == validMapperInterface }
    }

}