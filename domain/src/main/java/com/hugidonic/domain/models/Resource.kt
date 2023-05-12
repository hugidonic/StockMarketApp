package com.hugidonic.domain.models

sealed class Resource<T>(data: T? = null, message: String? = null) {
    class Success<T>(data: T? = null): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(message=message, data=data)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(data = null)
}