package it.unibo.tuprolog.demo.example3

import it.unibo.tuprolog.core.parsing.parseAsRule
import it.unibo.tuprolog.core.parsing.parseAsStruct
import it.unibo.tuprolog.core.parsing.parseAsTerm
import it.unibo.tuprolog.unify.Unificator
import kotlin.test.Test

class HowToUseUnification {

    private val unificator = Unificator.default

    @Test
    fun `one may easily check weather two terms unify or not`() {
        val term1 = "[1, B, c, D]".parseAsTerm()
        val term2 = "[A, b, C, A]".parseAsTerm()

        println(unificator.match(term1, term2)) // true
        println(unificator.mgu(term1, term2)) // {A_1=1, B_0=b, C_0=c, D_0=1}
    }

    @Test
    fun `one may use unification to manipulate terms`() {
        val rule = "count([H | L], N) :- count(L, M), N is M + 1".parseAsRule()
        val head = "count([a, b, c], 3)".parseAsStruct()

        val substitution = unificator.mgu(rule.head, head)

        val rule1 = rule.apply(substitution)

        println(substitution) // {H_0=a, L_0=[b, c], N_0=3}
        println(rule1) // count([a, b, c], 3) :- (count([b, c], M_0), is(3, '+'(M_0, 1)))
    }
}