package com.kotlinspring.dto

import com.kotlinspring.entity.Persona
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ChatGPTRequest(
    var model: String,
    var messages: MutableList<Message>
){

    constructor(model: String, prompt: String) : this(model, mutableListOf()) {
        messages.add(Message(prompt,"user"))
    }
}

data class Message(
    val content: String,
    val role: String
)

data class ChatGPTResponse(
    val choices: List<Choice>
)

data class Choice(
    val index: Int,
    val message: Message
)

data class DalleRequest(
    val prompt: String
)

data class PersonaDTO(@NotBlank(message = "El nombre no puede estar en blanco")
                      val nombre: String = "",
                      @NotNull(message = "El campo no puede ser nulo")
                      val edad: Int = 0,
                      @NotNull(message = "El campo no puede ser nulo")
                      val peso: Float = 0f,
                      @NotNull(message = "El campo no puede ser nulo")
                      val altura: Float = 0f,
                      @NotNull(message = "El campo no puede ser nulo")
                      val proposito: String = ""){

}

data class DalleResponse(
    val data: List<images>
)

data class images(
    val url: String
)

fun promptCreator(persona : Persona) : String{

    return "Puedes crear una rutina de ejercicio personalmente para mi que tengo ${persona.edad} años" +
            " peso ${persona.peso} kilos y mido ${persona.altura} metros, no mas de 250 palabras"
}
