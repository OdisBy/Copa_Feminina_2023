package com.odisby.copa.womens.domain.usecase

import com.odisby.copa.womens.domain.repositories.MatchesRepository
import javax.inject.Inject

class EnableNotificationUseCase @Inject constructor(
    private val repository: MatchesRepository
) {
    suspend operator fun invoke(id: String) {
        repository.enableNotificationFor(id)
    }
}
