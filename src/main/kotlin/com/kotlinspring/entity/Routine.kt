package com.kotlinspring.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "rutinas")
@Data
@AllArgsConstructor
@NoArgsConstructor
class Routine(@Id
              @GeneratedValue(strategy = GenerationType.IDENTITY)
              val id: ObjectId = ObjectId(),
              val routine: String = "",
              @ManyToOne
              @JoinColumn(name="id")
              val persona: Persona
    ) {
}