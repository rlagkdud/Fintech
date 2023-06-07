package com.example.consumer.kafka

import com.example.consumer.service.LoanRequestService
import com.example.kafka.dto.LoanRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class LoanRequestConsumer(
        private val objectMapper: ObjectMapper,
        private val loanRequestService: LoanRequestService
) {
    @KafkaListener(topics = ["loan_request"], groupId = "fintech")
    fun loanRequestTopicConsumer(message:String){
        val loanRequestKafkaDto = objectMapper.readValue(message, LoanRequestDto::class.java)
       // println(loanRequestKafkaDto) // 카프카로부터 메세지를 잘 읽어오는지 확인하기 위한 로그
        //CB 사 호출 로직
        loanRequestService.loanRequest(loanRequestKafkaDto)
    }
}