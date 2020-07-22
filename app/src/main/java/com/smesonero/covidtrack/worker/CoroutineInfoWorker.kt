package com.smesonero.covidtrack.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.*

class CoroutineInfoWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {


    //override val coroutineContext = Dispatchers.IO  //Deprecated

    override suspend fun doWork(): Result = coroutineScope {

        withContext(Dispatchers.IO) {
            val jobs = (0 until 100).map {
                async {

                    Log.e("AA","EE")
                    //downloadSynchronously("https://www.google.com")
                }
            }

            // awaitAll will throw an exception if a download fails, which CoroutineWorker will treat as a failure
            jobs.awaitAll()
            Log.e("CORUTINE", "Despues wait all");
            Result.success()
        }
    }
}