package com.game.service;

import com.game.dto.PlayerDTO;
import com.game.exceptions.GameNotFoundException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.payload.LoveGameRequest;
import com.game.payload.PlayerRequest;

import java.util.List;

public interface PlayerService {

    Long createPlayer(PlayerDTO playerDTO);

    PlayerDTO findById(Long id) throws PlayerNotFoundException;

    List<PlayerDTO> getAll();

    void request(LoveGameRequest request);

    List<PlayerDTO> lovedGames();
}