package br.com.youtube.productms.service;

import br.com.youtube.productms.dto.ProductDTO;

import java.util.List;
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

    List<ProductDTO> getAll();

    Optional<ProductDTO> getById(Long id);

    boolean inactive(Long id);
    //delete fisico, nos removemos do banco
    //delete logico, nos inativamos no banco
    //nos estamos utilizando um atribudo chamado "isAvailable" true or false
    //então faremos um delete logico, para inativar ele no banco
}
