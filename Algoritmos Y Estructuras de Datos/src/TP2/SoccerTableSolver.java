package TP2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SoccerTableSolver {

    public Scoreboard solve(Scoreboard scoreboard, boolean backtrackingActive) { //este metodo se encarga de resolver mediante deducciones logicas todos los casos posibles. Al no poder resolver mas, utiliza backtracking
        if (scoreboard.isFullySolved()) {
            System.out.println("SOLVED 1");
            printScoreboard(scoreboard);
            //IMPORTANTE: Como no se puede salir de la recursion (por algun motivo) nuestro algoritmo encuentra la solucion correcta pero NO LA RETORNA
            //para demostrar que si funciona (a pesar de que los resultados retornados en el main estan incompletos) decidimos imprimir aqui adentro despues de resolver, los resultados resueltos
            return scoreboard;
        }
        if (!scoreboard.validate()) {
            return null; //si el scoreboard es invalido, retorna un null (sub-optimo). Solo ocurre al usar backtracking. El metodo backtrack se da cuenta si tomo un camino invalido mediante esto
        }
        boolean changed = false;
        for (String team: scoreboard.getScores().keySet()) { //por cada equipo que juega
            //LOGICA BASICA (Todas ganadas o perdidas)
            int unsolvedMatchesPlayed = scoreboard.getUnsolvedMatchesByTeam(team).size();
            int unsolvedMatchesScore = scoreboard.getUnsolvedScoreByTeam(team);
            if (unsolvedMatchesPlayed>0 && unsolvedMatchesScore==0) {
                for(Match m: scoreboard.getUnsolvedMatchesByTeam(team)) { //sabemos que el equipo perdio todos sus partidos restantes
                    changed = true;
                    if (m.getLocal().equals(team)) m.setResult(-1);
                    else m.setResult(1);
                }
            } else if (unsolvedMatchesPlayed>0 && unsolvedMatchesPlayed*3==unsolvedMatchesScore) {
                for (Match m : scoreboard.getUnsolvedMatchesByTeam(team)) { //sabemos que el equipo gano todos sus partidos restantes
                    changed = true;
                    if (m.getLocal().equals(team)) m.setResult(1);
                    else m.setResult(-1);
                }
            }

            //LOGICA MAS COMPLEJA (Compara combinaciones de partidos posibles entre equipos) no cubre absolutamente todos los casos deducibles, a falta de tiempo, pero es posible mejorarlo mas para reducir la cantidad de backtracking que es lo que genera mas operaciones
            int numberOfPossibleCombinations = possibleCombinations(unsolvedMatchesScore,unsolvedMatchesPlayed).size();
            if (numberOfPossibleCombinations == 1) {
                int[] combination = possibleCombinations(unsolvedMatchesScore,unsolvedMatchesPlayed).get(0); //sabemos que el primero y unico elemento es la unica combinacion posible
                if (combination[0] == 0 && combination[1] == 0 && combination[2] != 0) { //si no gano ninguna ni perdio ninguna pero sumo puntos -> empato todas
                    changed = true;
                    for (Match m: scoreboard.getUnsolvedMatchesByTeam(team)) {
                        m.setResult(0);
                    }
                }
                if (combination[0] > 0 && combination[2] > 0 && combination[1] == 0) { //si gano o empato partidas pero no perdio ninguna
                    for (Match m: scoreboard.getUnsolvedMatchesByTeam(team)) { //para cada partido no resuelto de este equipo
                        int opponentScore = scoreboard.getUnsolvedScoreByTeam(m.getOpponent(team));
                        int opponentMatches = scoreboard.getUnsolvedMatchesByTeam(m.getOpponent(team)).size();
                        if (possibleCombinations(opponentScore, opponentMatches).size() == 1) {
                            int[] opponentCombination = possibleCombinations(opponentScore, opponentMatches).get(0); //combinaciones posibles de su oponente
                            if (opponentCombination[0] > 0 && opponentCombination[2] > 0 && opponentCombination[1] == 0) { //si su oponente gana o empata partidos pero no pierde ninguno
                                //como no puede darse el caso de que los dos ganen al mismo tiempo, solo pueden haber empatado
                                m.setResult(0);
                                changed = true;
                            }
                        }
                    }
                }
                if (combination[0] == 0 && combination[2] > 0 && combination[1] > 0) { //caso converso, si no gano ninguna, pero perdio o empato
                    for (Match m: scoreboard.getUnsolvedMatchesByTeam(team)) { //para cada partido no resuelto de este equipo
                        int opponentScore = scoreboard.getUnsolvedScoreByTeam(m.getOpponent(team));
                        int opponentMatches = scoreboard.getUnsolvedMatchesByTeam(m.getOpponent(team)).size();
                        if (possibleCombinations(opponentScore, opponentMatches).size() == 1) {
                            int[] opponentCombination = possibleCombinations(opponentScore, opponentMatches).get(0); //combinaciones posibles de su oponente
                            if (opponentCombination[0] == 0 && opponentCombination[2] > 0 && opponentCombination[1] > 0) { //si su oponente pierde o empata partidos pero no gana ninguno
                                //como no puede darse el caso de que los dos pierdan al mismo tiempo, solo pueden haber empatado
                                m.setResult(0);
                                changed = true;
                            }
                        }
                    }
                }
                if (combination[0] > 0 && combination[1] > 0 && combination[2]==0) { //si gano y perdio partidas pero no empato ninguno
                    for (Match m: scoreboard.getUnsolvedMatchesByTeam(team)) { //para cada partido no resuelto de este equipo
                        int opponentScore = scoreboard.getUnsolvedScoreByTeam(m.getOpponent(team));
                        int opponentMatches = scoreboard.getUnsolvedMatchesByTeam(m.getOpponent(team)).size();
                        if (possibleCombinations(opponentScore, opponentMatches).size() == 1) {
                            int[] opponentCombination = possibleCombinations(opponentScore, opponentMatches).get(0); //combinaciones posibles de su oponente
                            if (opponentCombination[0] == 0 && opponentCombination[2] > 0 && opponentCombination[1] == 1) { //si su oponente pierde un solo partido
                                //como no puede darse el caso de que empaten, solo puede hacer ganado el primero
                                if (m.getLocal().equals(team)) {
                                    m.setResult(1);
                                    changed = true;
                                } else {
                                    m.setResult(-1);
                                    changed = true;
                                }

                            } else if (opponentCombination[0] == 1 && opponentCombination[2] > 0 && opponentCombination[1] == 0) { //si su oponente gana un solo partido
                                //como no puede darse el caso de que empaten, solo puede haber perdido el primero
                                if (m.getLocal().equals(team)) {
                                    m.setResult(-1);
                                    changed = true;
                                } else {
                                    m.setResult(1);
                                    changed = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (scoreboard.isFullySolved()) {
            System.out.println("SOLVED 2");
            printScoreboard(scoreboard); //cuando llega aqui, imprime el scoreboard
            //IMPORTANTE: Como no se puede salir de la recursion (por algun motivo) nuestro algoritmo encuentra la solucion correcta pero NO LA RETORNA
            //para demostrar que si funciona (a pesar de que los resultados retornados en el main estan incompletos) decidimos imprimir aqui adentro despues de resolver, los resultados resueltos
            return scoreboard;
        }
        if (!scoreboard.isFullySolved() && changed) {
            System.out.println("CALL 1");
            return solve(scoreboard,false);
        }
        if (backtrackingActive) {
            return null; //fallo el backtracking, null indica el fallo
        }
        else if (!changed && !backtrackingActive) { //si no se cambio ningun valor del scoreboard, significa que no hay mas deducciones logicas por hacer. Aqui usamos backtrack para encontrar las restantes
            //el !backtrackingActive previene que se hagan infinitos backtrack sin corte
            System.out.println("backtrack up!"); //indica que empezo el algoritmo de backtrack. Cada "camino" del backtrack ocurre al llamar solve() con backtrackingActive:true
            Scoreboard copyScoreboard = scoreboard.deepCopy(); //se hace una copia del Scoreboard para no perder el original en caso de que la prueba falle
            //aumenta en caso de que ninguno de los caminos para un backtrack particular retorne algo valido
            for (String team : copyScoreboard.getScores().keySet()) {
                int unsolvedPoints = copyScoreboard.getUnsolvedScoreByTeam(team);
                int unsolvedMatches = copyScoreboard.getUnsolvedMatchesByTeam(team).size();
                for (int i = 0; i < unsolvedMatches; i++) {
                    if (possibleCombinations(unsolvedPoints, unsolvedMatches).size() == 1) { //aplico backtracking para los que tienen una combinacion posible primero
                        int[] combination = possibleCombinations(unsolvedPoints, unsolvedMatches).get(0);
                        if (combination[0] > 0) { //si las partidas ganadas son mayores a 0 (pero no podemos determinar cuales gano o perdio porque hay varias posibilidades)
                            if (copyScoreboard.getUnsolvedMatchesByTeam(team).get(i).getLocal().equals(team)) {
                                copyScoreboard.getUnsolvedMatchesByTeam(team).get(i).setResult(1); //asumo que gano la partida en la posicion del ctr
                                Scoreboard result = solve(copyScoreboard, true);
                                if (result != null && !result.isFullySolved()) {
                                    System.out.println("Success 1!");
                                    return solve(copyScoreboard,true);
                                }
                                else if (result != null && result.isFullySolved()) {
                                    return result;
                                }
                                else {
                                    System.out.println("Failed 1!");
                                    copyScoreboard = scoreboard.deepCopy(); //"regresa" al punto inicial del scoreboard
                                }
                            } else if (copyScoreboard.getUnsolvedMatchesByTeam(team).get(i).getVisitor().equals(team)) {
                                copyScoreboard.getUnsolvedMatchesByTeam(team).get(i).setResult(-1);
                                Scoreboard result = solve(copyScoreboard, true);
                                if (result != null && !result.isFullySolved()) {
                                    System.out.println("Success 2!");
                                    return solve(copyScoreboard,true);
                                }
                                else if (result != null && result.isFullySolved()) {
                                    return result;
                                }
                                else {
                                System.out.println("Failed 2!");
                                copyScoreboard = scoreboard.deepCopy();
                                }
                            }
                        } else if (combination[1] > 0) { //si las partidas perdidas son mayores a 0
                            if (copyScoreboard.getUnsolvedMatchesByTeam(team).get(i).getLocal().equals(team)) {
                                copyScoreboard.getUnsolvedMatchesByTeam(team).get(i).setResult(-1); //asumo que perdio la partida en la posicion del ctr
                                Scoreboard result = solve(copyScoreboard, true);
                                if (result != null && !result.isFullySolved()) {
                                    System.out.println("Success 3!");
                                    return solve(copyScoreboard,true);
                                }
                                else if (result != null && result.isFullySolved()) {
                                    return result;
                                }
                                else {
                                    System.out.println("Failed 3!");
                                    copyScoreboard = scoreboard.deepCopy(); //"regresa" al punto inicial del scoreboard
                                }
                            } else if (copyScoreboard.getUnsolvedMatchesByTeam(team).get(i).getVisitor().equals(team)) {
                                copyScoreboard.getUnsolvedMatchesByTeam(team).get(i).setResult(1);
                                Scoreboard result = solve(copyScoreboard, true);
                                if (result != null && !result.isFullySolved()) {
                                    System.out.println("Success 4!");
                                    return solve(copyScoreboard,true);
                                }
                                else if (result != null && result.isFullySolved()) {
                                    return result;
                                }
                                else {
                                    System.out.println("Failed 4!");
                                    copyScoreboard = scoreboard.deepCopy();
                                }
                            }
                        }
                    }
                    //todo backtracking compuesto para cuando solo hay dos o mas combinaciones posibles para cada uno (Esto es CASI imposible de que ocurra con n de equipos y puntos pequenos)
                    //no lo incluyo por razones de tiempo y de complejidad
                }
            }
            System.out.println("whoops!");
        }
        return scoreboard;
    }

    public List<int[]> possibleCombinations(int pointsObtained, int matchesPlayed) { //Nota: Es bastante raro que haya mas de 1 combinacion posible.
        List<int[]> combinationList = new ArrayList<>();
        for (int matchesWon = pointsObtained/3;matchesWon>=0;matchesWon--) { //pointsObtained/3 es la cantidad maxima posible de partidas ganadas
            int matchesTied = pointsObtained-(matchesWon*3);
            int matchesLost = matchesPlayed-matchesWon-matchesTied;
            int totalPointValue = (matchesWon*3)+matchesTied;
            if (totalPointValue!=pointsObtained || matchesTied<0 || matchesLost<0) return combinationList; //si se cumple cualquiera de estas, la combinacion es invalida y se retorna la lista
            int aResult[] = new int[3]; //[ganadas,perdidas,empatadas]
            aResult[0] = matchesWon;
            aResult[1] = matchesLost;
            aResult[2] = matchesTied;
            combinationList.add(aResult);
        }
        return combinationList; //la lista de combinaciones nos da las combinaciones posibles validas para cierta cantidad de partidos jugados, y ciertos puntos obtenidos
        //lo usamos para maximizar la cantidad de datos en el programa para poder hacer la minima cantidad de backtracking posible (se hacen mas operaciones eligiendo un camino incorrecto y regresando, que usando un algoritmo "helper" para conseguir la mayor cantidad de datos posible)
    }

    public Scoreboard parse(String s) {
        Scoreboard parsedScoreboard = new Scoreboard();
        String[] allLines = s.split("\\r?\\n");
        String[] initialNums = allLines[0].split("\\s+");
        int numberOfTeams = Integer.valueOf(initialNums[0]);
        int numberOfMatches = Integer.valueOf(initialNums[1]);
        for (int i = 0;i<numberOfTeams;i++) {
            String[] lineValues = allLines[i+1].split("\\s+");
            String teamName = lineValues[0];
            int teamScore = Integer.valueOf(lineValues[1]);
            parsedScoreboard.addScore(teamName,teamScore);
        }
        for (int i = 0; i<numberOfMatches; i++) {
            String[] lineValues = allLines[i+1+numberOfTeams].split("\\s+");
            String localTeam = lineValues[0];
            String visitorTeam = lineValues[1];
            Match match = new Match(localTeam,visitorTeam);
            parsedScoreboard.addMatch(match);
        }
        return parsedScoreboard;
    }

    public void printScoreboard(Scoreboard scoreboard) {
        if (scoreboard.isFullySolved()) { //indica que esta completo y resuelto correctamente
            System.out.print("Resuelto!\n");
        }
        for (Match m: scoreboard.getMatches()) {
            if (m.getResult()==-1) {
                System.out.print(2);
            } else if (m.getResult()==0) {
                System.out.print("X");
            } else if (m.getResult()==1) {
                System.out.print(1);
            } else {
                System.out.print("[empty]"); //esto indica que m no tiene resultado
            }
        }
        System.out.println("");
    }

}
