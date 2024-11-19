package com.api_vendinha.api.domain.dtos.request

data class SaleRequestDto(
    val userId: Long,
    val productId: Long,
    val quantity: Int
)
