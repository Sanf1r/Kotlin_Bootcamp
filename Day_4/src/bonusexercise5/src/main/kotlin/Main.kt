package org.example

fun main() {
    val rambo = Rambo()
    val sniper = Sniper()
    val ninja = Ninja()

    val pistol = Pistol()
    val rifle = SniperRifle()
    val silent = SilencedPistol()
    val shuriken = Shuriken()
    val katana = Katana()
    val c4 = C4()
    val grenade = Grenade()

    rambo.interact(pistol)
    sniper.interact(katana)
    ninja.interact(shuriken)
    ninja.interact(silent)
    ninja.interact(c4)
    rambo.interact(grenade)
    sniper.interact(grenade)
    sniper.interact(rifle)
}
