package org.example

import bullets.RifleBullet
import bullets.ThreeEighty

fun main() {
    val player = Player()

    println("First rifle reload:")
    println("Ammo: ${player.reload(RifleBullet::class)}")
    println(player.rifle)

    for (i in 0 until 5) {
        player.shootRifle()
    }

    println("Second rifle reload:")
    println("Ammo: ${player.reload(RifleBullet::class)}")
    println(player.rifle)

    for (i in 0 until 5) {
        player.shootRifle()
    }

    println("Third rifle reload:")
    println("Ammo: ${player.reload(RifleBullet::class)}")
    println(player.rifle)

    println("First revolver reload:")
    println("Ammo: ${player.reload(ThreeEighty::class)}")
    println(player.revolver)

    for (i in 0 until 6) {
        player.shootRevolver()
    }
    println("Second revolver reload:")
    println("Ammo: ${player.reload(ThreeEighty::class)}")
    println(player.revolver)
    player.revolver.shootBullet()
    println("Third revolver reload:")
    println("Ammo: ${player.reload(ThreeEighty::class)}")
}