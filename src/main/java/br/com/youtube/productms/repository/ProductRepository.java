package br.com.youtube.productms.repository;

import br.com.youtube.productms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Quem acessa o repository ?
 * Service
 * Controller acessa Service e Service acessa repository
 * */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
