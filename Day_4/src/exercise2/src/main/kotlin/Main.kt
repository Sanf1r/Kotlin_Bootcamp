package org.example

import bullets.*

fun main() {
    println("Check that normal bullets shoot and damp not:")
    val revolver = RevolverDrum<Bullet>()
    // fire normal and damp
    val normalBullet = FortyFive()
    revolver.addBullet(normalBullet)
    revolver.shootBullet()
    val dampBullet = FortyFive()
    dampBullet.damp = true
    revolver.addBullet(dampBullet)
    revolver.shootBullet()
    println()
    // add bullet to different drums
    println("Try to add one bullet to different drums:")
    val secondDrum = RevolverDrum<Bullet>()
    val insertBullet = FortyFive()
    println(revolver.addBullet(insertBullet))
    println(secondDrum.addBullet(insertBullet))
    println()
    // collections check
    println("Try to add one bullet to different collections:")
    revolver.unloadAllBullets()
    val bulletOne = ThreeEighty()
    val bulletTwo = ThreeEighty()
    val bulletThree = ThreeEighty()
    val wrongOne = FortyFive()
    val supply: MutableList<Bullet> = mutableListOf(bulletOne,bulletTwo,bulletThree,wrongOne)
    revolver.supplyBullets(supply)
    println(revolver)
    println(supply)
    println()
    // test load unload
    println("Load unload test:")
    revolver.unloadAllBullets()
    val test = FortyFive()
    revolver.addBullet(test)
    println(revolver)
    revolver.unloadOneBullet()
    println(revolver)
    revolver.addBullet(test)
    revolver.shootBullet()
    println()
    // test sharpshoot
    revolver.unloadAllBullets()
    for (i in 0 .. 5) {
        revolver.addBullet(FortyFive())
    }
    for (i in 0 .. 11) {
        revolver.shootBullet()
    }

}