import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase database = mongoClient.getDatabase("test");                         //immutable
        MongoCollection<Document> coll = database.getCollection("myTestCollection");      //immutable


        //document validation
        ValidationOptions collOptions = new ValidationOptions().validator(
                Filters.or(Filters.exists("email"), Filters.exists("phone")));
        database.createCollection("contacts",
                new CreateCollectionOptions().validationOptions(collOptions));


    }
}
