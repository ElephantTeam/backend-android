package com.schibsted.elephant.backend.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.Trigger
import org.springframework.scheduling.TriggerContext
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Configuration
@EnableScheduling
class TaskScheduller : SchedulingConfigurer {
    @Bean(destroyMethod = "shutdown")
    fun taskExecutor(): Executor {
        return Executors.newScheduledThreadPool(100)
    }

    val taskProvider = TaskProvider()

    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor())

        val runnable: Runnable = object : Runnable {
            override fun run() {
                //todo here the pushes can be send
                println("task")
            }
        }

        val trigger = object : Trigger {
            override fun nextExecutionTime(triggerContext: TriggerContext): Date? {
                val nextExecutionTime: Calendar = GregorianCalendar()
                val lastActualExecutionTime =
                    triggerContext.lastActualExecutionTime()
                nextExecutionTime.time = lastActualExecutionTime ?: Date()
                nextExecutionTime.add(
                    Calendar.MILLISECOND,
                    taskProvider.next()
                ) //you can get the value from wherever you want
                return nextExecutionTime.time
            }
        }

        taskRegistrar.addTriggerTask(runnable, trigger)
    }
}

class TaskProvider {
    /**
     * [numberOfPushes] number of pushes to be send across choosed timespan
     * [timeUnitQuantity] and [timeUnit] defines timespan eg 5 TimeUnit.SECONDS means 5 sec
     */
    private val numberOfPushes = 5
    private val timeUnitQuantity: Int = 50
    private val timeUnit: TimeUnit = TimeUnit.SECONDS

    //
    private val timeSpan: Long = timeUnit.toMillis(timeUnitQuantity.toLong())
    private var currentJobNumber = 0

    private val timeSpansBetweenJobs = mutableListOf<Int>()

    init {
        generateJobTriggerTimeSpans()
    }

    private fun generateJobTriggerTimeSpans() {
        //impl of alghoritm https://stackoverflow.com/questions/14959200/dividing-a-number-into-random-unequal-parts
        val reqSUm = timeSpan
        println("reqsum " + reqSUm)
        val randoms = mutableListOf<Int>()
        for (i in 0 until numberOfPushes) {
            val r = Random.nextInt(0, 1000)
            randoms.add(r)
            println("rand $i: " + r)
        }
        var randSum = 0
        randoms.forEach {
            randSum += it
        }

        println("randSum " + randSum)
        randoms.forEach {
            val recalled = ((it * reqSUm).toFloat() / randSum.toFloat()).toInt()
            timeSpansBetweenJobs.add(recalled)
        }

        var randomsRescaledSum = 0
        timeSpansBetweenJobs.forEach {
            randomsRescaledSum += it
            println("randrescaled: " + it)
        }
        println("randrescaledSum: " + randomsRescaledSum)
    }

    /**
     * returns time in miliseconds that tells, when the next job should be done
     */
    fun next(): Int {
        val nextTime = timeSpansBetweenJobs[currentJobNumber]
        currentJobNumber++
        return nextTime
    }
}
