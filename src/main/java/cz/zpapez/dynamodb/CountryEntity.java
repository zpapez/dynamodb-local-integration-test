package cz.zpapez.dynamodb;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
public class CountryEntity {
    public static final String TABLE_NAME = "Country";

    private String countryCode;
    private String countryDisplayName;

    @DynamoDbPartitionKey
    public String getCountryCode() {
        return countryCode;
    }
}
