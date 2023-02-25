package com.mischiefsmp.maps

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapCanvas
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

class ImageMapRenderer(private var image: Image): MapRenderer() {
    override fun render(map: MapView, canvas: MapCanvas, player: Player) {
        canvas.drawImage(0, 0, image)
    }
}

object MapManager {
    private val pl = MischiefMaps.plugin
    private val folder = File(pl.dataFolder, "images")

    init {
        folder.mkdirs()
    }

    fun setMapImage(item: ItemStack, url: String, saveOnDisk: Boolean) {
        setMapImage(item, ImageIO.read(URL(url)).getScaledInstance(128, 128, 0), saveOnDisk)
    }

    fun setMapImage(item: ItemStack, image: Image, saveOnDisk: Boolean) {
        setMapImage(ensureMapView(item)!!, image, saveOnDisk)
    }

    fun setMapImage(map: MapView, image: Image, saveOnDisk: Boolean) {
        map.isLocked = true
        map.renderers.forEach { map.removeRenderer(it) }
        map.addRenderer(ImageMapRenderer(image))
        if(saveOnDisk) saveMapImage(map.id, image)
    }

    fun ensureMapView(item: ItemStack): MapView? {
        if(item.type != Material.FILLED_MAP) return null
        val meta = item.itemMeta as MapMeta
        if(!meta.hasMapView()) {
            meta.mapView = pl.server.createMap(pl.server.worlds[0])
            item.itemMeta = meta
        }
        return meta.mapView
    }

    fun getMapImage(mapId: Int): Image? = ImageIO.read(File(folder, "$mapId.png"))

    fun saveMapImage(mapId: Int, image: Image) {
        val saveImage = BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB).also {
            it.graphics.drawImage(image, 0, 0, null)
        }
        ImageIO.write(saveImage, "png", File(folder, "$mapId.png"))
    }
}