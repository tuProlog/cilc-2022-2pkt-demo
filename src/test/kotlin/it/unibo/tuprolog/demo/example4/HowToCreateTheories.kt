package it.unibo.tuprolog.demo.example4

import it.unibo.tuprolog.dsl.theory.prolog
import it.unibo.tuprolog.theory.parsing.ClausesParser
import kotlin.test.Test

class HowToCreateTheories {
    @Test
    fun `theories may be parsed from strings`() {
        val parser = ClausesParser.withDefaultOperators()
        val theory = parser.parseTheory("""
            nat(z).
            nat(s(X)) :- nat(X).
        """.trimIndent())

        println(theory)
        // IndexedTheory{ nat(z) :- true. nat(s(X_0)) :- nat(X_0) }
    }

    @Test
    fun `theories can be created via kotlin DSL`() {
        val theory = prolog {
            theoryOf(
                fact { "nat"("z") },
                fact { "nat"("s"(X)) impliedBy "nat"(X) }
            )
        }

        println(theory)
        // IndexedTheory{ nat(z) :- true. nat(s(X_0)) :- nat(X_0) }
    }
}