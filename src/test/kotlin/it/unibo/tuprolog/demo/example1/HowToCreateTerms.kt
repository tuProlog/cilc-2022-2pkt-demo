/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package it.unibo.tuprolog.demo.example1


import it.unibo.tuprolog.core.*
import it.unibo.tuprolog.core.operators.Operator
import it.unibo.tuprolog.core.operators.Specifier.XFY
import it.unibo.tuprolog.core.operators.Specifier.YFX
import it.unibo.tuprolog.core.parsing.TermParser
import it.unibo.tuprolog.dsl.prolog
import kotlin.test.Test
import it.unibo.tuprolog.core.List as LogicList

@Suppress("USELESS_IS_CHECK")
class HowToCreateTerms {

    @Test
    fun `terms can be created via method calls`() {
        val person = Struct.of(
            "giovanni",
            Struct.of("first_name", Atom.of("Giovanni")),
            Struct.of("last_name", Var.of("X")),
            Struct.of("age", Integer.of(1))
        )

        println(person)
        // giovanni(first_name('Giovanni'), last_name(X_0), age(1))
    }

    @Test
    fun `terms can be created via kotlin dsl`() {
        val person = prolog {
            "giovanni"("first_name"("giovanni"), "last_name"(X), "age"(1))
        }

        println(person)
        // giovanni(first_name(giovanni), last_name(X_0), age(1))
    }

    @Test
    fun `terms can be parsed from strings`() {
        val string = "1 + 2 * X"
        val termParser = TermParser.withOperators(
            Operator("+", XFY, 700),
            Operator("*", YFX, 400),
        )
        val term = termParser.parseTerm(string)

        println(term)
        // '+'(1, '*'(2, X_0))
    }

    @Test
    fun `handy ways exist for building particular terms such as lists`() {
        val list1 = Cons.of(
            Integer.of(1),
            Cons.of(
                Atom.of("a"),
                Cons.of(
                    Struct.of("f", Atom.of("x")),
                    EmptyList.instance
                )
            )
        )
        val list2 = LogicList.of(
            Integer.of(1), Atom.of("a"), Struct.of("f", Atom.of("x"))
        )

        println(list1) // [1, a, f(x)]
        println(list2) // [1, a, f(x)]
        println(list1 == list2) // true
    }

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
}
