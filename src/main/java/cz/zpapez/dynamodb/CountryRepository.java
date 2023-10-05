package cz.zpapez.dynamodb;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;


@Component
@RequiredArgsConstructor
public class CountryRepository {
    @Getter
    private final DynamoDbTable<CountryEntity> table;

    public List<CountryEntity> getAll() {
        return getTable().scan().items().stream().toList();
    }
}
