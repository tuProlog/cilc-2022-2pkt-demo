package it.unibo.tuprolog.demo.example5

import it.unibo.tuprolog.theory.parsing.parseAsTheory

object NotableTheories {
    val nQueens = """      
        queens(N, Queens) :-
            length(Queens, N),
            board(Queens, Board, 0, N, _, _),
            queens(Board, 0, Queens).
        
        board([], [], N, N, _, _).
        board([_|Queens], [Col-Vars|Board], Col0, N, [_|VR], VC) :-
            Col is Col0+1,
            functor(Vars, f, N),
            constraints(N, Vars, VR, VC),
            board(Queens, Board, Col, N, VR, [_|VC]).
        
        constraints(N, Row, [R|Rs], [C|Cs]) :-
            N > 0,
            arg(N, Row, R-C),
            M is N-1,
            constraints(M, Row, Rs, Cs).
        constraints(0, _, _, _).
        
        sel(E, [E|T1], T1).
        sel(E, [L1|T1], [L1|T2]) :- sel(E, T1, T2).
        
        queens([], _, []).
        queens([C|Cs], Row0, [Col|Solution]) :-
            Row is Row0+1,
            sel(Col-Vars, [C|Cs], Board),
            arg(Row, Vars, Row-Row),
            queens(Board, Row, Solution).
        
        length(List, N) :- length(List, 0, N).
        length([_|T], C, N) :-
            C < N,
            C1 is C + 1,
            length(T, C1, N).
        length([], N, N).
    """.parseAsTheory()
}