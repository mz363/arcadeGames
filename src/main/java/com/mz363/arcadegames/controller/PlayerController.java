package com.mz363.arcadegames.controller;

import com.mz363.arcadegames.ArcadeGamesApplication;
import com.mz363.arcadegames.exception.ResourceNotFoundException;
import com.mz363.arcadegames.model.Player;
import com.mz363.arcadegames.repository.PlayerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(ArcadeGamesApplication.class);

    @Autowired
    private PlayerRepository repository;

    @GetMapping("/players")
    public List<Player> getAllPLayers() {
        logger.info("Get all the players...");
        return repository.findAll();
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable(value = "id") long playerId) throws ResourceNotFoundException {
        logger.info("Get player by id...");
        Player player = repository.findById(playerId).
                orElseThrow(() -> new ResourceNotFoundException("Player not found for this id:: " + playerId));
        return ResponseEntity.ok().body(player);

    }

    @PostMapping("/players")
    public Player createPlayer(@Valid @RequestBody Player player) {
        logger.info("Insert player...");
        return repository.save(player);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Player> PlayerById(@PathVariable(value = "id") long playerId, @RequestBody Player updatedPlayer) throws ResourceNotFoundException {
        logger.info("Update player...");
        Player player = repository.findById(playerId).
                orElseThrow(() -> new ResourceNotFoundException("Player not found for this id:: " + playerId));
        player.setName(updatedPlayer.getName());
        player.setHighScore(updatedPlayer.getHighScore());
        player.setAttempts(updatedPlayer.getAttempts());
        repository.save(player);
        return ResponseEntity.ok().body(player);

    }

    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable(value = "id") long playerId) throws ResourceNotFoundException {
        logger.info("Delete player...");
        Player player = repository.findById(playerId).
                orElseThrow(() -> new ResourceNotFoundException("Player not found for this id:: " + playerId));
        repository.delete(player);
    }
}