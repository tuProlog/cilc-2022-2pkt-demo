package it.unibo.tuprolog.demo.example5

import it.unibo.tuprolog.core.parsing.parseAsStruct
import it.unibo.tuprolog.solve.Solution
import it.unibo.tuprolog.solve.Solver
import it.unibo.tuprolog.solve.SolverFactory
import it.unibo.tuprolog.solve.concurrent.ConcurrentSolverFactory
import it.unibo.tuprolog.solve.library.Libraries
import it.unibo.tuprolog.solve.library.Library
import it.unibo.tuprolog.solve.primitive.PrimitiveWrapper

val Solver.Companion.concurrent: SolverFactory
    get() = ConcurrentSolverFactory

fun libraryOf(alias: String, primitive: PrimitiveWrapper<*>, vararg otherPrimitives: PrimitiveWrapper<*>) =
    Libraries.of(
        Library.aliased(
            alias,
            primitives = sequenceOf(primitive, *otherPrimitives).map { it.descriptionPair }.toMap()
        )
    )

fun libraryOf(primitive: PrimitiveWrapper<*>, vararg otherPrimitives: PrimitiveWrapper<*>) =
    libraryOf("default", primitive, *otherPrimitives)

fun Solver.solve(query: String): Sequence<Solution> = solve(query.parseAsStruct())
