package sl.ms.inventorymanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import sl.ms.inventorymanagement.entity.Product;
import sl.ms.inventorymanagement.entity.ProductDto;
import sl.ms.inventorymanagement.repository.ProductRepo;
import sl.ms.inventorymanagement.service.InventoryService;
import sl.ms.inventorymanagement.service.ProductService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ServiceTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	ProductService service;
	
	InventoryService invent;
	
	@MockBean
	ProductRepo productRepo;
	
	File file;
	
	@BeforeEach
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		file=Paths.get("src", "test","resources","sample.csv").toFile();
		
		invent=Mockito.mock(InventoryService.class);
		
		//service=Mockito.mock(ProductService.class);
	}
	
	@Test
	void addInventoryTest() {
		InventoryService mockInvent=Mockito.mock(InventoryService.class);
		Product product = new Product();
		product.setId(1);
		product.setName("Item1");
		product.setPrice(120.12);
		product.setQuantity(10);
		
		Mockito.doNothing().when(mockInvent).addInventory(Mockito.isA(Integer.class), Mockito.isA(Product.class));
		mockInvent.addInventory(1, product);
		
		Mockito.verify(mockInvent).addInventory(1, product);
	}
	
	@Test
	void addInventoryListTest() throws Exception {
		
		List<Product> list =new ArrayList<>();
		Product product = new Product();
		product.setId(1);
		product.setName("Item1");
		product.setPrice(120.12);
		product.setQuantity(10);
		
		
		Mockito.doNothing().when(invent).addInventoryList(ArgumentMatchers.anyList());
		invent.addInventoryList(list);
		
		
		Mockito.verify(invent).addInventoryList(list);
	}
	
	@Test
	void addInventoryFileTest() throws Exception {
		file = Paths.get("src", "test", "resources", "sample.csv").toFile();
		FileInputStream input = new FileInputStream(file);
		MockMultipartFile file1 = new MockMultipartFile("file",input);
		
		Mockito.doNothing().when(invent).addInventoryFile(file1);
		invent.addInventoryFile(file1);
		Mockito.verify(invent).addInventoryFile(file1);
		
	}
	
	
	@Test
	public void getProductsTest() throws Exception {
		//addInventoryFileTest();
		//service=Mockito.mock(ProductService.class);
		
		ProductRepo mockRepo=Mockito.mock(ProductRepo.class);
		
		List<Product> expected=new ArrayList<>();
		Product mockorder=new Product();
		
		mockorder.setName("Item1");
		mockorder.setPrice(120.1);
		mockorder.setQuantity(10);
		mockorder.setId(1);
		
		expected.add(mockorder);
		
		Mockito.doReturn(expected).when(productRepo).findAll();
		
		//Mockito.doReturn(expected).when(service).getProducts();
		
		List<Product> actual=service.getProducts();
		assertNotNull(actual);
		assertEquals(expected,actual);
	}
	
	@Test
	void findByProductId() {
		//service=Mockito.mock(ProductService.class);
		
		//ProductRepo mockRepo=Mockito.mock(ProductRepo.class);
		
		Object mockObject;
		
		Product mockorder=new Product();
		
		mockorder.setName("Item1");
		mockorder.setPrice(120.1);
		mockorder.setQuantity(10);
		mockorder.setId(1);
		
		mockObject=mockorder;
		
		Mockito.doReturn(Optional.of(mockorder)).when(productRepo).findById(Mockito.anyInt());
		
		
		//Mockito.doReturn(mockObject).when(service).findByProductId(Mockito.anyInt());
		//Mockito.when(orderRepo.findById(Mockito.anyInt())).thenReturn(opt);
		Optional<Object> result=Optional.of(service.findByProductId(1));
		Product p=(Product)result.get();
		assertNotNull(service.findByProductId(1));
		assertSame("Item1",p.getName());
	}
	
	@Test
	void updateProductTest() {
		service=Mockito.mock(ProductService.class);
		
		ProductRepo mockRepo=Mockito.mock(ProductRepo.class);
		
		Product mockProduct=new Product();
		
		mockProduct.setName("Item1");
		mockProduct.setPrice(120.1);
		mockProduct.setQuantity(10);
		mockProduct.setId(1);
		
		Mockito.doReturn(Optional.of(mockProduct)).when(productRepo).findById(Mockito.anyInt());
		
		Mockito.doReturn(mockProduct).when(productRepo).save(mockProduct);
		
		Mockito.doNothing().when(service).updateProduct(Mockito.anyInt(), Mockito.isA(Product.class));
		service.updateProduct(1,mockProduct);
		
		Mockito.verify(service).updateProduct(1,mockProduct);
		
	}
	
	@Test
	public void deleteProductTest() {
		service=Mockito.mock(ProductService.class);
		
		ProductRepo mockRepo=Mockito.mock(ProductRepo.class);
		
		Product mockorder=new Product();
		
		mockorder.setName("Item1");
		mockorder.setPrice(120.1);
		mockorder.setQuantity(10);
		mockorder.setId(1);
		
		Mockito.doReturn(Optional.of(mockorder)).when(mockRepo).findById(Mockito.anyInt());
		mockorder.setQuantity(0);
		Mockito.doReturn(mockorder).when(mockRepo).save(mockorder);
		
		Mockito.doNothing().when(service).deleteProduct(Mockito.anyInt());
		service.deleteProduct(1);
		
		Mockito.verify(service).deleteProduct(1);
		
	}
	
	@Test
	public void specificProductsTest() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(df);
		
		file=Paths.get("src", "test","resources","products_request.json").toFile();
		/*
		 * List<Product> expected=new ArrayList<>(); Product mockorder=new Product();
		 * 
		 * mockorder.setName("Item1"); mockorder.setPrice(120.1);
		 * mockorder.setQuantity(10); mockorder.setId(1);
		 * 
		 * expected.add(mockorder);
		 */
		Product[] products = mapper.readValue(file, Product[].class);
		
		List<Product> distinctList = new ArrayList<>();
		List<Product> productList = List.of(products);
		
		List<ProductDto> dtoList = new ArrayList<>();
		distinctList=productList.stream().distinct().collect(Collectors.toList());
		
		distinctList.stream().forEach(a -> {
			ProductDto dto = new ProductDto();
			dto.setId(a.getId());
			dto.setName(a.getName());
			dtoList.add(dto);
		});
		
		
		Mockito.doReturn(productList).when(productRepo).findAll();
		
		//Mockito.when(productRepo.findAll()).thenReturn(productList);
		
		//Mockito.doReturn(dtoList).when(service).specificProducts();
		
		List<ProductDto> expected  = service.specificProducts();
		
		assertNotNull(dtoList);
		assertEquals(expected,dtoList);
		
	}
}
