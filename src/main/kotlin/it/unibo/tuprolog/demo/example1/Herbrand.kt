package it.unibo.tuprolog.demo.example1

import it.unibo.tuprolog.core.Atom
import it.unibo.tuprolog.core.Indicator
import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.Term

/**
 * Given a set of [functors], this method lazily enumerates the partial Herbrand universe `Hᵢ`, up to index [max].
 * The algorithm implemented by this method is as follows:
 * 1. let `H₀` be the set of [Atom]s, attained from the 0-ary names in [functors]
 * 2. let `H₁` be the set of [Term]s attained by adding the [expansion] of `H₀` to `H₀` itself
 * 3. let `H₂` be the set of [Term]s attained by adding the [expansion] of `H₁` to `H₁` itself
 * 4. let `Hᵢ` be the set of [Term]s attained by adding the [expansion] of `Hᵢ₋₁` to `Hᵢ₋₁` itself
 * 5. and so on, up to `i == [max]
 * @param functors is the set of [Indicator]s spawning the Herbrand universe
 * @param max limits the computation of the Herbrand universe to `Hₘₐₓ` (defaults to [Int.MAX_VALUE])
 * @return a [Sequence] lazily enumerating all the terms in `Hₘₐₓ`
 */
fun herbrand(vararg functors: Indicator, max: Int = Int.MAX_VALUE): Sequence<Term> {
    val constants = functors.filter { it.indicatedArity == 0 }.map { Atom.of(it.indicatedName!!) }.toSet()
    val functorsWithArity = functors.filter { it.indicatedArity!! > 0 }.toSet()
    return sequence {
        val universe: MutableSet<Term> = mutableSetOf()
        var expansion: Set<Term> = constants
        var i = max
        while (i >= 0 && expansion.isNotEmpty()) {
            universe.addAll(expansion)
            yieldAll(expansion)
            expansion = expansion(universe, functorsWithArity).toSet()
            i--
        }
    }
}

/**
 * Given some partial Herbrand [universe] `Hᵢ`, and a set of [functors],
 * this method yields the sequence of [Term]s to be added to `Hᵢ` to compute `Hᵢ₊₁`, i.e.:
 * ```
 * Hᵢ₊₁ - Hᵢ
 * ```
 * @param universe the partial Herbrand universe `Hᵢ`, spawned by [functors]
 * @param functors a set of [Indicator]s, i.e. function names of given arities
 * @return the [Sequence] of [Term]s in `Hᵢ₊₁ - Hᵢ`
 */
internal fun expansion(universe: Set<Term>, functors: Set<Indicator>): Sequence<Term> {
    return sequence {
        for (indicator in functors) {
            for (arguments in cartesianPower(universe, indicator.indicatedArity!!)) {
                val term = Struct.of(indicator.indicatedName!!, arguments)
                if (term !in universe) {
                    yield(term)
                }
            }
        }
    }
}

/**
 * Given a set of [items] of type [T], and a non-negative integer [n],
 * this method computes the  [n]-ary [Cartesian power](https://en.wikipedia.org/wiki/Cartesian_product#n-ary_Cartesian_power ) of [items], i.e.:
 * ```
 * itemsⁿ = items × items × ... × items (n times)
 * ```
 * @param T any type
 * @param items a set of objects of type [T], coveniently represented as an [Iterable]
 * @param n a non-negative [Int] representing the order of the power
 * @return a [Sequence] of [List]s of [T] where each list contains [n] items
 */
internal fun <T> cartesianPower(items: Iterable<T>, n: Int): Sequence<List<T>> {
    return when {
        n < 0 -> throw IllegalArgumentException("Unexpected exponent: $n (should be non-negative)")
        n == 0 -> emptySequence()
        n == 1 -> items.asSequence().map { listOf(it) }
        else -> sequence {
            for (item in items) {
                for (smallerPower in cartesianPower(items , n - 1)) {
                    yield(listOf(item) + smallerPower)
                }
            }
        }
    }
}
