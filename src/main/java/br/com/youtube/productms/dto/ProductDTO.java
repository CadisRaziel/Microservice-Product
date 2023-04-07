package br.com.youtube.productms.dto;

/*
 * DTO
 * Objeto que espelha a entidade (model)
 * utilizado para transicionar os dados sem a necessidade de expor a entidade(model)
 * */

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data // -> Lombok (ele ja coloca construtor nomeado com os parametros, construtor vazio e faz os getters and setters)
public class ProductDTO {
    /*
    * Porque nao pegamos o ID:
    * Durante a criação o id ainda nao existe
    * e durante o retorno nem sempre a gente quer devolver o id
    * caso for retornar, coloque-o aqui
    * */

    @NotBlank //-> quero que venha ao menos 1 caracter (nao vai aceita nullo)
    private String name;

    @Size(min = 50) //-> a descrição tem que ter no minimo 50 caracteres, menos que isso não será aceita
    private String description;

    @Positive //-> minimo valor aceitavel "0.01"
    private double price;
    private boolean available;
}
