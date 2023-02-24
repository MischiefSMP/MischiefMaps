package com.mischiefsmp.maps.commands

import com.mischiefsmp.maps.Utils
import org.bukkit.Material
import org.bukkit.Sound
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
            if(sender.inventory.itemInMainHand.type != Material.FILLED_MAP) {
                if(sender.inventory.itemInMainHand.type != Material.AIR) return false
                sender.inventory.setItemInMainHand(ItemStack(Material.FILLED_MAP))
            }

            val image = ImageIO.read(URL(args[0])).getScaledInstance(128, 128, 0)
            Utils.setMapImage(sender.inventory.itemInMainHand, image, sender.world)

            sender.playSound(sender.location, Sound.ITEM_BOOK_PAGE_TURN, 1F, 1F)

            return true
        }

        return false
    }
}