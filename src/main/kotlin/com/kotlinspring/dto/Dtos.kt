package com.kotlinspring.dto

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
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
/*
data class DalleRequest(
    val prompt: String
)
*/
data class PersonaDTO(@NotBlank(message = "El nombre no puede estar en blanco")
                      val nombre: String,
                      @NotNull(message = "El campo no puede ser nulo")
                      val edad: Int,
                      @NotNull(message = "El campo no puede ser nulo")
                      val peso: Float,
                      @NotNull(message = "El campo no puede ser nulo")
                      val altura: Float,
                      @NotNull(message = "El campo no puede ser nulo")
                      val proposito: String,
                      @NotNull(message = "El campo no puede ser nulo")
                      val gender: String,
                      @NotNull(message = "El campo no puede ser nulo")
                      val fitness_level: Int,
                      @NotNull(message = "El campo no puede ser nulo")
                      val location: String){

}
/*
data class DalleResponse(
    val data: List<images>
)

data class images(
    val url: String
)
*/
fun promptCreator(persona : PersonaDTO) : String{

    return "Create a workout routine for a ${persona.gender} with the purpose of ${persona.proposito} a total exercises for one week"
}

/*
fun promptPlanfit(): String{
    return "make a workout routine for chest using the planfit plugin"
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
        var prompt : String = "genera imagen de hombre haciendo $i genera de forma creativa, asegurate que la imagen sea clara y detallada"
        prompts.add(prompt)
    }
    return prompts
}
*/
data class planFitResquest(
    val gender: String,
    val fitness_level: Int,
    val body_weight: Float,
    val parts:List<String>,
    val location:String
    )


fun personToPlanfitRequest(persona: PersonaDTO, parts:MutableList<String>): planFitResquest{
    val planfit = planFitResquest(persona.gender.get(0).toString(),persona.fitness_level,persona.peso,parts,persona.location)
    println(planfit)
    return planfit
}