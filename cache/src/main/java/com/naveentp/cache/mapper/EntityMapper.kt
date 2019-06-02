package com.naveentp.cache.mapper

/**
 * @author Naveen T P
 * @since 02/06/19
 */
interface EntityMapper<E, D> {
    fun mapToEntity(domain: D): E
    fun mapFromEntity(entity: E): D
}