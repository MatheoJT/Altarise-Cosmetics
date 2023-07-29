package net.altarise.cm;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public enum CosmeticRarity  {

    COMMON("common", ChatColor.GREEN, "Commun"),
    RARE("rare", ChatColor.DARK_AQUA, "Rare"),
    EPIC("epic", ChatColor.DARK_PURPLE, "Epique"),
    LEGENDARY("legendary", ChatColor.GOLD, "Légendaire"),
    SUPREM("suprem", ChatColor.DARK_RED, "Suprême"),
    EVENT("event", ChatColor.LIGHT_PURPLE, "Evenement"),
    OTHER("other", ChatColor.YELLOW, "Autre");

    private final String id;
    private final ChatColor color;
    private final String name;

    private static final Map<String, CosmeticRarity> rarityById = new HashMap<>();

    static {

        for (CosmeticRarity value : CosmeticRarity.values()) {
            rarityById.put(value.id, value);
        }

    }



    CosmeticRarity(String id, ChatColor color, String name) {
        this.id = id;
        this.color = color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ChatColor getColor() {
        return color;
    }

    public static CosmeticRarity valueOfID(String id) {
        return rarityById.get(id);
    }



}
