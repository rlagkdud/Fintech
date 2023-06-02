package com.example.api.exception

import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

class ErrorResponse(
        private val customException: CustomException
){

    fun toResponseEntity():ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
                .status(customException.customErrorCode.statusCode)
                .body(ErrorResponseDto(
                        errorCode = customException.customErrorCode.errorCode,
                        errorMessage = customException.customErrorCode.errorMesasge
                ))
    }

    data class ErrorResponseDto(
            val errorCode:String,
            val errorMessage:String
    ){
        val timeStamp = LocalDateTime.now();
    }
}