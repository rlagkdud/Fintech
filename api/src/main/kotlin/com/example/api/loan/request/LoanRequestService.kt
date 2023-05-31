package com.example.api.loan.request

import com.example.domain.domain.UserInfo

interface LoanRequestService {
    fun loanRequestMain(
            loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ) : LoanRequestDto.LoanRequestResponseDto

    fun saveUserInfo(
            userInfoDto: UserInfoDto
    ) :UserInfo

    fun loanRequestReview(userKey:String)
}