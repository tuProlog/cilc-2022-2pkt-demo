/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package it.unibo.tuprolog.demo.example2


import it.unibo.tuprolog.core.*
import it.unibo.tuprolog.core.List
import it.unibo.tuprolog.core.parsing.parseAsRule
import kotlin.test.Test

@Suppress("USELESS_IS_CHECK")
class HowToCreateClauses {

    @Test
    fun `clauses can be created via method calls`() {
        val X = Var.of("X")
        val member = Fact.of(
            Struct.of("member", X, List.from(X, last = Var.anonymous()))
        )

        println(member)
        // member(X_0, [X_0 | __0])
    }

    @Test
    fun `clauses can be parsed from strings`() {
        val rule = "member(X, [_|T]) :- member(X, T)".parseAsRule()

        println(rule)
        // member(X_0, [__0 | T_0]) :- member(X_0, T_0)
    }
}
