package com.odisby.notification_scheduler

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.odisby.copa.womens.domain.model.MatchDomain
import java.time.Duration
import java.time.LocalDateTime

private const val NOTIFICATION_TITLE_KEY = "NOTIFICATION_TITLE_KEY"
private const val NOTIFICATION_CONTENT_KEY = "NOTIFICATION_CONTENT_KEY"
private const val NOTIFICATION_TIMEOUT = "NOTIFICATION_TIMEOUT"
private const val NOTIFICATION_TAG = "MATCH_NOTIFICATION_TAG"
private const val EARLY_NOTIFICATION_TAG = "EARLY_MATCH_NOTIFICATION_TAG"

class NotificationMatcherWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val title = inputData.getString(NOTIFICATION_TITLE_KEY)
            ?: throw IllegalArgumentException("title is required")
        val content = inputData.getString(NOTIFICATION_CONTENT_KEY)
            ?: throw IllegalArgumentException("content is required")
        val timeOut = inputData.getLong(NOTIFICATION_TIMEOUT, -1L)

        if(timeOut != -1L) {
            context.showNotification(title, content, timeOut)
        } else {
            context.showNotification(title, content, null)
        }

        return Result.success()
    }

    companion object {
        fun start(context: Context, match: MatchDomain) {
            val (id, matchDate, _, teamA, teamB, _, _, _, _) = match

            val initialDelayEarly = Duration.between(LocalDateTime.now(), matchDate).minusMinutes(20)
            val inputDataEarly = workDataOf(
                NOTIFICATION_TITLE_KEY to "Se prepare que o jogo vai começar em 20 minutos",
                NOTIFICATION_CONTENT_KEY to "Hoje tem ${teamA.flag} X ${teamB.flag}",
                NOTIFICATION_TIMEOUT to 20L
            )

            val initialDelay = Duration.between(LocalDateTime.now(), matchDate)
            val inputData = workDataOf(
                NOTIFICATION_TITLE_KEY to "O jogo vai começar agora",
                NOTIFICATION_CONTENT_KEY to "O jogo ${teamA.flag} X ${teamB.flag} está começando",
            )
            val uniqueIdEarly = "early_$id"
            val uniqueIdOnTime = "ontime_$id"

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    uniqueIdEarly,
                    ExistingWorkPolicy.KEEP,
                    createRequest(initialDelayEarly, inputDataEarly, EARLY_NOTIFICATION_TAG.plus("_${match.id}"))
                )

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    uniqueIdOnTime,
                    ExistingWorkPolicy.KEEP,
                    createRequest(initialDelay, inputData, NOTIFICATION_TAG.plus("_${match.id}"))
                )
        }

        fun cancel(context: Context, match: MatchDomain) {
            WorkManager.getInstance(context)
                .cancelAllWorkByTag(NOTIFICATION_TAG.plus("_${match.id}"))

            WorkManager.getInstance(context)
                .cancelAllWorkByTag(EARLY_NOTIFICATION_TAG.plus("_${match.id}"))
        }

        private fun createRequest(initialDelay: Duration, inputData: Data, tag: String): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<NotificationMatcherWorker>()
                .setInitialDelay(initialDelay)
                .setInputData(inputData)
                .addTag(tag)
                .build()
    }
}