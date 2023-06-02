package com.example.api.exception

import org.springframework.http.HttpStatus

enum class CustomErrorCode(
        val statusCode:HttpStatus,
        val errorCode:String,
        val errorMesasge:String
) {
    RESULT_NOT_FOUND(HttpStatus.NOT_FOUND, "E001", errorMesasge = "result not found")
}