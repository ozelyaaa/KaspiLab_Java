package kz.testandpractice.project5.controllers;

import jakarta.validation.Valid;
import kz.testandpractice.project5.models.ProductDTO;
import kz.testandpractice.project5.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<ProductDTO> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') and hasAuthority('SCOPE_read')")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ProductDTO saveProduct(@Valid @RequestBody ProductDTO productDTO, String address) {
        return productService.saveProduct(productDTO, address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
                                                 @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN) // Explicitly set content type
                .body("Successfully deleted!");
    }
}