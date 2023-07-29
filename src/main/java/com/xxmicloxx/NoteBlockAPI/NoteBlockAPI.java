package com.xxmicloxx.NoteBlockAPI;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.scheduler.BukkitWorker;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Main class; contains methods for playing and adjusting songs for players
 */
public class NoteBlockAPI {

	private static NoteBlockAPI noteBlockAPI;
	private final Plugin plugin;
	private Map<UUID, ArrayList<SongPlayer>> playingSongs = new ConcurrentHashMap<UUID, ArrayList<SongPlayer>>();
	private Map<UUID, Byte> playerVolume = new ConcurrentHashMap<UUID, Byte>();
	private boolean disabling = false;
	private HashMap<Plugin, Boolean> dependentPlugins = new HashMap<>();

	public NoteBlockAPI(Plugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Returns true if a Player is currently receiving a song
	 *
	 * @param player
	 * @return is receiving a song
	 */
	public static boolean isReceivingSong(Player player) {
		return isReceivingSong(player.getUniqueId());
	}

	/**
	 * Returns true if a Player with specified UUID is currently receiving a song
	 *
	 * @param uuid
	 * @return is receiving a song
	 */
	public static boolean isReceivingSong(UUID uuid) {
		ArrayList<SongPlayer> songs = noteBlockAPI.playingSongs.get(uuid);
		return (songs != null && !songs.isEmpty());
	}

	/**
	 * Stops the song for a Player
	 *
	 * @param player
	 */
	public static void stopPlaying(Player player) {
		stopPlaying(player.getUniqueId());
	}

	/**
	 * Stops the song for a Player
	 *
	 * @param uuid
	 */
	public static void stopPlaying(UUID uuid) {
		ArrayList<SongPlayer> songs = noteBlockAPI.playingSongs.get(uuid);
		if (songs == null) {
			return;
		}
		for (SongPlayer songPlayer : songs) {
			songPlayer.removePlayer(uuid);
		}
	}

	/**
	 * Sets the volume for a given Player
	 *
	 * @param player
	 * @param volume
	 */
	public static void setPlayerVolume(Player player, byte volume) {
		setPlayerVolume(player.getUniqueId(), volume);
	}

	/**
	 * Sets the volume for a given Player
	 *
	 * @param uuid
	 * @param volume
	 */
	public static void setPlayerVolume(UUID uuid, byte volume) {
		noteBlockAPI.playerVolume.put(uuid, volume);
	}

	/**
	 * Gets the volume for a given Player
	 *
	 * @param player
	 * @return volume (byte)
	 */
	public static byte getPlayerVolume(Player player) {
		return getPlayerVolume(player.getUniqueId());
	}

	/**
	 * Gets the volume for a given Player
	 *
	 * @param uuid
	 * @return volume (byte)
	 */
	public static byte getPlayerVolume(UUID uuid) {
		Byte byteObj = noteBlockAPI.playerVolume.get(uuid);
		if (byteObj == null) {
			byteObj = 100;
			noteBlockAPI.playerVolume.put(uuid, byteObj);
		}
		return byteObj;
	}

	public static ArrayList<SongPlayer> getSongPlayersByPlayer(Player player) {
		return getSongPlayersByPlayer(player.getUniqueId());
	}

	public static ArrayList<SongPlayer> getSongPlayersByPlayer(UUID player) {
		return noteBlockAPI.playingSongs.get(player);
	}

	public static void setSongPlayersByPlayer(Player player, ArrayList<SongPlayer> songs) {
		setSongPlayersByPlayer(player.getUniqueId(), songs);
	}

	public static void setSongPlayersByPlayer(UUID player, ArrayList<SongPlayer> songs) {
		noteBlockAPI.playingSongs.put(player, songs);
	}

	public static NoteBlockAPI getAPI() {
		return noteBlockAPI;
	}

	public void onEnable() {
		noteBlockAPI = this;

		for (Plugin pl : plugin.getServer().getPluginManager().getPlugins()) {
			if (pl.getDescription().getDepend().contains("NoteBlockAPI") || pl.getDescription().getSoftDepend().contains("NoteBlockAPI")) {
				dependentPlugins.put(pl, false);
			}
		}


		new NoteBlockPlayerMain().onEnable();

		plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {

			@Override
			public void run() {
				Plugin[] plugins = plugin.getServer().getPluginManager().getPlugins();
				Type[] types = new Type[]{PlayerRangeStateChangeEvent.class, SongDestroyingEvent.class, SongEndEvent.class, SongStoppedEvent.class};
				for (Plugin plugin : plugins) {
					ArrayList<RegisteredListener> rls = HandlerList.getRegisteredListeners(plugin);
					for (RegisteredListener rl : rls) {
						Method[] methods = rl.getListener().getClass().getDeclaredMethods();
						for (Method m : methods) {
							Type[] params = m.getParameterTypes();
							param:
							for (Type paramType : params) {
								for (Type type : types) {
									if (paramType.equals(type)) {
										dependentPlugins.put(plugin, true);
										break param;
									}
								}
							}
						}

					}
				}

			}
		}, 1);

	}

	public void onDisable() {
		disabling = true;
		Bukkit.getScheduler().cancelTasks(plugin);
		List<BukkitWorker> workers = Bukkit.getScheduler().getActiveWorkers();
		for (BukkitWorker worker : workers) {
			if (!worker.getOwner().equals(this))
				continue;
			worker.getThread().interrupt();
		}
		NoteBlockPlayerMain.plugin.onDisable();
	}

	public void doSync(Runnable runnable) {
		plugin.getServer().getScheduler().runTask(plugin, runnable);
	}

	public void doAsync(Runnable runnable) {
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
	}

	public boolean isDisabling() {
		return disabling;
	}

	protected void handleDeprecated(StackTraceElement[] ste) {
		int pom = 1;
		String clazz = ste[pom].getClassName();
		while (clazz.startsWith("com.xxmicloxx.NoteBlockAPI")) {
			pom++;
			clazz = ste[pom].getClassName();
		}
		String[] packageParts = clazz.split("\\.");
		ArrayList<Plugin> plugins = new ArrayList<Plugin>();
		plugins.addAll(dependentPlugins.keySet());

		ArrayList<Plugin> notResult = new ArrayList<Plugin>();
		parts:
		for (int i = 0; i < packageParts.length - 1; i++) {

			for (Plugin pl : plugins) {
				if (notResult.contains(pl)) {
					continue;
				}
				if (plugins.size() - notResult.size() == 1) {
					break parts;
				}
				String[] plParts = pl.getDescription().getMain().split("\\.");
				if (!packageParts[i].equalsIgnoreCase(plParts[i])) {
					notResult.add(pl);
					continue;
				}
			}
			plugins.removeAll(notResult);
			notResult.clear();
		}

		plugins.removeAll(notResult);
		notResult.clear();
		if (plugins.size() == 1) {
			dependentPlugins.put(plugins.get(0), true);
		}
	}

}
