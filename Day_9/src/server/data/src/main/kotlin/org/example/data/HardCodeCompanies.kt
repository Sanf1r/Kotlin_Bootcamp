package org.example.data

import org.example.domain.Company
import org.example.domain.Vacancy

class HardCodeCompanies {
    val outList: List<Company> = listOf(
        Company(
            1,
            "Sber",
            "Banking",
            listOf(
                Vacancy(1, "qa", "middle", 80000, "Very interesting job"),
                Vacancy(2, "pm", "senior", 130000, "High priority!")
            ),
            "89191234567"
        ),
        Company(
            2,
            "Yandex",
            "IT",
            listOf(
                Vacancy(3, "developer", "middle", 150000, "Help us!"),
                Vacancy(4, "designer", "middle", 100000, "Looking for 5 weeks"),
                Vacancy(5, "pm", "senior", 160000, "...")
            ),
            "88005353535"
        ),
        Company(
            3,
            "VK",
            "Public services",
            listOf(
                Vacancy(6, "analyst", "junior", 70000, "Semi intern"),
            ),
            "88005362637"
        ),
        Company(
            4,
            "Tinkoff",
            "Banking",
            listOf(
                Vacancy(7, "analyst", "junior", 70000, "Go to us"),
                Vacancy(8, "designer", "middle", 170000, "Where are you?"),
                Vacancy(9, "pm", "senior", 570000, "salary is a joke"),
            ),
            "89001237654"
        ),
        Company(
            5,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        ),
        Company(
            6,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        ),
        Company(
            7,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        ),
        Company(
            8,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        ),
        Company(
            9,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        ),
        Company(
            10,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        ),
        Company(
            11,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        ),
        Company(
            12,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        ),
        Company(
            13,
            "Test data",
            "Banking",
            emptyList(),
            "8900123154"
        )
    )
}