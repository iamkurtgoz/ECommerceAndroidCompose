package com.iamkurtgoz.domain.mapper

interface IMapper<E,R> {
    fun mapToResponse(model: E): R
}