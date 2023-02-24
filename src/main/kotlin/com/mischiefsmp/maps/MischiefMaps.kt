package com.mischiefsmp.maps

import org.bukkit.plugin.java.JavaPlugin

class MischiefMaps: JavaPlugin() {

    init {
        plugin = this
    }

    companion object {
        lateinit var plugin: MischiefMaps
    }
}