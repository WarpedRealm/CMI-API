package com.Zrips.CMI.utils;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.Zrips.CMILib.Chat.listMoveDirection;

public class ListEditor {

    public ListEditor() {
    }

    public static List<String> move(List<String> list, int place, listMoveDirection direction) {
        return null;
    }

    public static boolean showCommandList(String[] args, Player player, String cmd, String name, List<String> list) {
        return showCommandList(args, player, cmd, name, list, true);
    }

    public static boolean showCommandList(String[] args, Player player, String cmd, String name, List<String> list, boolean ShowAdd) {
        return showCommandList(args, player, cmd, name, list, ShowAdd, "commands");
    }

    public static boolean showCommandList(String[] args, Player player, String cmd, String name, List<String> list, boolean ShowAdd, String subCommand) {
        return false;
    }

    public static boolean showListForEditing(Player player, String cmd, String name, List<String> list, boolean ShowAdd) {
        return showListForEditing(player, cmd, name, list, ShowAdd, false);
    }

    public static boolean showListForEditing(Player player, String cmd, String name, List<String> list, boolean ShowAdd, boolean fillEmpty) {

        return true;
    }

    public static boolean processEditingList(String[] args, Player player, String cmd, String name, List<String> list, boolean add, String subCommand) {

        return false;
    }

    public static boolean processEditListClean(String[] args, Player player, String cmd, String name, List<String> list, boolean add, String subCommand) {

        return false;
    }

    public static boolean processForCommandEditing(String[] args, Player player, String cmd, String name, List<String> list) {
        return processForCommandEditing(args, player, cmd, name, list, true);
    }

    public static boolean processForCommandEditing(String[] args, Player player, String cmd, String name, List<String> list, boolean add) {
        return processForCommandEditing(args, player, cmd, name, list, add, null);
    }

    public static boolean processForCommandEditing(String[] args, Player player, String cmd, String name, List<String> list, boolean add, String subCommand) {

        return false;
    }

    public enum listEditorType {
        text, commands;
    }

    public static boolean processListEditing(String[] args, CommandSender sender, String cmd, String name, List<String> list, boolean add, String subCommand, boolean fillEmpty, listEditorType type) {

        return false;
    }
}
