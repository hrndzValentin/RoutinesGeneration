package com.kotlinspring.service

import com.kotlinspring.dto.ChatGPTRequest
import com.kotlinspring.dto.ChatGPTResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class OpenAIService(private val restTemplate : RestTemplate,
                   @Value("\${openai.api.key}") private val openaiApiKey: String,
                   @Value("\${openai.api.url}") private val openaiApiUrl: String) {



    fun restTemplate(data: ChatGPTRequest): ChatGPTResponse {

        val headers = org.springframework.http.HttpHeaders()
        headers.setBearerAuth(openaiApiKey)

        val entity = HttpEntity(data, headers)
        val response = restTemplate.exchange(
            openaiApiUrl,
            HttpMethod.POST,
            entity,
            ChatGPTResponse::class.java
        )
        return response.body ?: throw RuntimeException("Respuesta nula del API de OpenAI")

    }
}