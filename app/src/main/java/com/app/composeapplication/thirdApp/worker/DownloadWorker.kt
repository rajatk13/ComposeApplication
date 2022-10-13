package com.app.composeapplication.thirdApp.worker

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.app.composeapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random

class DownloadWorker(
    private val context: Context,
    private val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {



    override suspend fun doWork(): Result {
        showNotification()
        delay(5000L)
        val response = FileApi.instance.downloadImage()
        response.body()?.let { body->

        return withContext(Dispatchers.IO){
            val file = File(context.cacheDir,"image.jpg")
            val outputStream = FileOutputStream(file)
              outputStream.use {
                  try {
                     it.write(body.bytes())
                  }catch (e:IOException){
                    return@withContext Result.failure(
                        workDataOf(WorkerKeys.ERROR_MSG to e.localizedMessage)
                    )
                  }
              }
              Result.success(
                  workDataOf(
                      WorkerKeys.IMAGE_URI to file.toUri().toString()
                  )
              )
            }


        }
        if(!response.isSuccessful){
            if(response.code().toString().startsWith("5")){
                return Result.retry()
            }
            return Result.failure(
                workDataOf(
                    WorkerKeys.ERROR_MSG to "Network error"
                )
            )
        }
        return Result.failure(
            workDataOf(
                WorkerKeys.ERROR_MSG to "Unknown error"
            )
        )
    }

    private suspend fun showNotification(){
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(context,"download_chanel").setSmallIcon(R.drawable.flag_bck)
                    .setContentText("Downloading123...")
                    .setContentTitle("Rajat Downloading in Progress")
                    .build()
            )
        )
    }
}