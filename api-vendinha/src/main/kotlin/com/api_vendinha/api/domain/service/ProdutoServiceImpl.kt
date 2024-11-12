package com.api_vendinha.api.domain.service

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto
import com.api_vendinha.api.domain.dtos.request.UserRequestDto
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto
import com.api_vendinha.api.domain.dtos.response.UserResponseDto
import com.api_vendinha.api.domain.entities.Produto
import com.api_vendinha.api.domain.entities.User
import com.api_vendinha.api.infrastructure.repository.ProdutoRepository
import com.api_vendinha.api.infrastructure.repository.UserRepository
import org.springframework.stereotype.Service

// Marca a classe como um componente de serviço do Spring, o que permite que o Spring a gerencie e a injete em outros componentes.
@Service
class ProdutoServiceImpl (
    // Injeção de dependência do repositório de usuários. O repositório é usado para acessar e manipular dados no banco de dados.
    private val produtoRepository: ProdutoRepository,
    private val userRepository: UserRepository
): ProdutoServiceInterface {


    override fun save(produtoRequestDto: ProdutoRequestDto): ProdutoResponseDto {

        var user = userRepository.findById(produtoRequestDto.user).orElseThrow();

        val  produto = produtoRepository.save(
            Produto(
                nome = produtoRequestDto.nome,
                preco = produtoRequestDto.preco,
                quantidade = produtoRequestDto.quantidade,
                user = user
            )
        )

        return  ProdutoResponseDto(
            id = produto.id,
            nome = produto.nome,
            preco = produto.preco,
            quantidade = produto.quantidade,
            user = UserResponseDto(
                id = user.id,
                name = user.name,
                email = user.email,
                password = user.password,
                cpf_cnpj = user.cpf_cnpj,
                is_active = user.is_active
            )
        )

    }

    override fun update(produtoRequestDto: ProdutoRequestDto): ProdutoResponseDto {
        // Busca o produto pelo ID
        val produto = produtoRepository.findById(id).orElseThrow {
            IllegalArgumentException("Produto não encontrado!")
        }

        // Atualiza os dados do produto com os dados recebidos no DTO
        produto.nome = produtoRequestDto.nome
        produto.preco = produtoRequestDto.preco
        produto.quantidade = produtoRequestDto.quantidade

        // Salva o produto atualizado
        val produtoAtualizado = produtoRepository.save(produto)

        // Retorna o ProdutoResponseDto com os dados atualizados
        return ProdutoResponseDto(
            id = produtoAtualizado.id,
            nome = produtoAtualizado.nome,
            preco = produtoAtualizado.preco,
            quantidade = produtoAtualizado.quantidade,
            user = UserResponseDto(  // Aqui você converte o usuário associado em um UserResponseDto
                id = produtoAtualizado.user?.id ?: throw IllegalArgumentException("Usuário não encontrado!"),
                name = produtoAtualizado.user?.name ?: "",
                email = produtoAtualizado.user?.email ?: "",
                password = produtoAtualizado.user?.password ?: "",
                cpf_cnpj = produtoAtualizado.user?.cpf_cnpj ?: "",
                is_active = produtoAtualizado.user?.is_active ?: false
            )
        )
    }


}
