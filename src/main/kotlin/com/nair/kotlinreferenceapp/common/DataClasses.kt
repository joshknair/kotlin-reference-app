package com.nair.kotlinreferenceapp.common

data class Message(val message: String)

data class ErrorResponse(val message: String,
                         val errors: List<Error>? = emptyList())

data class Error(val field: String, val error: String)