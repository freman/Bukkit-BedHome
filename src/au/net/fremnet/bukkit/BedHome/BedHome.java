/*
    BedHome Bukkit plugin for Minecraft
    Copyright (C) 2011 Shannon Wynter (http://fremnet.net/)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package au.net.fremnet.bukkit.BedHome;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BedHome extends JavaPlugin {
	protected final static Logger	logger			= Logger.getLogger("Minecraft");
	public static final String		name			= "BedHome";

	private final BedHomePL			playerListener	= new BedHomePL(this);
	public final BedHomeLocation	location		= new BedHomeLocation(this);

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		location.Init();

		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_BED_LEAVE, playerListener, Event.Priority.Normal, this);

		log("Version " + pdfFile.getVersion() + " - Copyright 2011 - Shannon Wynter (http://fremnet.net) is enabled");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("home")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Location loc = location.loadLocation(player);
				if (loc == null) {
					sender.sendMessage("You have no home yet, go sleep in a bed");
				}
				else {
					player.teleport(loc);
				}
				return true;
			}
		}
		return false;
	}

	public static void log(String txt) {
		logger.log(Level.INFO, String.format("[%s] %s", name, txt));
	}
}
