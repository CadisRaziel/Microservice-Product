package br.com.youtube.productms.service;

import br.com.youtube.productms.dto.ProductDTO;

import java.util.Optional;

/**
 * O que a interface faz ?
 * * ela faz a assinatura do metodo (cria o metodo só a carcaça)
 * quem chamar essa interface precisa implementar tudo que tem nele
 * ai no caso de algum parametro ou nome for alterado, é só alterar aqui ao invés de todas as classes
 * que implementa essa interface
 * */

public interface ProductService {
    Optional<ProductDTO> create(ProductDTO request);
}
