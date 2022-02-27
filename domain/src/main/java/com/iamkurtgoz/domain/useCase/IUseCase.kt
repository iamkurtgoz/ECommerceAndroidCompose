package com.iamkurtgoz.domain.useCase

interface IUseCase<in Params, Result> {
    operator fun invoke(params: Params? = null): Result
}
