package com.api_vendinha.api.domain.service
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
}
