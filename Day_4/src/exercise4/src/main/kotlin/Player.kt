package org.example

import bullets.Bullet
import bullets.FortyFive
import bullets.PistolBullet
import bullets.RifleBullet
import bullets.ThreeEighty
import bullets.TwentyTwo
import kotlin.reflect.KClass

class Player {
    val revolver = RevolverDrum<PistolBullet>()
    val rifle = RifleMag<RifleBullet>()

    private val twentyTwo: MutableList<PistolBullet> = MutableList(10) { TwentyTwo() }
    private val threeEighty: MutableList<PistolBullet> = MutableList(7) { ThreeEighty() }
    private val fortyFive: MutableList<PistolBullet> = MutableList(44) { FortyFive() }
    private val rifleBullet: MutableList<RifleBullet> = MutableList(12) { RifleBullet() }

    fun shootRevolver() = revolver.shootBullet()
    fun shootRifle() = rifle.shootBullet()

    fun <T : Bullet> reload(caliber: KClass<T>): MutableList<out Bullet>? {

        val res: MutableList<out Bullet>? = when (caliber) {
            TwentyTwo::class -> {
                revolver.ejectShotAndDamp()
                twentyTwo.takeUnless { it.isEmpty() }?.let { revolver.supplyBullets(it) }
                if (twentyTwo.isEmpty()) {
                    null
                } else {
                    twentyTwo
                }
            }

            ThreeEighty::class -> {
                revolver.ejectShotAndDamp()
                threeEighty.takeUnless { it.isEmpty() }?.let { revolver.supplyBullets(it) }
                if (threeEighty.isEmpty()) {
                    null
                } else {
                    threeEighty
                }
            }

            FortyFive::class -> {
                revolver.ejectShotAndDamp()
                fortyFive.takeUnless { it.isEmpty() }?.let { revolver.supplyBullets(it) }
                if (fortyFive.isEmpty()) {
                    null
                } else {
                    fortyFive
                }
            }

            RifleBullet::class -> {
                rifle.ejectShotAndDamp()
                rifleBullet.takeUnless { it.isEmpty() }?.let { rifle.supplyBullets(it) }
                if (rifleBullet.isEmpty()) {
                    null
                } else {
                    rifleBullet
                }
            }

            else -> null
        }
        return res
    }
}