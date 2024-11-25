package com.api_vendinha.api.domain.service
import com.api_vendinha.api.domain.dtos.response.SaleResponseDto
import com.api_vendinha.api.domain.entities.Sale
import com.api_vendinha.api.infrastructure.repository.ProdutoRepository
import org.springframework.stereotype.Service
import com.api_vendinha.api.infrastructure.repository.SaleRepository
import org.springframework.transaction.annotation.Transactional



@Service
class SaleService(
    private val saleRepository: SaleRepository,
    private val produtoRepository: ProdutoRepository
) {

    @Transactional
    fun realizarVenda(userId: Long, productId: Long, quantidade: Int): Sale {
        val produto = produtoRepository.findById(productId)
            .orElseThrow { Exception("Produto não encontrado") }

        if (produto.quantidade < quantidade) {
            throw Exception("Quantidade insuficiente em estoque")
        }

        // Atualizar o estoque
        produto.quantidade -= quantidade
        produtoRepository.save(produto)

        // Criar e salvar a venda
        val venda = Sale(
            userId = userId,
            productId = productId,
            quantity = quantidade,
            price = produto.preco * quantidade
        )
        return saleRepository.save(venda)
    }

    fun listarVendas(): List<SaleResponseDto> {
        return saleRepository.findAll().map { sale ->
            val produto = produtoRepository.findById(sale.productId)
                .orElseThrow { Exception("Produto não encontrado") }

            // Calculando o valor total da venda
            val totalValue = sale.quantity * produto.preco

            // Mapeando a venda para o DTO de resposta
            SaleResponseDto(
                userId = sale.userId,
                productId = sale.productId,
                quantity = sale.quantity,
                totalValue = totalValue.toDouble()
            )
        }
    }
}