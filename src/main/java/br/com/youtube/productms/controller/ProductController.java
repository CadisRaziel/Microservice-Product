package br.com.youtube.productms.controller;


import br.com.youtube.productms.dto.ProductDTO;
import br.com.youtube.productms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products") // -> @PostMapping / @GetMapping etc... todas ja terao o "/products"
public class ProductController {

    //Valid -> pois colocamos validações no productDOTO

    @Autowired
    private ProductService service;
    //A classe controller tem acesso somente ao service
    //porém as vezes pode ter tambem repository

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO request) {
        //(@RequestBody @Valid ProductDTO request -> o que entra

        //quando eu crio um produto eu quero que ele ja fique disponivel(linha necessaria caso o front nao envie para nos)
        request.setAvailable(true);

        //Optional<ProductDTO> response = service.create(request); -> o que é respondido
        Optional<ProductDTO> response = service.create(request);

        //Optional -> sempre que colocarmos temos que verificar com o isPresent
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        }
        //caso der false
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //Ou podemos eliminar o if e o return e fazer usando lambda
        //return response.map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("id") Long id) {
        //@PathVariable -> variavel no caminho da url
        Optional<ProductDTO> response = service.getById(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        //caso o id não é encontrado
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO request) {
        Optional<ProductDTO> response = service.update(id, request);

        //Optional -> sempre que colocarmos temos que verificar com o isPresent
        if (response.isPresent()) {
            return ResponseEntity.ok(request);
        }
        //caso der false
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //poderia retornar um Not_Found, porém temos um body, então podemos usar o bad_request pro id tambem

        //Ou podemos eliminar o if e o return e fazer usando lambda
        //return response.map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean inactive = service.inactive(id);
        return inactive
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // NO_CONTENT  -> não retorna nada, sem conteudo
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se nao der certo, da um not_found
    }

}
