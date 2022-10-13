package com.app.composeapplication.thirdApp.worker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.work.*
import coil.compose.rememberImagePainter
import com.app.composeapplication.thirdApp.worker.ui.theme.ComposeApplicationTheme

class WorkManagerSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            ).build()
        val colorFilterRequest = OneTimeWorkRequestBuilder<ColorFilterWorker>().build()
        val workManager =WorkManager.getInstance(applicationContext)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                   // Greeting3("Android")
                   val workInfos =workManager.getWorkInfosForUniqueWorkLiveData("download")
                       .observeAsState()
                       .value
                    val downloadInfo = remember(key1 = workInfos) {
                        workInfos?.find { it.id==downloadRequest.id }
                        
                    }
                    val filterInfo = remember(key1 = workInfos) {
                        workInfos?.find { it.id==colorFilterRequest.id }
                    }


                   /* val imageUri by derivedStateOf {
                        val downloadUri = downloadInfo?.outputData?.getString(WorkerKeys.IMAGE_URI)
                            ?.toUri()
                        val filterUri = filterInfo?.outputData?.getString(WorkerKeys.FILTER_URI)
                            ?.toUri()

                        filterUri ?: downloadUri
                    }*/

                    val imageUri by derivedStateOf {
                        val downloadUri = downloadInfo?.outputData?.getString(WorkerKeys.IMAGE_URI)
                            ?.toUri()
                        val filterUri = filterInfo?.outputData?.getString(WorkerKeys.FILTER_URI)
                            ?.toUri()
                        filterUri ?: downloadUri
                    }
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        imageUri?.let {
                            Image(
                                painter = rememberImagePainter(data = it),
                                contentDescription = "rajat",
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Button(onClick = {
                            workManager.beginUniqueWork(
                                "download",
                                ExistingWorkPolicy.KEEP,
                                downloadRequest
                            ).then(colorFilterRequest)
                                .enqueue()

                        }, enabled = downloadInfo?.state !=WorkInfo.State.RUNNING
                        ){
                           Text(text = "Start download rajat")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        when(downloadInfo?.state){
                            WorkInfo.State.RUNNING -> Text(text = "Downloading..")
                            WorkInfo.State.SUCCEEDED -> Text(text = "Downloading succeeded")
                            WorkInfo.State.FAILED -> Text(text = "Downloading failed..")
                            WorkInfo.State.CANCELLED -> Text(text = "Downloading cancelled")
                            WorkInfo.State.ENQUEUED -> Text(text = "Downloading enqueued")
                            WorkInfo.State.BLOCKED -> Text(text = "Downloading blocked")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        when(filterInfo?.state){
                            WorkInfo.State.RUNNING -> Text(text = "Applying filter..")
                            WorkInfo.State.SUCCEEDED -> Text(text = "Filter succeeded")
                            WorkInfo.State.FAILED -> Text(text = "Filter failed")
                            WorkInfo.State.CANCELLED -> Text(text = "Filter cancelled")
                            WorkInfo.State.ENQUEUED -> Text(text = "Filter enqueued")
                            WorkInfo.State.BLOCKED -> Text(text = "Filter blocked")
                        }

                    }

                }
            }
        }
    }
}

/*
@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeApplicationTheme {
        Greeting3("Android")
    }
}*/
