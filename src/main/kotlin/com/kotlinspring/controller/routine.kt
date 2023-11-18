package com.kotlinspring.controller

import com.kotlinspring.dto.*
import com.kotlinspring.entity.Persona
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
import kotlin.reflect.KProperty1

@RequestMapping("/bot")
@RestController
class routine(private val restTemplate: RestTemplate,
              @Value("\${openai.model}") var model: String,
              @Value("\${openai.api.url}") var url: String,
              @Value("\${openai.api.dalle.url}") var dalle_url:String,
              @Autowired val personaService: PersonaService,
              @Autowired val routineService: RoutineService) {


    @GetMapping("/chat")
    fun getRoutine(@RequestParam prompt: String): String {
        var request = ChatGPTRequest(model, prompt)
        var response = restTemplate.postForObject(url, request, ChatGPTResponse::class.java)


        if (response != null) {
            return response.choices.get(0).message.content
        }
        return "No responses from chatGPT"

    }
    fun getRoutinePersonalized(persona: PersonaDTO): String {
        var request = ChatGPTRequest(model, promptCreator(persona))
        var response = restTemplate.postForObject(url, request, ChatGPTResponse::class.java)


        if (response != null) {
            return response.choices.get(0).message.content
        }
        return "No responses from chatGPT"

    }

    fun getImage(prompt: MutableList<String>): MutableList<String> {
        var images: MutableList<String> = mutableListOf()
        for (i in prompt){
            var request = DalleRequest(i)
            var response = restTemplate.postForObject(dalle_url, request, DalleResponse::class.java)
            if (response != null) {
                images.add(response.data.get(0).url)
            }
        }

        return images

    }


    @PostMapping("/create")
    fun createPersona(@Valid @RequestBody persona: PersonaDTO, bindingResult: BindingResult): ResponseEntity<MutableList<String>>{
        if (bindingResult.hasErrors()) {
            val errorMessage = StringBuilder("Errores de validación:\n")
            bindingResult.fieldErrors.forEach { error -> errorMessage.append(error.defaultMessage).append("\n") }
            return ResponseEntity.badRequest().body(mutableListOf(errorMessage.toString()))
        }

        var json: String = this.getRoutinePersonalized(persona)

        var schedule: RoutinesDTO =  messageMapper(json)

        return ResponseEntity.status(HttpStatus.CREATED).body(getImage(imagesPrompts(schedule.Lunes)))
    }


}
