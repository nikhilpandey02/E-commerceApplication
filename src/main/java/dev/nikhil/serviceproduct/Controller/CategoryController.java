package dev.nikhil.serviceproduct.Controller;

import dev.nikhil.serviceproduct.Services.CategoryService;
import dev.nikhil.serviceproduct.dtos.GetProductTitlesRequestDto;
import dev.nikhil.serviceproduct.dtos.ProductDto;
import dev.nikhil.serviceproduct.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{uuid}")
    public List<ProductDto> getCategory(@PathVariable("uuid") String uuid) {
        List<Product> products = categoryService.getCategory(uuid).getProducts();

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product: products) {
            ProductDto productDto = new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setTitle(product.getTitle());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            productDtos.add(productDto);
//            productDto.se
        }

        return productDtos;
    }

    @GetMapping("/titles/")
    public List<String> getProductTitles(@RequestBody GetProductTitlesRequestDto requestDto) {

        List<String> uuids = requestDto.getUuids();

        return categoryService.getProductTitles(uuids);
    }
}
