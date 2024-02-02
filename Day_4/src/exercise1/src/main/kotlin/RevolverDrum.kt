package org.example

import kotlin.reflect.KClass

class RevolverDrum<T : Any>(private val klass: KClass<T>) {
    val drum: MutableList<T?> = MutableList(6) { null }
    private var pointer: Int = 0

    companion object {
        inline operator fun <reified T : Any> invoke() = RevolverDrum(T::class)
    }

    private fun getPointer(): T? {
        return drum[pointer]
    }

    fun addBullet(add: T): Boolean {
        var res = false
        var tmpPointer = pointer
        while (true) {
            if (drum[tmpPointer] == null) {
                drum[tmpPointer] = add
                pointer = tmpPointer
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
        var res = false
        var tmpPointer = pointer
        while (true) {
            if (drum[tmpPointer] == null) {
                drum[tmpPointer] = add
                pointer = tmpPointer
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
        if (drum[pointer] == null) {
            ++pointer
            pointer %= drum.size
            return false
        } else {
            drum[pointer++] = null
            pointer %= drum.size
            return true
        }
    }

    fun unloadOneBullet(): T? {
        var element: T? = null
        if (drum[pointer] != null) {
            element = drum[pointer]
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