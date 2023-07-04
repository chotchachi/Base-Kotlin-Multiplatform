package com.base.kmm.android.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.base.kmm.android.databinding.ActivityMainBinding
import com.base.shared.domain.repository.Repository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityMainBinding
    private val repo by inject<Repository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repo.getDetectResult("64a231a4bf7a5.png", "64a299aa50111.png")
            .onEach {
                Log.d(">>>", it)
            }
            .catch { Log.d(">>>", it.toString()) }
            .launchIn(lifecycleScope)
    }

    private fun getAssetByteArray(imageName: String): ByteArray {
        val inputStream = assets.open(imageName)

        val buffer = ByteArray(8192)
        var bytesRead: Int
        val output = ByteArrayOutputStream()
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
        }
        return output.toByteArray()
    }
}