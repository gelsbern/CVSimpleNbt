package org.cubeville.simplenbt.commands.armor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.cubeville.commons.commands.Command;
import org.cubeville.commons.commands.CommandExecutionException;
import org.cubeville.commons.commands.CommandParameterColor;
import org.cubeville.commons.commands.CommandResponse;

public class ArmorColor extends Command {
    
    public ArmorColor() {
        super("armor color");
        addFlag("add");
        addBaseParameter(new CommandParameterColor());
    }
    
    @Override
    public CommandResponse execute(Player player, Set<String> flags, Map<String, Object> parameters, List<Object> baseParameters) 
        throws CommandExecutionException {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null || item.getType() == Material.AIR) {
            throw new CommandExecutionException("&cMust be holding &6leather armor&c!");
        } else if (!(item.getItemMeta() instanceof LeatherArmorMeta)) {
            throw new CommandExecutionException("&cMust be holding &6leather armor&c!");
        }

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        Color newCol = (Color) baseParameters.get(0);
        if(flags.contains("add")) {
            meta.setColor(meta.getColor().mixColors(newCol));
        }
        else {
            meta.setColor(newCol);
        }
        item.setItemMeta(meta);
        if(flags.contains("add")) {
            return new CommandResponse("&aColor &6" + ((Color) baseParameters.get(0)).toString() + "&a added to armor color.");
        }
        else {
            return new CommandResponse("&aArmor color changed to &6" + ((Color) baseParameters.get(0)).toString());
        }
    }
}
