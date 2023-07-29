package net.altarise.cm.musics;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import net.altarise.cm.Cosmetics;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

public class SongManager {


    private static final HashMap<Player, RadioSongPlayer> playerSong = new HashMap<>();

    public static void playSong(Player player, Music music) {
        if (playerSong.containsKey(player)) {
            stopSong(player);
        }

        final File file = new File(Cosmetics.INSTANCE().getDataFolder() + File.separator + "music", music.getFileName() + ".nbs");

        if (!file.exists()) {
            player.sendMessage("§cMusique §f" + music.getName() + " §cintrouvable. Merci de le signaler à un membre du staff.");
            Cosmetics.INSTANCE().getLogger().log(Level.WARNING, "Error to load a music: " + music.getName());
            return;
        }

        Song song = NBSDecoder.parse(file);
        RadioSongPlayer songPlayer = new RadioSongPlayer(song);
        songPlayer.addPlayer(player);
        songPlayer.setPlaying(true);
        songPlayer.setVolume((byte) 50);
        playerSong.put(player, songPlayer);
    }

    public static void stopSong(Player player) {
        if (playerSong.containsKey(player)) {
            playerSong.get(player).setPlaying(false);
            playerSong.get(player).destroy();
            playerSong.remove(player);
        }
    }


    public static void volumeUp(Player player, int volume) {
        if (playerSong.containsKey(player)) {
            playerSong.get(player).setVolume((byte) (playerSong.get(player).getVolume() + volume));
        }

    }

    public static void volumeDown(Player player, int volume) {
        if (playerSong.containsKey(player)) {
            playerSong.get(player).setVolume((byte) (playerSong.get(player).getVolume() - volume));
        }
    }

    public static byte getVolume(Player player) {
        if (playerSong.containsKey(player)) {
            return playerSong.get(player).getVolume();
        }
        return 0;
    }


}
