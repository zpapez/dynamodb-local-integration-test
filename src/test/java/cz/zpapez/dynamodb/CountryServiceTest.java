package cz.zpapez.dynamodb;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @Test
    void itShouldGetAllCountries() {
        List<CountryEntity> countries = new ArrayList<>();
        when(countryRepository.getAll()).thenReturn(countries);

        List<CountryEntity> result = countryService.getAll();

        assertEquals(countries, result);
    }
}
