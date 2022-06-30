package it.unibo.tuprolog.demo.example1

import it.unibo.tuprolog.core.Indicator
import kotlin.test.Test

class HowToUseTerms {

    @Test
    fun `the first N terms in the Herbrand universe spawned by z|0 and s|1 are the first N Peano numbers`() {
        val functors = arrayOf(
            Indicator.of("z", 0), // z/0
            Indicator.of("s", 1)  // s/1
        )

        val N = 10

        herbrand(*functors).take(N).forEach { println(it) }
    }
}