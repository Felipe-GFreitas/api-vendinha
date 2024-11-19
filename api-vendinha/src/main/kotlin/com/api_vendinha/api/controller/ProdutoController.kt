package com.api_vendinha.api.controller

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto
import com.api_vendinha.api.domain.dtos.request.UserRequestDto
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto
import com.api_vendinha.api.domain.dtos.response.UserResponseDto
import com.api_vendinha.api.domain.service.ProdutoServiceInterface
import com.api_vendinha.api.infrastructure.repository.ProdutoRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
class ProdutoController(
    val produtoServiceInterface: ProdutoServiceInterface,
    private val produtoRepository: ProdutoRepository
) {

    @PostMapping("/save")
    fun save(@RequestBody produtoRequestDto: ProdutoRequestDto): ProdutoResponseDto {

        return produtoServiceInterface.save(produtoRequestDto)
    }

    @PutMapping("/update/{id}")
    fun updateProduto(
        @PathVariable id: Long,
        @RequestBody produtoRequestDto: ProdutoRequestDto
    ): ProdutoResponseDto {
        return produtoServiceInterface.update(id, produtoRequestDto)
    }

    @GetMapping("/list")
    fun getAllProdutos():List<ProdutoResponseDto>{
        val produtos = produtoRepository.findAll()

        return produtos.map {
            ProdutoResponseDto(
                id = it.id,
                nome = it.nome,
                preco = it.preco,
                quantidade = it.quantidade,
                user = UserResponseDto(
                    id = it.user?.id!!,
                    name = it.user?.name!!,
                    email = it.user?.email!!,
                    password = it.user?.password!!,
                    cpf_cnpj = it.user?.cpf_cnpj!!,
                    is_active = it.user?.is_active!!
                )
            )
        }
    }

}
