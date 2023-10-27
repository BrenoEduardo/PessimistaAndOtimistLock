package iftm.pessimisticlock.controllers;

import iftm.pessimisticlock.models.item;
import iftm.pessimisticlock.models.dto.SockItemDto;
import iftm.pessimisticlock.repositories.ItemRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessimitLock")
public class SockController {

    @Autowired
    private ItemRepository repository;

    @PostMapping("/item/{id}/update")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<?> UpdateItemQuantity(
        @PathVariable Long id,
        @RequestParam int incrementAmount) {

        final item item = repository.findById(id);

        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found with ID " + id);
        }

        int currentQuantity = item.getQuant();
        int updatedQuantity = incrementAmount + currentQuantity;

        if (updatedQuantity < 0) {
            return ResponseEntity.badRequest().body("Updated quantity cannot be negative");
        }

        item.setQuant(updatedQuantity);
        repository.save(item);

        return ResponseEntity.ok("Successfully updated quantity for item with ID " + id + " to " + updatedQuantity);
    }
     @PostMapping("/item")
    public ResponseEntity<?> createSockItem(@RequestBody final SockItemDto request) {
        // create a new item
        final item item = request.toSockItem();

        repository.save(item);

        return ResponseEntity.ok("Successfully created quantity for new item with ID " + item.getId());
    }


}
