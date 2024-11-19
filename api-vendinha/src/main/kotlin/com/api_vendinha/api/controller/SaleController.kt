package com.api_vendinha.api.controller
import org.springframework.web.bind.annotation.*
import com.api_vendinha.api.domain.service.SaleService
import com.api_vendinha.api.domain.dtos.request.SaleRequestDto
import org.springframework.http.ResponseEntity


@RestController
@RequestMapping("/vendas")
@CrossOrigin("*")

class SaleController(private val saleService: SaleService) {

    @PostMapping
    fun realizarVenda(@RequestBody saleRequest: SaleRequestDto): ResponseEntity<String> {
        saleService.realizarVenda(
            userId = saleRequest.userId,
            productId = saleRequest.productId,
            quantidade = saleRequest.quantity
        )
        return ResponseEntity.ok("Venda realizada com sucesso!")
    }
}
