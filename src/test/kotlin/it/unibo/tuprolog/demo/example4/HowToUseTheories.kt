package it.unibo.tuprolog.demo.example4

import it.unibo.tuprolog.theory.parsing.parseAsTheory
import org.junit.Test

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
        // iris(instance0) :- true
        // sepal_length(instance0, 5.1) :- true
        // sepal_width(instance0, 3.5) :- true
        // petal_length(instance0, 1.4) :- true
        // petal_width(instance0, 0.2) :- true
        // class(instance0, setosa) :- true

        // iris(instance1) :- true
        // sepal_length(instance1, 7.0) :- true
        // sepal_width(instance1, 3.2) :- true
        // petal_length(instance1, 4.7) :- true
        // petal_width(instance1, 1.4) :- true
        // class(instance1, versicolor) :- true

        // iris(instance2) :- true
        // sepal_length(instance2, 5.8) :- true
        // sepal_width(instance2, 2.7) :- true
        // petal_length(instance2, 5.1) :- true
        // petal_width(instance2, 1.9) :- true
        // class(instance2, virginica) :- true
    }
}