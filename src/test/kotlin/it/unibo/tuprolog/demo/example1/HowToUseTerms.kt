package it.unibo.tuprolog.demo.example1

import it.unibo.tuprolog.core.Atom
import it.unibo.tuprolog.core.Indicator
import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.Term
import kotlin.test.Test

class HowToUseTerms {

    private val peanoIntegers: Sequence<Term>
        get() = generateSequence<Struct>(Atom.of("z")) { Struct.of("s", it) }

    @Test
    fun `the first N terms in the Herbrand universe spawned by z|0 and s|1 are the first N Peano numbers`() {
        val functors = arrayOf(
            Indicator.of("z", 0),
            Indicator.of("s", 1)
        )

        val N = 10

        print(herbrand(*functors).take(N).toList())
    }
}