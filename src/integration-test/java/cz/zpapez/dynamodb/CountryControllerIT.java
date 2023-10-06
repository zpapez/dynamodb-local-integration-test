package cz.zpapez.dynamodb;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
public class CountryControllerIT extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @RegisterExtension
    static LocalDbCreationRule localDynamoDb = new LocalDbCreationRule();

    @DynamicPropertySource
    static void localDynamoDbProperties(DynamicPropertyRegistry registry) {
        String port = localDynamoDb.getPort();
        registry.add("zpapez.aws.dynamodb.endpoint", () ->
                String.format("http://localhost:%s", port));
    }

    @Autowired
    private DynamoDbTable<CountryEntity> countryTable;

    @BeforeEach
    public void beforeEach() {
        countryTable.createTable();
    }

    @AfterEach
    public void afterEach() {
        countryTable.deleteTable();
    }

    @Test
    void itShouldGetListOfCountries() throws Exception {
        CountryEntity country1 = gson.fromJson(getResourceFileReader("/CountryControllerIT/itShouldGetListOfCountries/countryEntity1.json"),
                CountryEntity.class);
        CountryEntity country2 = gson.fromJson(getResourceFileReader("/CountryControllerIT/itShouldGetListOfCountries/countryEntity2.json"),
                CountryEntity.class);

        countryTable.putItem(country1);
        countryTable.putItem(country2);

        mvc.perform(get("/api/v1/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].countryCode", is("BR")))
                .andExpect(jsonPath("$.[0].countryDisplayName", is("Brazil")))

                .andExpect(jsonPath("$.[1].countryCode", is("US")))
                .andExpect(jsonPath("$.[1].countryDisplayName", is("United States")));
    }
}
