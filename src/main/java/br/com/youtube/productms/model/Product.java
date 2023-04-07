package br.com.youtube.productms.model;

import lombok.Data;

import javax.persistence.*;

@Entity //-> nossa entidade no banco
@Table(name = "tb_product") // -> nome da tabela no banco
@Data // -> Lombok (ele ja coloca construtor nomeado com os parametros, construtor vazio e faz os getters and setters)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "available")
    private boolean available;

    //Boolean => com o B maiusculo ele aceita 3 coisas (objeto)
    //Nulo e verdadeiro e falso

    //boolean => com o b minusculo ele aceita 2 coisas (primitivo)
    //verdadeiro e falso

    //Quando o atributo inicia em letra maiuscula ele aceita nulo
}
