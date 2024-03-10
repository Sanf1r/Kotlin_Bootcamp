package org.example.domain

import java.time.Period
import mu.KotlinLogging

class TextAnalysis {
    private val logger = KotlinLogging.logger {}

    fun execute(data: CandidateInfo, list: List<String>): List<String> {
        val res = mutableListOf<String>()
        var months = 0
        for (job in data.jobExperience) {
            val per = Period.between(job.dateStart, job.dateEnd)
            months += 12 * per.years + per.months
        }

        val grade = when (months) {
            in 0..35 -> "junior"
            in 36..72 -> "middle"
            else -> "senior"
        }

        list.forEach {
            if (it.equals(data.candidate.profession, true) ||
                it.equals(data.candidate.sex, true)
            ) {
                logger.info { "Found \"$it\" tag in resume" }
                res.add(it)
            }
        }

        res.add(grade)
        logger.info { "Found \"$grade\" tag in resume" }
        return res.toList()
    }
}