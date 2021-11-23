package com.game.model;

import com.game.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String playerName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "loved_games",
            joinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Game> lovedGames = new HashSet<>();

    public static Player toModel(PlayerDTO dto) {
        Player model = new Player();
        model.setPlayerName(dto.getPlayerName());
        return model;
    }

    public static PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerName(player.getPlayerName());
        dto.setId(player.getId());
        if (player.getLovedGames() != null) {
            Set<Game> lovedGames = player.getLovedGames();
            dto.setLovedGameDtoList(lovedGames.stream().map(Game::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }
}
