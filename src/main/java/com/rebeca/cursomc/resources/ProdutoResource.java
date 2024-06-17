package com.rebeca.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rebeca.cursomc.domain.Produto;
import com.rebeca.cursomc.repository.ProdutoRepository;

// import io.swagger.annotations.Api;

import java.util.List;

@RestController
@RequestMapping("/produto")
// @Api(tags = "Exemplo", description = "Controlador de Exemplo")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository productRepository;

    @GetMapping("/listaTodos")
    public List<Produto> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/listaUm/{id}")
    public ResponseEntity<Produto> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novoProduto")
    public Produto createProduct(@RequestBody Produto product) {
        return productRepository.save(product);
    }

    @PutMapping("atualiza/{id}")
    public ResponseEntity<Produto> updateProduct(@PathVariable Long id, @RequestBody Produto productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());
                    Produto updatedProduct = productRepository.save(product);
                    return ResponseEntity.ok().body(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    //     return productRepository.findById(id)
    //             .map(product -> {
    //                 productRepository.delete(product);
    //                 return ResponseEntity.ok().build();
    //             })
    //             .orElse(ResponseEntity.notFound().build());
    // }
}

