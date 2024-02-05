package org.example

import bullets.*

fun main() {
    println("1.")
    println("String, Int - ${areTypesEqual(String, Int)}")
    println("String, String - ${areTypesEqual(String, String)}")
    println()
    println("2.")
    val converter = FirstToSecondConverter()
    val list: List<First> = listOf(First("Alex"), First("John"), First("Peter"))
    println("Map List<First>: $list")
    println("TO List<Second>: ${converter.convertToList(list)}")
    println()
    println("3.")
    val animal = Dog()
    println(animal.isInstanceOf<Dog>())
    println(animal.isInstanceOf<Cat>())
    println(animal.isInstanceOf<Animal>())
    println()
    println("4.")
    val str = "The delegate will replace all spaces with underscores"
    println("Before: $str")
    val four = Four()
    four.description = str
    println("After: ${four.description}")
    println()
    println("5.")
    four.logs = "Hello"
    val inside = four.logs
    println()
    println("6.")
    getOperation("SUBTRACTION")?.invoke(10, 5).also { println("The result of subtraction is $it") }
    println()
    println("7.")
    val revolver = RevolverDrum<Bullet>()
    revolver.addBullet(FortyFive())
    revolver.addBullet(FortyFive())
    revolver.addBullet(FortyFive())
    revolver.unloadOneBullet()
    revolver.nextBullet()
    revolver.nextBullet()
    revolver.addBullet(FortyFive())
    revolver.addBullet(FortyFive())
    println("Before: ${revolver.drum}")
    revolver.removeGaps()
    println("After: ${revolver.drum}")
    println()
    println("8.")
    revolver.unloadAllBullets()
    val supply: MutableList<Bullet> = mutableListOf(FortyFive(),
        FortyFive(),
        FortyFive(),
        FortyFive(),
        FortyFive(),
        FortyFive()
    )
    revolver.supplyBullets(supply)
    println(revolver)
    revolver.spinAndShoot()
    println(revolver)
    println()
    println("9.")
    revolver.unloadAllBullets()
    val supplyTwo: MutableList<Bullet> = mutableListOf(FortyFive(),
        FortyFive(),
        FortyFive(),
        FortyFive(),
        FortyFive(),
        FortyFive()
    )
    revolver.supplyBullets(supplyTwo)
    revolver.unloadOneBullet()
    revolver.nextBullet()
    println("Before:")
    println(revolver)
    println()
    println("After:")
    val newDrum = revolver.oneBulletDrum()
    println(revolver)
    println()
    println("New drum:")
    println(newDrum)

}