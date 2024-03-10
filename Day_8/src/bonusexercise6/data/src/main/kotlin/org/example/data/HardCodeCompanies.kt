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
                Vacancy(1, "developer", "middle", 150000, "Help us!"),
                Vacancy(2, "designer", "middle", 100000, "Looking for 5 weeks"),
                Vacancy(3, "pm", "senior", 160000, "...")
            ),
            "88005353535"
        ),
        Company(
            3,
            "VK",
            "Public services",
            listOf(
                Vacancy(1, "analyst", "junior", 70000, "Semi intern"),
            ),
            "88005362637"
        ),
        Company(
            4,
            "Tinkoff",
            "Banking",
            emptyList(),
            "89001237654"
        )
    )
}