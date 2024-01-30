package org.example

enum class Field(val value: String) {
    IT("IT"),
    BANK("Banking"),
    PS("Public services"),
    ALL("All");
}

enum class Profession(val value: String) {
    DEV("Developer"),
    QA("QA"),
    PM("PM"),
    AN("Analyst"),
    DES("Designer"),
    ALL("All");
}

enum class Level(val value: String) {
    JUN("Junior"),
    MID("Middle"),
    SEN("Senior"),
    ALL("All");
}

enum class Salary(val value: String) {
    LOW("< 100000"),
    MID("100000 - 150000"),
    TOP("> 150000"),
    ALL("All");
}