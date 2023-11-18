package com.kotlinspring.dto

import com.kotlinspring.entity.Persona
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

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

fun promptCreator(persona : PersonaDTO) : String{

    return "Genera una rutina de ejercicio para ${persona.proposito}, tengo ${persona.edad} años, peso ${persona.peso} kilos, mido ${persona.altura} metros " +
            "y soy hombre dame la organizacion de la rutina para una semana, " +
            "devuelvela en un formato JSON con 7 campos para cada dia de la semana cada campo tiene solo la lista de ejercicios"
}

@Serializable
data class RoutinesDTO(
    val Lunes: MutableList<String>,
    val Martes: MutableList<String>,
    val Miércoles: MutableList<String>,
    val Jueves: MutableList<String>,
    val Viernes: MutableList<String>,
    val Sábado: MutableList<String>,
    val Domingo: MutableList<String>
)

fun messageMapper(json: String): RoutinesDTO{

    val routine = Json.decodeFromString<RoutinesDTO>(json)
    return routine
}

fun imagesPrompts(excercises:MutableList<String>): MutableList<String>{
    var prompts: MutableList<String> = mutableListOf()
    for (i in excercises){
        var prompt : String = "persona haciendo $i"
        prompts.add(prompt)
    }
    return prompts
}
