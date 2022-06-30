package it.unibo.tuprolog.demo.example5

import it.unibo.tuprolog.core.parsing.parseAsStruct
import it.unibo.tuprolog.solve.Solution
import it.unibo.tuprolog.solve.Solver
import it.unibo.tuprolog.solve.concurrent.ConcurrentSolverFactory
import kotlin.test.Test

class HowToSolveQueries {

    private val N = 8

    @Test
    fun `using Prolog, let's compute all solutions for the N-queens problem`() {
        val solver = Solver.prolog.solverWithDefaultBuiltins(
            staticKb = NotableTheories.nQueens
        )
        val query = "queens($N, L)".parseAsStruct()

        solver.solve(query)
            .filterIsInstance<Solution.Yes>()
            .map { it.substitution.getByName("L") }
            .forEach(::println)
    }

    @Test
    fun `using concurrent LP, let's compute all solutions for the N-queens problem`() {
        val solver = Solver.concurrent.solverWithDefaultBuiltins(
            staticKb = NotableTheories.nQueens
        )
        val query = "queens($N, L)".parseAsStruct()

        solver.solve(query)
            .filterIsInstance<Solution.Yes>()
            .map { it.substitution.getByName("L") }
            .forEach(::println)
    }
}