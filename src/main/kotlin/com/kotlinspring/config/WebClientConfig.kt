package com.kotlinspring.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient


@Configuration
class WebClientConfig {

    @Value("\${openai.api.key}")
    private lateinit var openaiApiKey: String
    @Bean
    fun webClientBuilder(): WebClient.Builder {
        // Configura el cliente HTTP de Reactor con un timeout personalizado
        val httpClient = HttpClient.create()
            .responseTimeout(java.time.Duration.ofSeconds(70))

        // Configura el intercambiador de estrategias para admitir tamaños grandes de respuesta
        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { it.defaultCodecs().maxInMemorySize(16 * 1024 * 1024) }
            .build()

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .exchangeStrategies(exchangeStrategies)
            .defaultHeader("Authorization", "Bearer $openaiApiKey") // Agrega encabezados personalizados
        // Agrega más configuraciones según sea necesario
    }

    @Bean
    fun webClient(webClientBuilder: WebClient.Builder): WebClient {
        return webClientBuilder.build()
    }
}