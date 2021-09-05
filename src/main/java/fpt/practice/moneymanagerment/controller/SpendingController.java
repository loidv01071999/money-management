package fpt.practice.moneymanagerment.controller;

import fpt.practice.moneymanagerment.dto.SpendingDTO;
import fpt.practice.moneymanagerment.request.SpendingRequest;
import fpt.practice.moneymanagerment.service.SpendingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = -1)
@RequestMapping("/api")
public class SpendingController {

    private static final Logger logger = Logger.getLogger(SpendingController.class);

    @Autowired
    private SpendingService spendingService;

    @GetMapping("/spendings")
    public ResponseEntity<?> getListSpendings() {
        logger.debug("get list spending request: ");
        List<SpendingDTO> spendings = spendingService.getListSpendings();
        ResponseEntity<List<SpendingDTO>> response = new ResponseEntity<>(spendings, HttpStatus.OK);
        return response;
    }

    @PostMapping("/spendings")
    public ResponseEntity<?> addSpending(@RequestBody SpendingRequest spendingRequest) {
        logger.debug("crate a spending: " + spendingRequest);
        spendingService.addSpending(spendingRequest);
        ResponseEntity<String> response = new ResponseEntity<>("Add successful", HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/spendings/{id}")
    public ResponseEntity<?> updateSpending(@PathVariable("id") Long spendingId, @RequestBody SpendingRequest spendingRequest) {
        logger.debug("update spending: " + spendingRequest);
        spendingService.updateSpending(spendingId, spendingRequest);
        ResponseEntity<String> response = new ResponseEntity<>("Update success", HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/spendings/{id}")
    public ResponseEntity<?> removeSpending(@PathVariable("id") Long spendingId) {
        logger.debug("delete spending: " + spendingId);
        spendingService.removeSpending(spendingId);
        ResponseEntity<String> response = new ResponseEntity<>("Delete success", HttpStatus.OK);
        return response;
    }


}
