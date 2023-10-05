package cz.zpapez.dynamodb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository repository;

    public List<CountryEntity> getAll() {
        return repository.getAll();
    }
}
