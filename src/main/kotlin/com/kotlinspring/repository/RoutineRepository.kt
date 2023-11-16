package com.kotlinspring.repository

import com.kotlinspring.entity.Routine
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RoutineRepository : MongoRepository<Routine,Int> {
}