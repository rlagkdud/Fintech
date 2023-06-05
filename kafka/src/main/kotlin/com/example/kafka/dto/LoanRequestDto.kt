package com.example.kafka.dto

data class LoanRequestDto(
        val userKey: String,
        val userName: String,
        val userIncomeAmount: Long,
        var userRegistrationNumber: String
)
