/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.game;

import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.maps.GameMap;
import net.flyingprefix.skywars.phase.FriendlyPhase;
import net.flyingprefix.skywars.phase.LobbyPhase;

/**
 *
 * @author canschmitt
 */
public class GameManager {
    
    private GameMap map;
    private GameState gameState = GameState.LOBBY;
    
    private LobbyPhase lobbyPhase;
    private FriendlyPhase friendlyPhase;
    
    public GameManager() {
        this.lobbyPhase = new LobbyPhase();
        this.friendlyPhase = new FriendlyPhase();
    }
    
    

    public GameState getGameState() {
        return gameState;
    }

    public GameMap getMap() {
        return map;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setMap(GameMap map) {
        this.map = map;
        SkyWars.getPlugin().getTeamManager().loadTeams();
    }

    public FriendlyPhase getFriendlyPhase() {
        return friendlyPhase;
    }

    public LobbyPhase getLobbyPhase() {
        return lobbyPhase;
    }
    
    
}
