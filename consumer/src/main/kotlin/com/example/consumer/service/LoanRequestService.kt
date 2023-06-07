package com.example.consumer.service

import com.example.consumer.dto.ReviewResponseDto
import com.example.domain.domain.LoanReview
import com.example.domain.repository.LoanReviewRepository
import com.example.kafka.dto.LoanRequestDto
import com.example.kafka.producer.LoanRequestSender
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class LoanRequestService(
        private val loanReviewRepository: LoanReviewRepository
) {
    companion object{
        const val cssUrl = "http://localhost:8081/css/api/v1/request"
    }

    fun loanRequest(loanRequestDto: LoanRequestDto){
        // CB compoment로 요청 보내기 -> 응답값을 DB에 저장하기
        val reviewResult = loanRequestToCb(loanRequestDto)

        saveLoanReviewData(reviewResult.toLoanReviewEntity())

    }

    private fun loanRequestToCb(loanRequestDto: LoanRequestDto) : ReviewResponseDto{
        val restTemplate = RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(1000))
                .build()

        return restTemplate.postForEntity(cssUrl, loanRequestDto, ReviewResponseDto::class.java).body!!
    }

    private fun saveLoanReviewData(loanReview:LoanReview) = loanReviewRepository.save(loanReview)
}