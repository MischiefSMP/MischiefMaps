package com.mischiefsmp.maps

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.MapInitializeEvent

class MapListener: Listener {

    @EventHandler
    fun mapInitialize(event: MapInitializeEvent) {
        val img = MapManager.getMapImage(event.map.id)
        if(img != null) MapManager.setMapImage(event.map, img, false)
    }
}