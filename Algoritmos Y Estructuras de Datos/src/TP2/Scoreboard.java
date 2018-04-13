package TP2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard {
    private Map<String,Integer> scores;
    private List<Match> matches;

    public Scoreboard(){
        scores = new HashMap<>();
        matches = new ArrayList<>();
    }

    public void addMatch(Match m) {
        matches.add(m);
    }

    public void addScore(String team, int score){
        scores.put(team,score);
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Match> getMatchesByTeam(String s){
        List<Match> result = new ArrayList<Match>();
        for (Match m : matches) {
            if (m.getLocal().equals(s) || m.getVisitor().equals(s)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<Match> getUnsolvedMatchesByTeam(String s) {
        List<Match> result = new ArrayList<Match>();
        for (Match m : matches) {
            if (!m.isSolved()) {
                if (m.getLocal().equals(s) || m.getVisitor().equals(s)) {
                    result.add(m);
                }
            }
        }
        return result;
    }


    public Map<String, Integer> getScores() {
        return scores;
    }

    public int getUnsolvedScoreByTeam(String s) {
        int result = scores.get(s);
        for (Match m: getMatchesByTeam(s)) {
            if (m.isSolved()) {
                if (m.getLocal().equals(s) && m.getResult() == 1 || m.getVisitor().equals(s) && m.getResult() == -1) {
                    result -= 3;
                } else if (m.getResult() == 0) {
                    result -= 1;
                }
            }
        }
        return result;
    }


    public boolean isFullySolved(){
        int sum = 0;
        int sum2 = 0;
        for(Match m: matches){
            if (!m.isSolved()) return false;
            if (m.getResult()==1 || m.getResult()==-1) sum += 3;
            if (m.getResult()==0) sum += 2;
        }
        for (String team: scores.keySet()) {
            sum2 += getUnsolvedScoreByTeam(team);
        }
        if (getSumOfScores()==sum && sum2==0) {
            return true;
        }
        return false;
    }

    public int getSumOfScores(){
        int result = 0;
        for(String s: scores.keySet()) {
            result += scores.get(s);
        }
        return result;
    }

    public boolean validate(){ //retorna falso si los valores del scoreboard son imposibles (ocurre debido al backtracking)
        int pointTotal = 0;
        int pointsByTeam;
        for (String team: scores.keySet()) {
            pointsByTeam = 0;
            for (Match m : getMatchesByTeam(team)) {
                if (m.getResult()==0) {
                    pointTotal+=1;
                    pointsByTeam+=1;
                } else if ((m.getResult()==1 && m.getLocal().equals(team)) || (m.getResult()==-1 && m.getVisitor().equals(team))) {
                    pointTotal+=3;
                    pointsByTeam+=3;
                }
            }
            if (pointsByTeam > scores.get(team)) return false;
        }
        if (pointTotal > getSumOfScores()) return false;
        if (pointTotal < 0) return false;
        return true;
    }

    public Scoreboard deepCopy(){
        Scoreboard newScoreboard = new Scoreboard();
        for (Match m: matches) {
            Match newMatch = new Match(m.getLocal(),m.getVisitor());
            newMatch.result = m.result;
            newMatch.solved = m.solved;
            newScoreboard.addMatch(newMatch);
        }
        for (String team: scores.keySet()) {
            int n = scores.get(team);
            newScoreboard.addScore(team,n);
        }
        return newScoreboard;
    }
}
