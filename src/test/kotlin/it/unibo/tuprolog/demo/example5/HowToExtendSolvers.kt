package it.unibo.tuprolog.demo.example5

import it.unibo.tuprolog.core.*
import it.unibo.tuprolog.core.parsing.parseAsStruct
import it.unibo.tuprolog.solve.ExecutionContext
import it.unibo.tuprolog.solve.Solver
import it.unibo.tuprolog.solve.primitive.Solve
import it.unibo.tuprolog.solve.primitive.UnaryPredicate
import it.unibo.tuprolog.unify.Unificator.Companion.mguWith
import org.gciatto.kt.math.BigInteger
import kotlin.test.Test

class HowToExtendSolvers {

    object Natural : UnaryPredicate<ExecutionContext>("natural") {
        override fun Solve.Request<ExecutionContext>.computeAll(first: Term): Sequence<Solve.Response> =
            when (first) {
                is Integer -> sequenceOf(
                    replyWith(first.value >= BigInteger.ZERO)
                )
                is Var ->  sequence {
                    var i = BigInteger.ZERO
                    while (true) {
                        yield(replyWith(first mguWith Integer.of(i)))
                        i += BigInteger.ONE
                    }
                }
                else -> sequenceOf(
                    replyFail()
                )
            }
    }

    @Test
    fun `Prolog solvers can be extended with Kotlin functions having a logic API`() {
        val solver = Solver.prolog.solverOf(
            libraries = libraryOf(Natural)
        )

        solver.solve("natural(1)").forEach { println(it) }
        // Yes(query=natural(1), substitution={})

        solver.solve("natural(X)").take(10).forEach { println(it) }
        // Yes(query=natural(X_0), substitution={X_0=0})
        // Yes(query=natural(X_0), substitution={X_0=1})
        // Yes(query=natural(X_0), substitution={X_0=2})
        // Yes(query=natural(X_0), substitution={X_0=3})
        // Yes(query=natural(X_0), substitution={X_0=4})
        // Yes(query=natural(X_0), substitution={X_0=5})
        // Yes(query=natural(X_0), substitution={X_0=6})
        // Yes(query=natural(X_0), substitution={X_0=7})
        // Yes(query=natural(X_0), substitution={X_0=8})
        // Yes(query=natural(X_0), substitution={X_0=9})

        solver.solve("natural(a)").take(10).forEach { println(it) }
        // No(query=natural(a))
    }

    @Test
    fun `Concurrent LP solvers can be extended with Kotlin functions having a logic API`() {
        val solver = Solver.concurrent.solverOf(
            libraries = libraryOf(Natural)
        )

        solver.solve("natural(1)").forEach { println(it) }
        // Yes(query=natural(1), substitution={})

        solver.solve("natural(X)").take(10).forEach { println(it) }
        // Yes(query=natural(X_0), substitution={X_0=0})
        // Yes(query=natural(X_0), substitution={X_0=1})
        // Yes(query=natural(X_0), substitution={X_0=2})
        // Yes(query=natural(X_0), substitution={X_0=5})
        // Yes(query=natural(X_0), substitution={X_0=4})
        // Yes(query=natural(X_0), substitution={X_0=6})
        // Yes(query=natural(X_0), substitution={X_0=3})
        // Yes(query=natural(X_0), substitution={X_0=8})
        // Yes(query=natural(X_0), substitution={X_0=7})
        // Yes(query=natural(X_0), substitution={X_0=9})

        solver.solve("natural(a)").take(10).forEach { println(it) }
        // No(query=natural(a))
    }
}