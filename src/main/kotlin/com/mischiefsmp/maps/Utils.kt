package com.mischiefsmp.maps

import com.mischiefsmp.maps.commands.ImageMapRenderer
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import java.awt.Image

object Utils {
    fun setMapImage(item: ItemStack, image: Image, world: World) {
        val mapMeta = (item.itemMeta as MapMeta)
        val mapView = if(mapMeta.hasMapView()) mapMeta.mapView!! else MischiefMaps.plugin.server.createMap(world).also {
            mapMeta.mapView = it
            item.itemMeta = mapMeta
        }

        mapView.isLocked = true
        mapView.renderers.forEach { mapView.removeRenderer(it) }
        mapView.addRenderer(ImageMapRenderer(image))
    }
}