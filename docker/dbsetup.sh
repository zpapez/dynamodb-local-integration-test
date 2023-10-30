export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1
unset http_proxy
unset https_proxy

aws dynamodb create-table --table-name Country --attribute-definitions AttributeName=countryCode,AttributeType=S \
  --key-schema AttributeName=countryCode,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://localhost:8000
