package com.kotlinspring.service

import com.kotlinspring.dto.ChatGPTRequest
import com.kotlinspring.dto.ChatGPTResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Service
class MyWebService (private val webClient: WebClient,
                    @Value("\${openai.api.url}") private val openaiApiUrl: String){


    fun makeWebClientRequest(JsonRequest : ChatGPTRequest) {
        // Usa el WebClient inyectado para hacer solicitudes
        val response = webClient.post()
            .uri(openaiApiUrl)
            .body(BodyInserters.fromValue(JsonRequest))
            .retrieve()
            .bodyToMono(ChatGPTResponse::class.java)
            .block()

    }
}