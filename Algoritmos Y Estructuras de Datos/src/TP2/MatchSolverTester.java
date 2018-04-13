package TP2;

import java.util.List;

public class MatchSolverTester {
    public static void main(String[] args) {

        SoccerTableSolver sts = new SoccerTableSolver();
        //NOTA: El parser se encarga de inicializar los componentes del scoreboard de manera adecuada y retornarlo segun el string proporcionado.
        Scoreboard primerScoreboard = sts.parse("6 10\n" +
                "Madrid 8\n" +
                "Barcelona 8\n" +
                "Valencia 4\n" +
                "Sevilla 4\n" +
                "Deportivo 2\n" +
                "Betis 0\n" +
                "Madrid Deportivo\n" +
                "Barcelona Sevilla\n" +
                "Betis Madrid\n" +
                "Betis Sevilla\n" +
                "Barcelona Deportivo\n" +
                "Betis Barcelona\n" +
                "Madrid Barcelona\n" +
                "Sevilla Deportivo\n" +
                "Deportivo Valencia\n" +
                "Madrid Valencia");
        sts.solve(primerScoreboard,false);
        System.out.println("\nResultado final, para 6 equipos y 10 partidas: ");
        sts.printScoreboard(primerScoreboard);
        System.out.println("-------------------------");

        Scoreboard segundoScoreboard = sts.parse("10 18\n" +
                "Deportivo 11\n" +
                "Betis 9\n" +
                "Sevilla 6\n" +
                "AtlMadrid 6\n" +
                "Barcelona 5\n" +
                "AthBilbao 4\n" +
                "Madrid 2\n" +
                "Espanyol 2\n" +
                "Valencia 1\n" +
                "RealSociedad 1\n" +
                "Deportivo RealSociedad\n" +
                "Barcelona AtlMadrid\n" +
                "AthBilbao Espanyol\n" +
                "AtlMadrid Madrid\n" +
                "Deportivo Madrid\n" +
                "Betis Deportivo\n" +
                "RealSociedad Espanyol\n" +
                "Valencia Deportivo\n" +
                "Deportivo Barcelona\n" +
                "Madrid Barcelona\n" +
                "Espanyol Sevilla\n" +
                "Sevilla AtlMadrid\n" +
                "Madrid Betis\n" +
                "Valencia AthBilbao\n" +
                "Betis AthBilbao\n" +
                "Valencia AtlMadrid\n" +
                "RealSociedad Betis\n" +
                "Barcelona Betis");
        sts.solve(segundoScoreboard,false);
        //Este print retorna un valor incompleto porque el solve de la linea anterior (por algun motivo que no pudimos resolver) recibe de nuevo el scoreboard ANTES del backtracking
        //sin embargo, el backtracking funciona adecuadamente y SI se resuelve el scoreboard, eso lo demostramos imprimiendo el scoreboard desde adentro del algoritmo, dada la condicion isFullySolved()==true
        System.out.println("Resultado final para 10 equipos y 18 partidos: [esta incompleto pero se resolvio adecuadamente en la linea anterior]");
        sts.printScoreboard(segundoScoreboard);
        System.out.println("\n-----------------\nInterpretacion de los resultados impresos anteriormente (en el codigo esta mejor explicado, revisar cada linea con sout() dentro del algoritmo):" +
                "\nCALL 1 es una llamada recursiva a la LOGICA del algoritmo. Aqui NO se hace backtracking, sino que se hallan nuevos datos mediante deducciones usando datos existentes." +
                "\nbacktrack up! se refiere a una entrada al if de backtracking. Cada backtrack up! termina con un SUCCESS, FAILED, o whoops!, dependiendo si el algoritmo es exitoso o no, o si se llega al final del if (por algun motivo)" +
                "\nComo observamos en el codigo impreso anteriormente, hay varios backtrack anidados (probablemente sea eso la causa del error, de que no retorna el scoreboard resuelto, sino el primero que se intenta backtrackear)" +
                "\nLos backtrack up! que terminan en un whoops!, NO HACEN NADA (hasta donde sabemos), por ende, no cuentan hacia el total de 'caminos tomados'." +
                "\nEl numero total de caminos tomados para 10 equipos y 18 partidos, o 'intentos', seria 5, que es mucho menos de lo que pedia la consigna (20 casos o menos)" +
                "\nLos dos ejercicios resueltos son los dos ejemplos que figuran en el TP. Para verificar que estan correctos, resolvimos a mano los dos. El primero sale por deduccion logica (sin backtracking), el segundo sale por deducciones y un solo intento de backtracking (una decision)." +
                "\nEl algoritmo que hicimos funciona optimamente para el primer ejercicio pero no optimamente para el segundo, puesto que era posible resolverlo en 2 caminos como maximo." +
                "\n--------------\n" +
                "POSDATA: Despues de implementar el metodo parse (a ultimo momento) la resolucion del algoritmo CAMBIO! (inesperado). Ahora se resuelve en 4 'caminos' y aparecen Success/Failed 1 y 2 que antes no aparecian (solo habian 3 y 4)\n" +
                "A pesar de esto, el metodo se sigue resolviendo de manera adecuada, con excepcion del retorno.");

    }
}
