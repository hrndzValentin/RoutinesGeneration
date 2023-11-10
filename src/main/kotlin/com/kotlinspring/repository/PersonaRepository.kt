package com.kotlinspring.repository
import com.kotlinspring.entity.Persona
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepository : MongoRepository<Persona, Int> {
}