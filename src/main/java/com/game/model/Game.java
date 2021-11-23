package com.game.model;

import com.game.dto.GameDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "lovedGames", fetch = FetchType.LAZY)
    private Set<Player> players = new HashSet<>();

    public static Game toModel(GameDTO dto) {
        Game model = new Game();
        model.setName(dto.getGameName());
        return model;
    }

    public static GameDTO toDTO(Game model) {
        GameDTO dto = new GameDTO();
        dto.setGameName(model.getName());
        dto.setId(model.getId());
        return dto;
    }
}
