package com.game.service.impl;

import com.game.dto.PlayerDTO;
import com.game.exceptions.PlayerNotFoundException;
import com.game.model.Game;
import com.game.model.Player;
import com.game.payload.LoveGameRequest;
import com.game.repository.GameRepository;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository repository;
    private final GameRepository gameRepository;

    @Override
    public Long createPlayer(PlayerDTO playerDTO) {
        Player model = Player.toModel(playerDTO);
        repository.save(Player.toModel(playerDTO));
        return model.getId();
    }

    @Override
    public PlayerDTO findById(Long id) throws PlayerNotFoundException {
        return repository.findById(id).map(Player::toDTO)
                .orElseThrow(() -> new PlayerNotFoundException(String.format("Player with id %s was not found", id)));
    }

    @Override
    public List<PlayerDTO> getAll() {
        List<Player> players = repository.findAll();
        return players.stream().map(Player::toDTO).collect(Collectors.toList());
    }

    @Override
    public void request(LoveGameRequest request) {
        Game game = gameRepository.getById(request.getGameId());
        Player player = repository.getById(request.getPlayerId());
        player.getLovedGames().add(game);
        repository.save(player);
    }

    @Override
    public List<PlayerDTO> lovedGames() {
        List<Player> players = repository.findAllByLovedGamesIsNotNull();

        return players.stream().map(Player::toDTO).collect(Collectors.toList());
    }

}
