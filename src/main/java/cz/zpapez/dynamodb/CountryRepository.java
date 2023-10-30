package cz.zpapez.dynamodb;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;


@Component
@RequiredArgsConstructor
public class CountryRepository {
    private final DynamoDbTable<CountryEntity> table;

    public List<CountryEntity> getAll() {
        return table.scan().items().stream().toList();
    }
}
