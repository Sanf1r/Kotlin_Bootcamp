package org.example.domain

class GetResumeUseCase(private val repository: Repository) {

    fun execute(): List<CandidateInfo> {
        return repository.getResume()
    }
}