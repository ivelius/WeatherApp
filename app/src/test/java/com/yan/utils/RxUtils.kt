package com.yan.utils

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class RxUtils {

    companion object {
        fun resetRxSchedulers() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }

        /**
         * Makes all rx operations execute sequentially on the same thread
         */
        fun makeRxSchedulersImmediate() {
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }
}