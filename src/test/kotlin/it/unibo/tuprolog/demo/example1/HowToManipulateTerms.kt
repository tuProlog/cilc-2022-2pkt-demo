/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package it.unibo.tuprolog.demo.example1


import it.unibo.tuprolog.core.*
import it.unibo.tuprolog.core.List
import it.unibo.tuprolog.core.operators.Operator
import it.unibo.tuprolog.core.operators.OperatorSet
import it.unibo.tuprolog.core.operators.Specifier.XFY
import it.unibo.tuprolog.core.operators.Specifier.YFX
import it.unibo.tuprolog.core.parsing.TermParser
import it.unibo.tuprolog.core.parsing.parseAsTerm
import it.unibo.tuprolog.dsl.prolog
import kotlin.test.Test
import it.unibo.tuprolog.core.List.Companion as LogicList

@Suppress("USELESS_IS_CHECK")
class HowToManipulateTerms {

    @Test
    fun `one may easily access inner properties for terms`() {
        val person = Struct.of(
            "giovanni",
            Struct.of("first_name", Atom.of("Giovanni")),
            Struct.of("last_name", Var.of("X")),
            Struct.of("age", Integer.of(1))
        )

        println(person.functor) // giovanni
        println(person.arity) // 3
        println(person.args) // last_name(X_0), age(1)]
    }

    @Test
    fun `one may easily rewrite terms by applying substitutions to variables`() {
        val X = Var.of("X")
        val person = Struct.of(
            "giovanni",
            Struct.of("first_name", Atom.of("Giovanni")),
            Struct.of("last_name", X),
            Struct.of("age", Integer.of(1))
        )
        val sub = Substitution.of(X to Atom.of("Ciatto"))

        println(person.apply(sub)) // giovanni(first_name('Giovanni'), last_name('Ciatto'), age(1))
        println(person)            // giovanni(first_name('Giovanni'), last_name(X_0), age(1))
    }

    @Test
    fun `one may format terms according to the provided operators`() {
        val expression = "'*'('+'(X, 1), '-'(X, 2))".parseAsTerm()

        val formatter = TermFormatter.prettyExpressions(
            prettyVariables = true,
            operatorSet = OperatorSet.ARITHMETIC
        )

        println(expression.format(formatter)) // (X + 1) * (X - 2)
    }
}
