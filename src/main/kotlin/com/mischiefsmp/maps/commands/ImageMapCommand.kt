package com.mischiefsmp.maps.commands

import com.mischiefsmp.maps.MapManager
import com.mischiefsmp.maps.MischiefMaps
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.net.URL
import javax.imageio.ImageIO

class ImageMapCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(args.isEmpty()) return false
        if(sender is ConsoleCommandSender) return false

        if(sender is Player) {
            Bukkit.getScheduler().runTaskAsynchronously(MischiefMaps.plugin, Runnable {
                if(sender.inventory.itemInMainHand.type != Material.FILLED_MAP) {
                    if(sender.inventory.itemInMainHand.type != Material.AIR) return@Runnable
                    sender.inventory.setItemInMainHand(ItemStack(Material.FILLED_MAP))
                }

                MapManager.setMapImage(sender.inventory.itemInMainHand, args[0], true)

                sender.playSound(sender.location, Sound.ITEM_BOOK_PAGE_TURN, 1F, 1F)

                return@Runnable
            })
        }

        return false
    }
}