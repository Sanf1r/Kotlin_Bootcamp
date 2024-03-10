package org.example.data

import org.example.domain.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

class HardCodeResume {
    private val formatterFull = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private val formatterPart = DateTimeFormatterBuilder().appendPattern("MM.yyyy")
        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
        .toFormatter()
    private val formatterYear = DateTimeFormatterBuilder().appendPattern("yyyy")
        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
        .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
        .toFormatter()

    val resumes: CandidateInfo = CandidateInfo(
        1,
        Candidate(
            "Vasiliev Sergei Petrovich",
            "QA",
            "male",
            LocalDate.parse("30.09.1998", formatterFull),
            Contacts("72938572843", "vspetrovich@pochta.ru"),
            "possible"
        ),
        listOf(
            Education(
                "higher",
                LocalDate.parse("2017", formatterYear),
                LocalDate.parse("2021", formatterYear),
                "Mathematics in state university"
            ),
            Education(
                "secondary special",
                LocalDate.parse("2013", formatterYear),
                LocalDate.parse("2017", formatterYear),
                "College of informatics"
            ),
            Education(
                "secondary",
                LocalDate.parse("2005", formatterYear),
                LocalDate.parse("2013", formatterYear),
                "Lyceum 156"
            )
        ),
        listOf(
            JobExperience(
                LocalDate.parse("08.2021", formatterPart),
                LocalDate.parse("04.2022", formatterPart),
                "FinTech",
                "Some fintech company creating a business platform"
            ),
            JobExperience(
                LocalDate.parse("05.2022", formatterPart),
                LocalDate.parse("01.2023", formatterPart),
                "SoftProm",
                "Typical galley"
            )
        ),
        "I'm a QA specialist from head to heels. ..."
    )
}