package dev.plotsky.musikt

import okhttp3.OkHttpClient
import okreplay.OkReplayConfig
import okreplay.OkReplayInterceptor
import okreplay.RecorderRule
import okreplay.TapeMode
import org.junit.Rule
import org.junit.rules.TestRule

open class ReplayTest(mode: TapeMode) {
    private val interceptor = OkReplayInterceptor()
    private val configuration = OkReplayConfig.Builder()
        .defaultMode(mode) // or TapeMode.READ_WRITE
        .sslEnabled(true).interceptor(interceptor).build()

    @get:Rule
    val testRule: TestRule = RecorderRule(configuration)

    val client = OkHttpClient().newBuilder()
        .addInterceptor(interceptor).build()
}
