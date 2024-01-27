package org.example

fun fieldSelect() {
    println("Select a field of activity:")
    println("1. IT")
    println("2. Banking")
    println("3. Public services")
    println("4. All")
    println()
}

fun profSelect(str: StringBuilder) {
    print(str.toString())
    println("Select a profession:")
    println("1. Developer")
    println("2. QA")
    println("3. Project Manager")
    println("4. Analyst")
    println("5. Designer")
    println("6. All")
    println()
}

fun levelSelect(str: StringBuilder) {
    print(str.toString())
    println("Select the level of a candidate:")
    println("1. Junior ")
    println("2. Middle")
    println("3. Senior")
    println("4. All")
    println()
}

fun salarySelect(str: StringBuilder) {
    print(str.toString())
    println("Select a salary level:")
    println("1. < 100000")
    println("2. 100000 - 150000")
    println("3. > 150000")
    println("4. All")
    println()
}