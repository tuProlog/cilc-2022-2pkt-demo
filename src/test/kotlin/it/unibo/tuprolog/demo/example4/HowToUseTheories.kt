package it.unibo.tuprolog.demo.example4

import it.unibo.tuprolog.theory.parsing.parseAsTheory
import kotlin.test.Test

class HowToUseTheories {
    @Test
    fun `propositional theories may be easily converted in relational form`() {
        val propositionalIris = """
            % sepal length, sepal width, petal length, petal width, class
            iris(5.1, 3.5, 1.4, 0.2, setosa).
            iris(7.0, 3.2, 4.7, 1.4, versicolor).
            iris(5.8, 2.7, 5.1, 1.9, virginica).
        """.parseAsTheory()

        val relationalIris = relationalise(
            propositionalIris,
            "iris",
            "sepal_length", "sepal_width", "petal_length", "petal_width", "class"
        )

        relationalIris.forEach(::println)
    }
}
