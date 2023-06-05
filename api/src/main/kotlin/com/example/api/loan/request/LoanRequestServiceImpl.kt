package com.example.api.loan.request

import com.example.api.loan.GenerateKey
import com.example.api.loan.encrypt.EncryptComponent
import com.example.domain.repository.UserInfoRepository
import com.example.kafka.enum.KafkaTopic
import com.example.kafka.producer.LoanRequestSender
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
        private val generateKey: GenerateKey,
        private val userInfoRepository: UserInfoRepository,
        private val encryptComponent: EncryptComponent,
        private val loanRequestSender: LoanRequestSender
) :LoanRequestService {
    override fun loanRequestMain(
            loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        val userKey = generateKey.generateUserKey()

        loanRequestInputDto.userRegistrationNumber =
                encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)

        val userInfoDto = loanRequestInputDto.toUserInfoDto(userKey)

        saveUserInfo(userInfoDto)

        loanRequestReview(userInfoDto)

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) =
       userInfoRepository.save(userInfoDto.toEntity())


    override fun loanRequestReview(userInfoDto: UserInfoDto) {
        loanRequestSender.sendMessage(
                KafkaTopic.LOAN_REQUEST,
                userInfoDto.toLoanRequestKafkaDto()
        )
    }

}