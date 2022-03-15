package com.newstore.network.mapper

class DataMappingException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    constructor(message: String?) : this(message, null)

    companion object {
        fun forInvalidDataMapperClass(mapperClassName: String?): DataMappingException {
            return DataMappingException("Invalid Data Mapper ($mapperClassName) defined for Response")
        }

        fun forUnableToUseDataMapper(exception: Exception): DataMappingException {
            return DataMappingException("Unable to use Data Mapper", exception)
        }

        fun forNoDataMapperDefined(responseClassName: String?): DataMappingException {
            return DataMappingException("No Data Mapper defined for response class: $responseClassName")
        }

        fun forUnableToExtractResponseClass(responseClassName: String?): DataMappingException {
            return DataMappingException("Could not extract Response Class ($responseClassName) from API response type")
        }
    }
}