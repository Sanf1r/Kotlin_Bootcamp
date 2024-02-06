package org.example

abstract class Character<T : Weapon> {
    abstract fun interact(weapon: T)
}

class Rambo : Character<Weapon>() {
    override fun interact(weapon: Weapon) {
        println("Rambo attacks with a ${weapon.interact()}")
    }
}

class Sniper : Character<Weapon>() {
    override fun interact(weapon: Weapon) {
        when (weapon) {
            is Firearm -> println("Sniper attacks with a ${weapon.interact()}")
            else -> println("Sniper cant attack with a ${weapon::class.simpleName}!")
        }
    }
}

class Ninja : Character<Weapon>() {
    override fun interact(weapon: Weapon) {
        when (weapon) {
            is Cold, is Silent -> println("Ninja attacks with a ${weapon.interact()}")
            else -> println("Ninja cant attack with a ${weapon::class.simpleName}!")
        }
    }
}

