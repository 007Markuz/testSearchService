package integration.com.test.searchService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.test.searchService.SearchServiceApplication;
import com.test.searchService.domain.service.ProductsService;
import com.test.searchService.domain.service.ProductsServiceImpl;
import com.test.searchService.presentation.endpoints.ProductsControllers;
import com.test.searchService.presentation.response.ProductResponse;
import com.test.searchService.presentation.response.ProductsResponse;
import java.net.URI;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(
    classes = SearchServiceApplication.class,
    webEnvironment = DEFINED_PORT)
@ContextConfiguration(classes = ProductsServiceImpl.class)
public class ProductsIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @MockBean
  private ProductsServiceImpl productsService ;

  @BeforeEach
  public void init() {
    ProductsResponse productsResponseUne = new ProductsResponse();
    productsResponseUne.products = new ArrayList<ProductResponse>();
    productsResponseUne.products.add(getProduct(1, "foo", 100,0));
    when(productsService.getProducts("foo")).thenReturn(productsResponseUne);
  }

  @Test
  public void corsWithAnnotation() throws Exception {

    ResponseEntity<ProductsResponse> entity = this.restTemplate.exchange(
        RequestEntity.get(uri("/products?key=foo")).header(HttpHeaders.ORIGIN, "https://test-search-web.herokuapp.com/").build(),
        ProductsResponse.class);

    Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
    Assertions.assertEquals("*", entity.getHeaders().getAccessControlAllowOrigin());
    ProductsResponse response = entity.getBody();
    Assertions.assertEquals(1, response.products.size());
  }
  private URI uri(String path) {
    return restTemplate.getRestTemplate().getUriTemplateHandler().expand(path);
  }
  private ProductResponse getProduct(int id, String brand, double price,int discount){

    return ProductResponse.builder()
        .id(id)
        .brand(brand)
        .description(brand)
        .image(brand)
        .price(price)
        .discount(discount)
        .build();
  }
}
