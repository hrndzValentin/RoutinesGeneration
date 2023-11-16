package com.kotlinspring.controller

import com.kotlinspring.dto.*
import com.kotlinspring.entity.Persona
import com.kotlinspring.service.PersonaService
import com.kotlinspring.service.RoutineService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RequestMapping("/bot")
@RestController
class routine(private val restTemplate: RestTemplate,
              @Value("\${openai.model}") var model: String,
              @Value("\${openai.api.url}") var url: String,
              @Value("\${openai.api.dalle.url}") var dalle_url:String,
              @Autowired val personaService: PersonaService,
              @Autowired val routineService: RoutineService) {


    @GetMapping("/chat")
    fun getRoutine(@RequestParam prompt : String): String{
        var request = ChatGPTRequest(model, prompt)
        var response = restTemplate.postForObject(url,request,ChatGPTResponse::class.java)


        if (response != null) {
            return response.choices.get(0).message.content
        }
        return "No responses from chatGPT"

    }

    @GetMapping("/chat/prompt")
        fun getRoutinePersonalized(@RequestBody persona: Persona): String{
        var request = ChatGPTRequest(model, promptCreator(persona))
        var response = restTemplate.postForObject(url,request,ChatGPTResponse::class.java)


        if (response != null) {
            return response.choices.get(0).message.content
        }
        return "No responses from chatGPT"

    }

    @GetMapping("/images")
    fun getImage(@RequestParam prompt : String): String{
        var request = DalleRequest(prompt)
        var response = restTemplate.postForObject(dalle_url,request,DalleResponse::class.java)


        if (response != null) {
            return response.data.get(0).url
        }
        return "No responses from Dalle 2.0"

    }

    @PostMapping("/create")
    fun createPersona(@Valid @RequestBody persona: PersonaDTO, bindingResult: BindingResult): ResponseEntity<String>{
        if (bindingResult.hasErrors()) {
            val errorMessage = StringBuilder("Errores de validación:\n")
            bindingResult.fieldErrors.forEach { error -> errorMessage.append(error.defaultMessage).append("\n") }
            return ResponseEntity.badRequest().body(errorMessage.toString())
        }

    }


}