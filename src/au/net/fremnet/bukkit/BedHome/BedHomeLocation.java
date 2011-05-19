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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BedHomeLocation {
	static BedHome plugin;
	static File dataFolder;
	
	public BedHomeLocation(BedHome parent) {
		plugin = parent;
	}
	
	public void Init() {
		dataFolder = plugin.getDataFolder();
		if (!dataFolder.exists()) {
			dataFolder.mkdir();
		}
	}

	private File getWorld(Player player) {
		File world = new File(dataFolder.getAbsolutePath() + File.separator + player.getWorld().getName());
		if (!world.exists()) {
			world.mkdir();
		}
		return world;
	}
	
	public void saveLocation(Player player) {
		Location loc = player.getLocation();
		saveLocation(player, loc);
	}
	
	public void saveLocation(Player player, Location location) {
		File save = new File(getWorld(player).getAbsoluteFile() + File.separator + player.getName());
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(save)));
			bw.write(String.format("%f,%f,%f", location.getX(), location.getY(), location.getZ()));
			bw.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Location loadLocation(Player player) {
		File save = new File(getWorld(player).getAbsoluteFile() + File.separator + player.getName());
		if (!save.exists()) {
			return null;
		}
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(save)));
			String inputLine = br.readLine();
			if (inputLine == null) {
				return null;
			}
			
			String splits[] = inputLine.split(",", 3);
			return new Location(player.getWorld(), Double.parseDouble(splits[0]), Double.parseDouble(splits[1]), Double.parseDouble(splits[2]));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
}
