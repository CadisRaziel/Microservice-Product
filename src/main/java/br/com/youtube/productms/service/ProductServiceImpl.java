package br.com.youtube.productms.service;

import br.com.youtube.productms.dto.ProductDTO;
import br.com.youtube.productms.model.Product;
import br.com.youtube.productms.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Diferença de Extends vs Implements
 * Implements -> implementar uma interface (obrigatorio colocar seus metodos da interface)
 * Extends -> Tudo que uma classe tem a classe que recebe o extends tambem tem
 * (porém nao é obrigatorio por os metodos, e podemos sobreescreve-los)
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    //Controller acessa Service e Service acessa repository
    private ProductRepository repository;
    private ModelMapper mapper;

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

    @Override
    public List<ProductDTO> getAll() {
        //vamos utilizar o ModelMapper (estou usando via @Beans)
        //ModelMapper mapper = new ModelMapper();

        List<Product> products = repository.findAll();
        List<ProductDTO> responses = new ArrayList<>();

        /*
        //eu posso utilizar tanto esse for, quanto a lamda abaixo
         for (Product product : products) {
            //toda vez que que rodar um item da lista 'products'
            //a gente estancia o ProductDTO
            ProductDTO response =  mapper.map(product, ProductDTO.class);
            responses.add(response);
        }
        * */

        //uma alternativa do for acima, podemos usar lambda
        products.forEach(product -> {
            ProductDTO response = mapper.map(product, ProductDTO.class);
            responses.add(response);
        });

        return responses;
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        //Optional -> Evita o nullpointer (ele nos da o metodo 'isPresent')
        Optional<Product> product = repository.findById(id);

        //POSSO FAZER DESSE JEITO
        if (product.isPresent()) {
            //isPresent -> Se tem valor
            return Optional.of(mapper.map(product.get(), ProductDTO.class));
        }
        return Optional.empty();


        //Ou posso fazer com lambda dessa forma
        /*
          return product.map(value -> mapper.map(value, ProductDTO.class));
         */
    }

    @Override
    public boolean inactive(Long id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){//se o id que o usuario passou existe no banco, se é presente no banco
            product.get().setAvailable(false); //setamos o campo pra false ele ele existir
            repository.save(product.get());
            return true;
        }
        return false;
    }

    //TODO falta fazer
    @Override
    public Optional<ProductDTO> update(Long id, ProductDTO request) {
        return Optional.empty();
    }


    //diferença de um delete fisico pra um logico
    //a cima temos um logico que vamos utilizar /\ (manipulamos ele para inativar no banco)
    //a baixo temos um fisico de exemplo \/ (deletamos do banco)

    //Caso fosse um delete fisico
    //nao vou comentar ele, ele só nao sera usado
    public boolean delete(Long id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){//se o id que o usuario passou existe no banco, se é presente no banco
           repository.deleteById(id);
            return true;
        }
        return false;
    }
}


/**
 * O service é responsavel pelas regras de negocio
 * e conhecer o cara que acessa a camada de dados que é o repository
 * */