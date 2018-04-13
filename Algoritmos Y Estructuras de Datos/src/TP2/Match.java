package TP2;

public class Match {
    private String local;
    private String visitor;
    int result; //1 if local win, 0 if tie, -1 if visitor win
    boolean solved;
    public Match(String a, String b) {
        local = a;
        visitor = b;
        solved = false;
        result = 999; //placeholder, para indicar que no fue setteado el resultado (si no imprime 0, como si hubiera sido un empate) [mala practica de programacion, lo uso para testear]
    }

    public String getLocal() {
        return local;
    }

    public String getVisitor() {
        return visitor;
    }

    public String getOpponent(String s) { //retorna el oponente del equipo dado (muy util para el diseno que hice)
        if (!s.equals(local) && !s.equals(visitor)) throw new RuntimeException();
        if (s.equals(local)) return visitor;
        if (s.equals(visitor)) return local;
        return null; //no sucede, placeholder para compilar
    }

    public void setResult(int result){
        this.result = result;
        solved = true;
    }

    public int getResult(){
        return result;
    }

    public boolean isSolved() {
        return solved;
    }
}
