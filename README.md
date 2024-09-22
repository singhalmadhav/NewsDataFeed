# NewsDataFeed

### Development:

#### Install Dynamo DB in local:
  
`curl -O https://s3.us-west-2.amazonaws.com/dynamodb-local/dynamodb_local_latest.zip`
#### Unzip the folder:
`unzip dynamodb_local_latest.zip`

#### Run the server locally:

`java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb`

#### Updating the dynamo db config to run the application locally

#### Run the below command to see the table data in local
`aws dynamodb scan \
--table-name StoredArticle \
--endpoint-url http://localhost:8000`

