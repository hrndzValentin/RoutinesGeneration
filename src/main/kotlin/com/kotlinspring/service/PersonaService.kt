package com.kotlinspring.service

import com.kotlinspring.entity.Persona
import com.kotlinspring.repository.PersonaRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

@Service
class PersonaService: PersonaRepository {
    override fun <S : Persona?> save(entity: S & Any): S & Any {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<Persona> {
        TODO("Not yet implemented")
    }

    override fun findAll(sort: Sort): MutableList<Persona> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<Persona> {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableList<Persona> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Persona) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<Persona>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R & Any {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> insert(entity: S & Any): S & Any {
        TODO("Not yet implemented")
    }

    override fun <S : Persona?> insert(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<Persona> {
        TODO("Not yet implemented")
    }
}