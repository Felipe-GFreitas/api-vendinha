package com.api_vendinha.api.domain.dtos.response

data class SaleResponseDto(
    val userId: Long,
    val productId: Long,
    val quantity: Int,
    val totalValue: Double
)
