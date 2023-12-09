package com.kotlinspring.controller

import com.kotlinspring.dto.*
import com.kotlinspring.entity.Persona
import com.kotlinspring.service.MyWebService
import com.kotlinspring.service.PersonaService
import com.kotlinspring.service.RoutineService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import kotlin.reflect.KProperty1

@RequestMapping("/bot")
@RestController
class routine(
    private val restTemplate: RestTemplate,
    @Value("\${openai.model}") var model: String,
    @Value("\${openai.api.url}") var url: String,
    @Value("\${openai.api.dalle.url}") var dalle_url: String,
    @Value("\${planfit.api.url}") var planfitUrl: String,
    @Autowired val personaService: PersonaService,
    @Autowired val routineService: RoutineService,
    @Autowired val webClient: WebClient
) {




    @GetMapping("/chat")
    fun getRoutine(@RequestBody persona: PersonaDTO): String {
        val promptLista = "Tell me what of the next muscles or keywords (leg, back, chest, shoulder, core, biceps, triceps, forearm, cardio) are applied in that routine, just return every word separated by a coma, just the keywords, no more words"
        var request = ChatGPTRequest(model, promptCreator(persona))
        //var response = restTemplate.postForObject(url, request, ChatGPTResponse::class.java)

        // Primer request ChatGPT para rutina
        var response = webClient.post().uri(url).body(BodyInserters.fromValue(request)).retrieve()
            .bodyToMono(ChatGPTResponse::class.java).block()

        if (response != null) {
            var request2 = ChatGPTRequest(model, promptLista)
            var response2 = webClient.post().uri(url).body(BodyInserters.fromValue(request2)).retrieve()
                .bodyToMono(ChatGPTResponse::class.java).block()
            if (response2 != null) {
                var planRequest = personToPlanfitRequest(persona,response2.choices.get(0).message.content.split(",").map { it.trim() }.toMutableList())
                //var response3 = webClient.post().uri(planfitUrl).body(BodyInserters.fromValue(planRequest)).retrieve()
                //   .bodyToMono(List::class.java).block()
                var response3 = restTemplate.postForObject(planfitUrl, planRequest, List::class.java)
                if (response3 != null) {
                    return response3.toString()
                }
            }
        }    
        return "No responses from chatGPT"



    }
/*
    @GetMapping("/planfit")
    fun getRoutinePersonalized(@RequestBody request: planFitResquest): String {
        //var request = ChatGPTRequest(model, promptCreator(persona))
        var planRequest = planFitResquest("M",3,70.5f, listOf("leg","chest"),"home")
        var response = restTemplate.postForObject(planfitUrl, request, List::class.java)
        //var response = webClient.post().uri(planfitUrl).body(BodyInserters.fromValue(request)).retrieve()
        //    .bodyToMono(List::class.java).block()

        if (response != null) {
            return response.toString()
        }
        return "No responses from chatGPT"

    }


    fun getImage(prompt: MutableList<String>): MutableList<String> {
        var images: MutableList<String> = mutableListOf()
        for (i in prompt) {
            var request = DalleRequest(i)
            //var response = restTemplate.postForObject(dalle_url, request, DalleResponse::class.java)
            var response = webClient.post().uri(dalle_url).body(BodyInserters.fromValue(request)).retrieve()
                .bodyToMono(DalleResponse::class.java).block()
            if (response != null) {
                images.add(response.data.get(0).url)
            }
        }

        return images

    }


    @PostMapping("/create")
    fun createPersona(
        @Valid @RequestBody persona: PersonaDTO,
        bindingResult: BindingResult
    ): ResponseEntity<MutableList<String>> {
        if (bindingResult.hasErrors()) {
            val errorMessage = StringBuilder("Errores de validación:\n")
            bindingResult.fieldErrors.forEach { error -> errorMessage.append(error.defaultMessage).append("\n") }
            return ResponseEntity.badRequest().body(mutableListOf(errorMessage.toString()))
        }

       // var json: String = this.getRoutinePersonalized(persona)

       // var schedule: RoutinesDTO = messageMapper(json)

       // return ResponseEntity.status(HttpStatus.CREATED).body(getImage(imagesPrompts(schedule.Lunes)))
    }

*/
}
