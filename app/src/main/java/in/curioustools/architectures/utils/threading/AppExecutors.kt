package `in`.curioustools.architectures.utils.threading

import android.os.Handler
import android.os.Looper

import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Constructor for instantiating singleton AppExecutor class
 *
 * @param singleThreadExecutorService     Disk IO operations
 * @param mainThread Main Thread operations
 * "thank you https://github.com/AbhishekChd for this" - root-ansh
 */
class AppExecutors private constructor(val singleThreadExecutorService: ExecutorService, val mainThread: Executor) {

    companion object {
        private val LOCK = Any()
        private var INSTANCE: AppExecutors? = null

        //A singleton class instantiation . @return [AppExecutors]

        fun getSingletonInstance(): AppExecutors {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    INSTANCE =
                        AppExecutors(
                            Executors.newSingleThreadExecutor(),
                            MainThreadExecutor()
                        )
                }
            }
            return INSTANCE!!
        }


    }

}

private class MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mainThreadHandler.post(command)
    }
}