package org.example.webapp;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "mycollection")
public class Products {

    @Id
    private String id;
    private String name;

    // Getters and Setters
}
