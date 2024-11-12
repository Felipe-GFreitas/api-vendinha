package com.api_vendinha.api.domain.entities

import jakarta.persistence.*


@Table(name = "produtos")
@Entity
data class Produto (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    val id: Long? = null, // O id pode ser nulo inicialmente antes de ser atribuído pelo banco de dados.

    @Column(name = "nome")
    var nome: String,

    @Column(name = "preco")
    var preco: Float,

    @Column(name = "quantidade")
    var quantidade: Int,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,
)

