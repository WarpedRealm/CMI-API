package com.Zrips.CMI.Modules.Jail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import com.Zrips.CMI.Modules.Portals.CuboidArea;
import com.Zrips.CMI.Modules.Portals.CuboidArea.ChunkRef;

import net.Zrips.CMILib.Time.CMITimeManager;

public class JailManager {

    private SortedMap<String, CMIJail> jails;
    protected Map<String, Map<ChunkRef, Set<CMIJail>>> chunkJails;

    private Set<CMIUser> onlineInjail = new HashSet<CMIUser>();

    private int JailCheckInterval = 500;
    private Integer DefaultTime = 500;
    private Integer JailChatRange = 20;
    private boolean CountWhileOffline = false;
    private boolean NoAfk = false;
    private boolean PreventDamage = true;
    private boolean noHunger = true;
    private List<String> cmdsOnUnJail;
    private List<String> cmdsOnJail;
    private List<String> whiteList;
    private CMI plugin;

    public JailManager(CMI plugin) {

    }

    private int sched = -1;

    public void recheckAllOnline() {

    }

    public void stop() {

    }

    private void tasker() {

    }

    public boolean anyJailedOnline() {
        return !onlineInjail.isEmpty();
    }

    public void removeOnline(CMIUser user) {
        if (user == null)
            return;
        onlineInjail.remove(user);
    }

    public void addOnline(CMIUser user) {
        if (user == null)
            return;

        onlineInjail.add(user);
        tasker();
    }

    public void addJail(CMIJail jail) {
        jails.put(jail.getName().toLowerCase(), jail);
        recalculateChunks(jail);
    }

    public void recalculateChunks() {
        chunkJails.clear();
        for (Entry<String, CMIJail> one : this.jails.entrySet()) {
            recalculateChunks(one.getValue());
        }
    }

    public void recalculateChunks(CMIJail jail) {

    }

    public CMIJail getByName(String name) {
        if (name == null)
            return null;
        return jails.get(name.toLowerCase());
    }

    public CMIJail getByLoc(Location loc) {
        return null;
    }

    public CMIJail collidesWithJail(CuboidArea newarea) {
        return collidesWithJail(newarea, null);
    }

    public CMIJail collidesWithJail(CuboidArea newarea, CMIJail ignore) {
        Set<Entry<String, CMIJail>> set = jails.entrySet();
        for (Entry<String, CMIJail> entry : set) {
            CMIJail check = entry.getValue();
            if (check.checkCollision(newarea)) {
                if (ignore != null && ignore.equals(check))
                    continue;
                return entry.getValue();
            }
        }
        return null;
    }

    private static List<ChunkRef> getChunks(CMIJail jail) {
        return jail.getArea().getChunks();
    }

    private static List<ChunkRef> getChunks(CMIJail jail, int range) {
        List<ChunkRef> chunks = new ArrayList<>();
        chunks.addAll(jail.getArea().getChunks(range));
        return chunks;
    }

    public void loadConfig() {

    }

    public void load() {
        Long time = System.currentTimeMillis();
        jails.clear();
        chunkJails.clear();

        File file = new File(plugin.getDataFolder(), "jails.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
            return;
        }

        YamlConfiguration k = null;
        try {
            k = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            return;
        }

        if (k.getKeys(false).isEmpty())
            return;

        Map<String, Object> root = k.getValues(false);

        for (Entry<String, Object> world : root.entrySet()) {
            Map<String, Object> jaillist = k.getValues(false);
            if (jaillist != null) {
                try {
                    loadMap(jaillist);
                } catch (Exception ex) {
                    plugin.sendMessage(Bukkit.getConsoleSender(), ChatColor.RED + "Error in loading jails file for world: " + world.getKey());
                }
            }
        }
        plugin.loadMessage(jails.size(), "jails", System.currentTimeMillis() - time);
    }

    public void loadMap(Map<String, Object> root) throws Exception {

    }

    public void save() {

    }

    public SortedMap<String, CMIJail> getJails() {
        return jails;
    }

    public List<CMIJail> getJailsByDistance(Location loc) {

        List<CMIJail> sortedList = new ArrayList<CMIJail>();

        return sortedList;
    }

    public void removeJail(CMIJail jail) {
        jails.remove(jail.getName().toLowerCase());
        this.recalculateChunks();
        this.save();
    }

    public int getJailsCheckInterval() {
        return JailCheckInterval;
    }

    private boolean isCellOk(CMIJailCell cell) {
        return true;
    }

    private boolean isJailOk(CMIJail jail) {
        if (jail == null)
            return false;
        if (this.getByName(jail.getName()) == null)
            return false;
        return true;
    }

    public CMIJailCell getValidCell(CMIUser user) {
        return getValidCell(user, null, null);
    }

    public CMIJailCell getValidCell(CMIUser user, CMIJail j, Integer cellId) {

        return null;
    }

    public void removePlayerFromJail(CMIUser user) {

    }

    public void placePlayerIntoJail(CMIUser user) {
        placePlayerIntoJail(user, null, null, user.getJailedForTime());
    }

    public boolean placePlayerIntoJail(CMIUser user, CMIJail jail, Integer cellId, Long jailedFor) {

        return true;
    }

    public void informAboutLeftTime(Player player) {
        informAboutLeftTime(plugin.getPlayerManager().getUser(player));
    }

    public void informAboutLeftTime(CMIUser user) {
        if (!user.isJailed())
            return;

        long left = user.getJailedForTime();

        plugin.sendMessage(user, plugin.getIM("jail", "leftTime", "[time]", CMITimeManager.to24hourShort(left)));
    }

    public boolean canUseCommand(String command) {
        command = command.toLowerCase();
        if (command.contains("/"))
            command = command.substring(1);
        for (String one : whiteList) {
            if (command.startsWith(one))
                return true;
        }
        return false;
    }

    public Integer getDefaultTime() {
        return DefaultTime;
    }

    public void setDefaultTime(int defaultTime) {
        DefaultTime = defaultTime;
    }

    public Integer getJailChatRange() {
        return JailChatRange;
    }

    public void setJailChatRange(Integer jailChatRange) {
        JailChatRange = jailChatRange;
    }

    public boolean isNoAfk() {
        return NoAfk;
    }

    public boolean isCountWhileOffline() {
        return CountWhileOffline;
    }

    public void setCountWhileOffline(boolean countWhileOffline) {
        CountWhileOffline = countWhileOffline;
    }

    public boolean isPreventDamage() {
        return PreventDamage;
    }

    public boolean isNoHunger() {
        return noHunger;
    }
}
