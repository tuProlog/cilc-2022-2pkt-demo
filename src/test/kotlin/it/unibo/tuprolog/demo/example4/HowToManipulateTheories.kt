package it.unibo.tuprolog.demo.example4

import it.unibo.tuprolog.core.parsing.parseAsStruct
import it.unibo.tuprolog.theory.parsing.parseAsTheory
import kotlin.test.Test

class HowToManipulateTheories {
    @Test
    fun `theories are iterable containers for clauses`() {
        val theory = """
            person(giovanni, ciatto, disi).
            person(giovanni, sartor, dsg).
            person(andrea, omicini, disi).
        """.parseAsTheory()

        for (clause in theory) {
            println(clause)
        }
        // person(giovanni, ciatto, disi) :- true
        // person(giovanni, sartor, dsg) :- true
        // person(andrea, omicini, disi) :- true
    }

    @Test
    fun `theories may be queried via unification`() {
        val theory = """
            person(giovanni, ciatto, disi).
            person(giovanni, sartor, dsg).
            person(andrea, omicini, disi).
        """.parseAsTheory()

        val query1 = "person(_, _, disi)".parseAsStruct()
        val query2 = "person(giovanni, _, _)".parseAsStruct()
        val query3 = "person(andrea, _, dsg)".parseAsStruct()

        println(theory[query1].toList())
        // [person(giovanni, ciatto, disi) :- true, person(andrea, omicini, disi) :- true]
        println(theory[query2].toList())
        // [person(giovanni, ciatto, disi) :- true, person(giovanni, sartor, dsg) :- true]
        println(theory[query3].toList())
        // []
    }

    @Test
    fun `clauses may be retracted from theory via unification`() {
        val theory = """
            person(giovanni, ciatto, disi).
            person(giovanni, sartor, dsg).
            person(andrea, omicini, disi).
        """.parseAsTheory()

        val query = "person(giovanni, _, _)".parseAsStruct()

        val retractResult = theory.retractAll(query)

        println(retractResult.clauses?.toList())
        // [person(giovanni, ciatto, disi) :- true, person(giovanni, sartor, dsg) :- true]
        println(retractResult.theory)
        // IndexedTheory{ person(andrea, omicini, disi) :- true }
    }

    @Test
    fun `clauses may be asserted into theories`() {
        val theory = """
            person(giovanni, sartor, dsg).
        """.parseAsTheory()

        val fact1 = "person(giovanni, ciatto, disi)".parseAsStruct()
        val fact2 = "person(andrea, omicini, disi).".parseAsStruct()

        val newTheory = theory.assertA(fact1).assertZ(fact2)

        newTheory.forEach { println(it) }
        // person(giovanni, ciatto, disi) :- true
        // person(giovanni, sartor, dsg) :- true
        // person(andrea, omicini, disi) :- true
    }
}