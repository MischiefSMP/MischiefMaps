package com.mischiefsmp.maps

import com.mischiefsmp.maps.commands.ImageMapCommand
import com.mischiefsmp.maps.commands.MapCommand
import org.bukkit.plugin.java.JavaPlugin

class MischiefMaps: JavaPlugin() {

    init {
        plugin = this
    }

    override fun onEnable() {
        getCommand("imagemap")!!.setExecutor(ImageMapCommand())
        getCommand("map")!!.setExecutor(MapCommand())
        server.pluginManager.registerEvents(MapListener(), this)
    }

    companion object {
        lateinit var plugin: MischiefMaps
    }
}