package org.example

import bullets.*

import kotlin.reflect.KClass

class RevolverDrum<T : Bullet>(private val klass: KClass<T>) {
    val drum: MutableList<T?> = MutableList(6) { null }
    private var pointer: Int = 0

    companion object {
        inline operator fun <reified T : Bullet> invoke() = RevolverDrum(T::class)
    }

    private fun getPointer(): T? {
        return drum[pointer]
    }

    private fun bulletsTypeCheck(add: T): Boolean {
        var res = true
        for (bull in drum) {
            if (bull != null && bull::class != add::class) {
                println("You can't load bullets of different caliber in one drum!")
                res = false
                break
            }
        }
        return res
    }

    fun addBullet(add: T): Boolean {
        if (!bulletsTypeCheck(add)) return false
        if (add.load) println("This bullet already loaded").also { return false }

        var res = false
        var tmpPointer = pointer
        while (true) {
            if (drum[tmpPointer] == null) {
                drum[tmpPointer] = add
                pointer = tmpPointer
                add.load = true
                res = true
                break
            } else {
                tmpPointer++
            }
            if (tmpPointer % drum.size == 0) tmpPointer = 0
            if (tmpPointer == pointer) break
        }
        return res
    }

    private fun addBulletBackwards(add: T): Boolean {
        if (!bulletsTypeCheck(add)) return false
        if (add.load) println("This bullet already loaded").also { return false }

        var res = false
        var tmpPointer = pointer
        while (true) {
            if (drum[tmpPointer] == null) {
                drum[tmpPointer] = add
                pointer = tmpPointer
                add.load = true
                res = true
                break
            } else {
                tmpPointer--
            }
            if (tmpPointer == -1) tmpPointer = drum.size - 1
            if (tmpPointer == pointer) break
        }
        return res
    }

    fun supplyBullets(from: MutableList<T>): Boolean {
        if (from.isEmpty()) return false
        from.removeIf { addBulletBackwards(it) }
        return true
    }

    fun shootBullet(): Boolean {
        val bullet = drum[pointer]
        if (bullet == null) {
            ++pointer
            pointer %= drum.size
            println("Click!")
            return false
        } else if (bullet.shot) {
            println("Click! A shot one!")
            ++pointer
            return false
        } else if (bullet.damp) {
            println("Click! A damp one!")
            ++pointer
            return false
        } else {
            bullet.shoot()
            drum[pointer++]?.shot = true
            pointer %= drum.size

            return true
        }
    }

    fun unloadOneBullet(): T? {
        var element: T? = null
        if (drum[pointer] != null) {
            element = drum[pointer]
            element?.load = false
            drum[pointer] = null
        }
        return element
    }

    fun unloadAllBullets(): List<T?> {
        val start = pointer
        val drumCopy = drum.toMutableList()
        drumSpin(drumCopy, drumCopy.size - start)
        return drumCopy.toList().also { drum.fill(null) }
    }

    fun scrollDrum() {
        val rand = (0..5).random()
        val index = rand % drum.size
        drumSpin(drum, index)
    }

    private fun drumSpin(drum: MutableList<T?>, index: Int) {
        reverse(drum, 0, drum.size - 1)
        reverse(drum, 0, index - 1)
        reverse(drum, index, drum.size - 1)
    }

    private fun reverse(list: MutableList<T?>, start: Int, end: Int) {
        var s = start
        var e = end
        while (s < e) {
            val temp = list[s]
            list[s] = list[e]
            list[e] = temp
            ++s
            --e
        }
    }

    fun bulletsCount(): Int {
        return drum.count { it != null }
    }

    fun nextBullet() {
        ++pointer
    }

    override fun toString(): String {
        val start = pointer
        val drumCopy = drum.toMutableList()
        drumSpin(drumCopy, drumCopy.size - start)

        return "Structure: ${this::class.simpleName}<${klass.simpleName}>\nObjects: $drumCopy\nPointer: ${getPointer()}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RevolverDrum<*>) return false
        if (drum.size != other.drum.size) return false
        val tmpDrum = drum.toMutableList()
        val tmpPointer = pointer
        drumSpin(tmpDrum, drum.size - tmpPointer)

        return tmpDrum == other.drum
    }

    override fun hashCode(): Int {
        return drum.hashCode()
    }
}