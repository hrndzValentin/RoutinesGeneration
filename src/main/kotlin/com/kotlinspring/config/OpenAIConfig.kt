package com.kotlinspring.config

import com.kotlinspring.dto.ChatGPTRequest
import com.kotlinspring.dto.ChatGPTResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class OpenAIConfig {

    @Value("\${openai.api.key}")
    private lateinit var openaiApiKey: String

    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        val interceptor = ClientHttpRequestInterceptor { request, body, execution ->
            request.headers.add("Authorization", "Bearer $openaiApiKey")
            execution.execute(request, body)
        }
        restTemplate.interceptors.add(interceptor)
        return restTemplate
    }
}