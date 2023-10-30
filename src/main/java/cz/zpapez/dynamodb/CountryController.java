package cz.zpapez.dynamodb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping
    public List<CountryEntity> getAll() {
        return service.getAll();
    }
}