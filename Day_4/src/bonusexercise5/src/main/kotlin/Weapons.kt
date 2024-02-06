package org.example

interface Firearm
interface Silent
interface Cold
interface Explosive

abstract class Weapon {
    abstract fun interact(): String
}

class Pistol : Weapon(), Firearm {
    override fun interact(): String {
        return "${this::class.simpleName} (${
            this::class.java.interfaces.joinToString(", ") { it.simpleName }
        }) - PIF-PAF!"
    }
}

class SniperRifle : Weapon(), Firearm {
    override fun interact(): String {
        return "${this::class.simpleName} (${
            this::class.java.interfaces.joinToString(", ") { it.simpleName }
        }) - SHOOOOOOT!"
    }
}

class SilencedPistol : Weapon(), Firearm, Silent {
    override fun interact(): String {
        return "${this::class.simpleName} (${
            this::class.java.interfaces.joinToString(", ") { it.simpleName }
        }) - psssss pif-paf but silent"
    }
}

class Shuriken : Weapon(), Cold {
    override fun interact(): String {
        return "${this::class.simpleName} (${
            this::class.java.interfaces.joinToString(", ") { it.simpleName }
        }) - SPLASH!"
    }
}

class Katana : Weapon(), Cold {
    override fun interact(): String {
        return "${this::class.simpleName} (${
            this::class.java.interfaces.joinToString(", ") { it.simpleName }
        }) - SLICE"
    }
}

class C4 : Weapon(), Explosive {
    override fun interact(): String {
        return "${this::class.simpleName} (${
            this::class.java.interfaces.joinToString(", ") { it.simpleName }
        }) - BIG KA-BOOOM-BOOOOOOOM!!!!!!"
    }
}

class Grenade : Weapon(), Explosive {
    override fun interact(): String {
        return "${this::class.simpleName} (${
            this::class.java.interfaces.joinToString(", ") { it.simpleName }
        }) KA-BOOOM!!!!!!"
    }
}
