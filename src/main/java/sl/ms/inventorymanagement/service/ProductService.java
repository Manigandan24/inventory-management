package sl.ms.inventorymanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.ms.inventorymanagement.entity.Product;
import sl.ms.inventorymanagement.entity.ProductDto;
import sl.ms.inventorymanagement.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;

	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	public Product findByProductId(int productId) {
		Optional<Product> product = productRepo.findById(productId);
		if (product.isPresent())
			return product.get();
		else
			return null;
	}

	public void updateProduct(int productId, Product product) {
		Optional<Product> pro = productRepo.findById(productId);
		if (pro.isPresent()) {
			pro.get().setId(product.getId());
			pro.get().setName(product.getName());
			pro.get().setPrice(product.getPrice());
			pro.get().setQuantity(product.getQuantity());

			productRepo.save(pro.get());
		}
	}

	public void deleteProduct(int productId) {
		Optional<Product> pro = productRepo.findById(productId);
		if (pro.isPresent()) {
			pro.get().setQuantity(0);
			productRepo.save(pro.get());
		}
	}

	public List<ProductDto> specificProducts() {
		List<Product> list = productRepo.findAll();
		List<Product> distinctList = productRepo.findAll();
		List<ProductDto> list1 = new ArrayList<>();
		distinctList=list.stream().distinct().collect(Collectors.toList());
		
		distinctList.stream().forEach(a -> {
			ProductDto dto = new ProductDto();
			dto.setId(a.getId());
			dto.setName(a.getName());
			list1.add(dto);
		});

		return list1;
	}
}
