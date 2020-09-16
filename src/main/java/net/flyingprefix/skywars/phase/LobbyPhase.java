/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.phase;

import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.game.GameState;
import net.flyingprefix.skywars.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author canschmitt
 */
public class LobbyPhase extends BukkitRunnable {

    private int timeLeft = 60;

    private boolean started = false;

    private TeamManager teamManager;

    public LobbyPhase(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    @Override
    public void run() {
        if (!started) {
            started = true;
        }
        timeLeft--;

        if (timeLeft <= 0) {
            setStarted(false);
            cancel();

            assignTeams();

            SkyWars.getPlugin().getGameManager().setGameState(GameState.FRIENDLY);
            SkyWars.getPlugin().getGameManager().getFriendlyPhase().runTaskTimer(SkyWars.plugin, 20, 20);
            return;
        }

        if (timeLeft == 50 || timeLeft == 40 || timeLeft == 30 || timeLeft == 20 || timeLeft == 10 || timeLeft == 5 || timeLeft == 4 || timeLeft == 3 || timeLeft == 2 || timeLeft == 1) {
            Bukkit.broadcastMessage(SkyWars.prefix + "§7Die Runde beginnt in §e" + timeLeft + (timeLeft != 1 ? " §7Sekunden" : " §7Sekunde"));
        }

    }

    public void assignTeams() {
        for (Player no : teamManager.getPlayersWithNoTeam()) {
            teamManager.setTeam(no, teamManager.getLowestTeam());
        }

        if (teamManager.getTeamsWithPlayers() == 1 || teamManager.unfairTeams()) {
            Bukkit.broadcastMessage(SkyWars.prefix + "§cDie Teams sind unfair und werden neu aufgeteilt");

            Bukkit.getOnlinePlayers().forEach(all -> {
                teamManager.setTeam(all, teamManager.getLowestTeam());
            });
        }
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

}
