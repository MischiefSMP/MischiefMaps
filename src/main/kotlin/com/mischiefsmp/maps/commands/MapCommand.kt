package com.mischiefsmp.maps.commands

import com.mischiefsmp.maps.MischiefMaps
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class MapCommand: CommandExecutor {
    private val pl = MischiefMaps.plugin

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return true
    }
}