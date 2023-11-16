package com.kotlinspring.entity

import com.kotlinspring.dto.Message
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
class Persona (@Id
               @GeneratedValue(strategy = GenerationType.IDENTITY)
               val id: ObjectId = ObjectId(),
               val nombre: String = "",
               val edad: Int = 0,
               val peso: Float = 0f,
               val altura: Float = 0f,
               val proposito: String = "",
               var images : MutableList<String>,
               @OneToMany(mappedBy = "persona")
               var routine: MutableList<Routine>

    ){
}