package org.example.domain

class GetResumeUseCase(private val repository: Repository) {

    fun execute(): CandidateInfo {
        return repository.getResume()
    }

    fun hardCodeTags(): List<String> {
        return listOf(
            "QA", "Designer", "Developer", "Analyst",
            "male", "female", "junior", "middle", "senior"
        )
    }
}