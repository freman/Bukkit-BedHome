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

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class BedHomePL extends PlayerListener {
	static BedHome plugin;
	
	public BedHomePL(BedHome parent) {
		plugin = parent;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Location loc = plugin.location.loadLocation(player);
		if (loc != null) {
			player.setCompassTarget(loc);
		}
	}
	
	public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		Player player = event.getPlayer();
		plugin.location.saveLocation(player);
		player.setCompassTarget(player.getLocation());
	}
}
