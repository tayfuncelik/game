package com.game.dto;

import com.game.model.Game;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PlayerDTO {
    private Long id;
    private String playerName;
    private List<GameDTO> lovedGameDtoList;
}
