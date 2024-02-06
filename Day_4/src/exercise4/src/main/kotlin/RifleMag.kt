package org.example

import bullets.RifleBullet
import kotlin.reflect.KClass

class RifleMag<T : RifleBullet>(private val klass: KClass<T>) {
    private val mag: MutableList<T?> = MutableList(5) { null }
    private var pointer: Int = 0

    companion object {
        inline operator fun <reified T : RifleBullet> invoke() = RifleMag(T::class)
    }

    fun ejectShotAndDamp() {
        for (i in 0 until mag.size) {
            if (mag[i]?.shot == true || mag[i]?.damp == true) {
                mag[i] = null
            }
        }
    }

    fun supplyBullets(from: MutableList<T>): Boolean {
        if (from.isEmpty()) return false
        from.removeIf { addBullet(it) }
        return true
    }

    fun shootBullet(): Boolean {
        val bullet = mag[pointer]
        if (bullet == null) {
            ++pointer
            pointer %= mag.size
            println("Click!")
            return false
        } else if (bullet.shot) {
            println("Click! A shot one!")
            ++pointer
            pointer %= mag.size
            return false
        } else if (bullet.damp) {
            println("Click! A damp one!")
            ++pointer
            pointer %= mag.size
            return false
        } else {
            bullet.shoot()
            mag[pointer++]?.shot = true
            pointer %= mag.size

            return true
        }
    }

    private fun bulletsTypeCheck(add: T): Boolean {
        var res = true
        for (bull in mag) {
            if (bull != null && add::class != RifleBullet::class) {
                println("You can only load rifle bullets!")
                res = false
                break
            }
        }
        return res
    }

    private fun addBullet(add: T): Boolean {
        if (!bulletsTypeCheck(add)) return false
        if (add.load) println("This bullet already loaded").also { return false }

        var res = false
        var tmpPointer = pointer
        while (true) {
            if (mag[tmpPointer] == null) {
                mag[tmpPointer] = add
                pointer = tmpPointer
                add.load = true
                res = true
                break
            } else {
                tmpPointer++
            }
            if (tmpPointer % mag.size == 0) tmpPointer = 0
            if (tmpPointer == pointer) break
        }
        return res
    }

    override fun toString(): String {

        return "Structure: ${this::class.simpleName}<${klass.simpleName}>\nObjects: $mag"
    }
}