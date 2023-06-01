package com.example.wishlist.gateway.api.controller;

import com.example.wishlist.commons.uitl.JsonUtil;
import com.example.wishlist.domain.Wishlist;
import com.example.wishlist.gateway.exceptionhandler.ApiExceptionHandler;
import com.example.wishlist.gateway.mongodb.entity.customer.CustomerEntity;
import com.example.wishlist.gateway.mongodb.entity.product.ProductEntity;
import com.example.wishlist.gateway.mongodb.entity.wishlist.WishlistEntity;
import com.example.wishlist.gateway.mongodb.repository.WishlistRepository;
import com.example.wishlist.usecase.wishlist.add.AddProductWishlistUseCase;
import com.example.wishlist.usecase.wishlist.add.InputAddProductWishlistDto;
import com.example.wishlist.usecase.wishlist.find.FindProductWishlistUseCase;
import com.example.wishlist.usecase.wishlist.list.ListProductsWishlistUseCase;
import com.example.wishlist.usecase.wishlist.remove.RemoveProductWishlistUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles(value = "local")
class WishlistControllerTest {

	private static final String URL = "http://localhost:8080/wishlist";
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	ObjectMapper mapper;
	MockMvc mockMvc;
	WishlistRepository wishlistRepository;
	WishlistController wishlistController;
	AddProductWishlistUseCase addUseCase;
	FindProductWishlistUseCase findUseCase;
	ListProductsWishlistUseCase listUseCase;
	RemoveProductWishlistUseCase removeUseCase;

	public WishlistControllerTest() {
		mapper = new ObjectMapper();
		wishlistRepository = mock(WishlistRepository.class);
		addUseCase = mock(AddProductWishlistUseCase.class);
		findUseCase = mock(FindProductWishlistUseCase.class);
		listUseCase = mock(ListProductsWishlistUseCase.class);
		removeUseCase = mock(RemoveProductWishlistUseCase.class);
		wishlistController = new WishlistController(addUseCase, findUseCase, listUseCase, removeUseCase);
	}

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(wishlistController).setControllerAdvice(new ApiExceptionHandler(messageSource))
				.build();
	}

	@Test
	@DisplayName("Deve criar um produto na wishlist")
	public void shouldReturnStatusCreatedWhenAddProduct() throws Exception {

		var inputAddWishlistDto = inputAddWishlistDtoMock();

		String requestJson = JsonUtil.createRequestJson(inputAddWishlistDto);

		String responseAsString = mockMvc.perform(post(URL+"/product").contentType(APPLICATION_JSON_UTF8)
						.content(requestJson))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		Assertions.assertTrue(responseAsString.length() > 0);
	}

	@Test
	@DisplayName("Deve validar limite máximo de 20 produtos")
	public void shouldReturnStatusBadRequestWhenAttributeRequired() throws Exception {

		var input = inputAddWishlistDtoMock();
		var list = new HashSet<InputAddProductWishlistDto.Product>();

		IntStream.range(0, 21).forEach(i -> {
			var prod1 = new InputAddProductWishlistDto.Product();
			prod1.setPrice(new BigDecimal("100"));
			prod1.setName("TV"+i);
			list.add(prod1);
		});

		input.setProducts(list);

		String requestJson = JsonUtil.createRequestJson(input);

		String responseAsString = mockMvc.perform(post(URL+"/product").contentType(APPLICATION_JSON_UTF8)
						.content(requestJson))
				.andExpect(status().isBadRequest())
				.andReturn().getResponse().getContentAsString();

		Assertions.assertTrue(responseAsString.length() > 0);
	}

	@Test
	@DisplayName("Deve obter um produto com sucesso")
	public void shouldReturnStatusOkWhenFindProduct() throws Exception {

//		when(wishlistRepository.findById("1")).thenReturn(Optional.of(wishlistMock()));
		when(wishlistRepository.save(new WishlistEntity(wishlistDomainMock())).toDomain()).thenReturn(wishlistDomainMock());

		String responseAsString = mockMvc.perform(get(URL + "/{idWishlist}/product/{productName}", 1, "TV").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assertions.assertTrue(responseAsString.length() > 0);
	}

	@Test
	@DisplayName("Deve obter todos os produtos com sucesso")
	public void shouldReturnStatusOkWhenListAllProducts() throws Exception {

		when(wishlistRepository.findById("1")).thenReturn(Optional.of(wishlistMock()));

		String responseAsString = mockMvc.perform(get(URL + "/{idWishlist}/products", 1).contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assertions.assertTrue(responseAsString.length() > 0);
	}

	@Test
	@DisplayName("Deve remover produto com sucesso")
	public void shouldReturnStatusNoContentWhenRemoveProduct() throws Exception {

		when(wishlistRepository.findById("1")).thenReturn(Optional.of(wishlistMock()));
		when(wishlistRepository.existsById("1")).thenReturn(true);

		String responseAsString = mockMvc.perform(delete(URL + "/{idWishlist}/product/{productName}", "1", "TV").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Assertions.assertTrue(responseAsString.length() > 0);
	}

	@Test
	@DisplayName("Deve remover produto com sucesso")
	public void shouldReturnStatusBadRequestWhenRemoveProductNotExists() throws Exception {

		String responseAsString = mockMvc.perform(delete(URL + "/{idWishlist}/product/{productName}", "11", "TV").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		Assertions.assertTrue(responseAsString.length() > 0);
	}

	@DisplayName("Cria mock do input")
	private InputAddProductWishlistDto inputAddWishlistDtoMock() {
		var inputAddWishlistDto = new InputAddProductWishlistDto();
		var inputCustomer = new InputAddProductWishlistDto.Customer();
		inputCustomer.setName("Joao Silva");

		var inputProductDto = new InputAddProductWishlistDto.Product();
		inputProductDto.setName("TV");
		inputProductDto.setPrice(new BigDecimal("2300"));

		inputAddWishlistDto.setCustomer(inputCustomer);
		inputAddWishlistDto.setProducts(Collections.singleton(inputProductDto));
		return  inputAddWishlistDto;
	}

	@DisplayName("Cria mock da wishlist")
	private WishlistEntity wishlistMock() {
		var customer = new CustomerEntity();
		customer.setName("Joao silva");
		var product = new ProductEntity();
		product.setName("TV");
		product.setPrice(new BigDecimal("1200"));
		var list = new ArrayList<ProductEntity>();
		list.add(product);
		var wishlist = new WishlistEntity();
		wishlist.setId("1");
		wishlist.setCustomerEntity(customer);
		wishlist.setProductEntities(list);
		wishlist.setTotal(new BigDecimal("1200"));
		return wishlist;
	}

	@DisplayName("Cria mock da wishlist")
	private Wishlist wishlistDomainMock() {
		var customer = new com.example.wishlist.domain.Customer();
		customer.setName("Joao silva");
		var product = new com.example.wishlist.domain.Product();
		product.setName("TV");
		product.setPrice(new BigDecimal("1200"));
		var list = new ArrayList<com.example.wishlist.domain.Product>();
		list.add(product);
		var wishlist = new Wishlist();
		wishlist.setId("1");
		wishlist.setCustomer(customer);
		wishlist.setProducts(list);
		wishlist.setTotal(new BigDecimal("1200"));
		return wishlist;
	}
}
