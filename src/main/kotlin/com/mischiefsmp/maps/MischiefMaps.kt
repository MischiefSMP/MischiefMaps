package com.mischiefsmp.maps

import com.mischiefsmp.maps.commands.ImageMapCommand
import org.bukkit.plugin.java.JavaPlugin

class MischiefMaps: JavaPlugin() {

    init {
        plugin = this
    }

    override fun onEnable() {
        getCommand("imagemap")!!.setExecutor(ImageMapCommand())
    }

    companion object {
        lateinit var plugin: MischiefMaps
    }
}