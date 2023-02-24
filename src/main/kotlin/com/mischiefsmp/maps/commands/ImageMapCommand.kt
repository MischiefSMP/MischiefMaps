package com.mischiefsmp.maps.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.map.MapCanvas
import org.bukkit.map.MapRenderer
import org.bukkit.map.MapView
import java.awt.Image
import java.net.URL
import javax.imageio.ImageIO

class ImageMapRenderer(var image: Image): MapRenderer() {
    override fun render(map: MapView, canvas: MapCanvas, player: Player) {
        canvas.drawImage(0, 0, image)
    }
}

class ImageMapCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(args.isEmpty()) return false
        if(sender is ConsoleCommandSender) return false

        if(sender is Player) {
            val image = ImageIO.read(URL(args[0]))

            sender.server.createMap(sender.world).also { mapView ->
                mapView.scale = MapView.Scale.FARTHEST
                mapView.isLocked = true
                mapView.addRenderer(ImageMapRenderer(image.getScaledInstance(2048, 2048, 0)))

                ItemStack(Material.FILLED_MAP).also { item ->
                    item.itemMeta = (item.itemMeta as MapMeta).also { meta ->
                        meta.mapView = mapView
                        meta.isScaling = true
                    }
                    sender.inventory.addItem(item)
                }
                sender.sendMap(mapView)
            }

            return true
        }

        return false
    }
}