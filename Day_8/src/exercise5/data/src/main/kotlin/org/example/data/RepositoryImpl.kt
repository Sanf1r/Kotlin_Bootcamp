package org.example.data

import org.example.domain.CandidateInfo
import org.example.domain.Company
import org.example.domain.Repository

class RepositoryImpl : Repository {

    private val hardCodeCompanies = HardCodeCompanies()
    private val hardCodeResume = HardCodeResume()

    override fun getCompanyList(): List<Company> {
        return hardCodeCompanies.outList
    }

    override fun getCompanyById(id: Int): Company? {
        return hardCodeCompanies.outList.getOrNull(id)
    }

    override fun getResume(): List<CandidateInfo> {
        return hardCodeResume.resumes
    }
}

