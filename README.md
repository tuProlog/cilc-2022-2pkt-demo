## Context

- Logic-based technologies (LBT) are either built
    * on top of Prolog
    * from Scratch

- Building LBT on top of Prolog means
    * be tailored on the way Prolog does stuff
    * either rely on FLI, or call Prolog from the command line, or write in Prolog

- Building LBT from scratch means
    * reinventing the wheel over and over again

## Why 2P-Kt

- Provide LP functialities
    * __as a library__, for mainstream programming languages
    * __individually__, to ease selective exploitation and combination
    * to as many platform/languages as possible, hence __maximising the potential audience__ 

- Provide an ecosystem of logic-based facilities aimed for re-use, e.g.
    * to create novel logic-based facilities
    * to exploit LP in every-day programming

- Avoid the need for reimplementing the same functionalities on a per-project basis
    * e.g. terms/clauses contruction/manipulation, unification, etc.
    * providing the facilities as ready-to-use, and customisable libraries

## Demonstration of 2P-Kt functionalities

We shall demonstrate "How to do stuff" in 2P-Kt, in particular concerning:
 
1. terms and clauses manipulation
    - creation, exploitation, parsing, and formatting

2. unification of terms and clauses

3. theories manipulation
    - creation, exploitation, parsing

4. resolution
    - e.g. in Prolog, or concurrent LP

### Terms 

#### Creation

![terms can be created via method calls](.img/e1-1.png)

![terms can be created via kotlin DSL](.img/e1-2.png)

![terms can be parsed from strings](.img/e1-3.png)

![handy ways exist for building particular terms such as lists](.img/e1-4.png)

#### Manipulation

![one may easily access inner properties for terms](.img/e1-5.png)

![one may easily rewrite terms by applying substitutions to variables](.img/e1-6.png)

#### Non-trivial usage example

![the first N terms in the Herbrand universe spawned by z|0 and s|1 are the first N Peano numbers](.img/e1-7.png)

### Clauses

#### Creation

![clauses can be created via method calls](.img/es2-1.png)

![clauses can be parsed from strings](.img/es2-2.png)

#### Manipulation

![one may easily access inner properties for clauses](.img/es2-3.png)

![one may easily get all the variables in a clause](.img/es2-4.png)

![one may rewrite clauses by substitutions application](.img/es2-5.png)

### Unification

![one may easily check weather two terms unify or not](.img/es3-1.png)

![one may use unification to manipulate terms](.img/es3-2.png)

### Theories

#### Creation

![theories may be parsed from strings](.img/es4-1.png)

![theories can be created via kotlin DSL](.img/es4-2.png)

#### Manipulation

![theories are iterable containers for clauses](.img/es4-3.png)

![theories may be queried via unification](.img/es4-4.png)

![clauses may be retracted from theory via unification](.img/es4-5.png)

![clauses may be asserted into theories](.img/es4-6.png)

#### Non-trivial usage example

![propositional theories may be easily converted in relational form](.img/es4-7.png)

### Solvers

#### Creation and usage

![using Prolog, let's compute all solutions for the N-queens problem](.img/es5-1.png)

![using concurrent LP, let's compute all solutions for the N-queens problem](.img/es5-2.png)

#### Extend solvers with primitives

![natural primitive](.img/es6-0.png)

![Prolog solvers can be extended with Kotlin functions having a logic API](.img/es6-1.png)

![concurrent LP solvers can be extended with Kotlin functions having a logic API](.img/es6-2.png)
