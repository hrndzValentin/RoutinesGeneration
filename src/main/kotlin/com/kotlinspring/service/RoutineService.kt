package com.kotlinspring.service

import com.kotlinspring.entity.Routine
import com.kotlinspring.repository.RoutineRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

@Service
class RoutineService : RoutineRepository{
    override fun <S : Routine?> save(entity: S & Any): S & Any {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<Routine> {
        TODO("Not yet implemented")
    }

    override fun findAll(sort: Sort): MutableList<Routine> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<Routine> {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableList<Routine> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Routine) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<Routine>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R & Any {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> insert(entity: S & Any): S & Any {
        TODO("Not yet implemented")
    }

    override fun <S : Routine?> insert(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<Routine> {
        TODO("Not yet implemented")
    }

}