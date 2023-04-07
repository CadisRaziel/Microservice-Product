package br.com.youtube.productms.service;

import br.com.youtube.productms.dto.ProductDTO;
import br.com.youtube.productms.model.Product;
import br.com.youtube.productms.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Diferença de Extends vs Implements
 * Implements -> implementar uma interface (obrigatorio colocar seus metodos da interface)
 * Extends -> Tudo que uma classe tem a classe que recebe o extends tambem tem
 * (porém nao é obrigatorio por os metodos, e podemos sobreescreve-los)
 * */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    //Controller acessa Service e Service acessa repository
    private ProductRepository repository;
    @Override
    public Optional<ProductDTO> create(ProductDTO request) {
        /**
         * Precisamos:
         * Transformar a request do parametro em objeto de banco
         * e depois transforma o objeto de banco em um response caso os dados for diferentes
         * resumindo: Request -> Model/Entidade -> Response
         * */

        //--------------Naturalmente fariamos assim!!--------------
        /*
        Product product = new Product();
        //veja como eu só acesso o modelo pelo set, o get eu pego pelo DTO
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setAvailable(request.isAvailable());

        //saveAndFlush -> disponibiliza o dado na hora caso ele seja consultado
        repository.saveAndFlush(product);


        ProductDTO response = new ProductDTO();
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setAvailable(product.isAvailable());

        return Optional.of(response);
        * */

        //--------------PORÈM PODEMOS FAZER ASSIM!!--------------
        //Porém incluimos o mapper em nosso projeto, e o que esta acima pode ficar assim\/
        ModelMapper mapper = new ModelMapper();

        //Request -> Origem(product.setName)
        //Product.class -> Destino(request.getName())
        Product product = mapper.map(request, Product.class);

        //saveAndFlush -> disponibiliza o dado na hora caso ele seja consultado
        repository.saveAndFlush(product);



        //Product -> Origem(response.setName)
        //ProductDTO.class -> Destino(product.getName())
        ProductDTO response = mapper.map(product, ProductDTO.class);
        return Optional.of(response);
    }
}
