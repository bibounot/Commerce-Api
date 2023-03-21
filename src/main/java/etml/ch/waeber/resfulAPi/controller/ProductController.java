package etml.ch.waeber.resfulAPi.controller;

import etml.ch.waeber.resfulAPi.DAO.ItemDAO;
import etml.ch.waeber.resfulAPi.DAO.ClientDAO;
import etml.ch.waeber.resfulAPi.controller.model.ProductID;
import etml.ch.waeber.resfulAPi.model.ClientEntity;
import etml.ch.waeber.resfulAPi.model.ItemEntity;
import etml.ch.waeber.resfulAPi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private ClientDAO clientDAO;

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/product")
    public ResponseEntity<ProductID> createProduct(@RequestBody final Product product) {
        System.out.println(product);
        ProductID productID = new ProductID(UUID.randomUUID().toString());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productID);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemEntity>> getAllItemAndCLient() {
            List<ItemEntity> itemEntities = itemDAO.findAll();
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(itemEntities);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemEntity> getProductByID(@PathVariable final String id) {
        ItemEntity itemEntity = itemDAO.findById(Integer.parseInt(id)).get();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemEntity);
    }

    //create new client with firstname in the body http
    @PostMapping("/client")
    public ResponseEntity<ClientEntity> createClient(@RequestBody final String firstname) {
        ClientEntity clientEntity = new ClientEntity();

        List<ClientEntity> clientEntities = clientDAO.findAll();
        for (ClientEntity client : clientEntities) {
            if (client.getPrenom().equals(firstname)) {
                //code erreur client existe déjà
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(clientEntity);
            }
        }
        clientEntity.setPrenom(firstname);
        clientEntity.setSolde(BigDecimal.valueOf(50));
        clientDAO.save(clientEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(clientEntity);
    }



    @GetMapping("/client/{id}")
    public ResponseEntity<ClientEntity> getClientByID(@PathVariable final String id) {
        ClientEntity clientEntity = clientDAO.findById(Integer.parseInt(id)).get();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(clientEntity);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientEntity>> getAllClient() {

        List<ClientEntity> clientEntities = clientDAO.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(clientEntities);
    }
}
