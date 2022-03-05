package com.iamkurtgoz.domain.useCase

interface IUseCaseWithoutParam<Result> {
    operator fun invoke(): Result
}

interface IUseCase<in Params, Result> {
    operator fun invoke(params: Params): Result
}
