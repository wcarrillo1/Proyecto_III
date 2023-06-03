package log;
import java.util.*;

public class ExpresionMath {
    private static final int EOL     = 0;
    private static final int VALOR   = 1;
    private static final int ABRE	 = 2;
    private static final int CIERRA  = 3;
    private static final int EXP     = 4;
    private static final int MULT    = 5;
    private static final int DIV     = 6;
    private static final int SUMA    = 7;
    private static final int RESTA   = 8;
    private static PrecedenciaOperadores[] tablaPrecedencia = new PrecedenciaOperadores[] {
            new PrecedenciaOperadores(0, -1),  // EOL
            new PrecedenciaOperadores(0, 0),   // VALOR
            new PrecedenciaOperadores(100, 0), // ABRE
            new PrecedenciaOperadores(0, 99),  // CIERRA
            new PrecedenciaOperadores(6, 5),   // EXP
            new PrecedenciaOperadores(3, 4),   // MULT
            new PrecedenciaOperadores(3, 4),   // DIV
            new PrecedenciaOperadores(1, 2),   // SUMA
            new PrecedenciaOperadores(1, 2)    // RESTA
    };
    private Stack pilaOperadores;
    private Stack pilaPosfija;
    private StringTokenizer str;
    private static class PrecedenciaOperadores {
        public int simboloEntrada;
        public int cimaPila;
        public PrecedenciaOperadores(int simboloEntrada, int cimaPila) {
            this.simboloEntrada = simboloEntrada;
            this.cimaPila = cimaPila;
        }
    }
    private static class Token {
        private int tipo = EOL;
        private double valor = 0;
        public Token() {
            this(EOL);
        }
        public Token(int t) {
            this(t, 0);
        }
        public Token(int t, double v) {
            tipo = t;
            valor = v;
        }
        public int getTipo() {
            return tipo;
        }
        public double getValor() {
            return valor;
        }
    }
    private static class EvaluadorToken {
        private StringTokenizer str;
        public EvaluadorToken(StringTokenizer is) {
            str = is;
        }
        public Token getToken() {
            double valorActual;
            if(!str.hasMoreTokens())
                return new Token();
            String s = str.nextToken();
            if(s.equals(" ")) return getToken();
            if(s.equals("^")) return new Token(EXP);
            if(s.equals("/")) return new Token(DIV);
            if(s.equals("*")) return new Token(MULT);
            if(s.equals("(")) return new Token(ABRE);
            if(s.equals(")")) return new Token(CIERRA);
            if(s.equals("+")) return new Token(SUMA);
            if(s.equals("-")) return new Token(RESTA);
            try {
                valorActual = Double.parseDouble(s);
            }
            catch(NumberFormatException e) {
                System.err.println("Error en Numero!!!");
                return new Token();
            }
            return new Token(VALOR, valorActual);
        }
    }
    public ExpresionMath(String s) {
        pilaOperadores = new Stack();
        pilaPosfija = new Stack();
        str = new StringTokenizer(s, "+*-/^() ", true);
        pilaOperadores.push(new Integer(EOL));
    }
    public double getValor() {
        EvaluadorToken tok = new EvaluadorToken(str);
        Token ultimoToken;
        do {
            ultimoToken = tok.getToken();
            procesarToken(ultimoToken);
        } while(ultimoToken.getTipo() != EOL);
        if(pilaPosfija.isEmpty()) {
            System.err.println("Falta un Operador!");
            return 0;
        }
        double resultado = posfijaTopAndPop();
        if(!pilaPosfija.isEmpty())
            System.err.println("Warning: Falta operador!");
        return resultado;
    }
    private void procesarToken(Token ultimoToken){
        int topOp;
        int lastType = ultimoToken.getTipo( );
        switch(lastType) {
            case VALOR:
                pilaPosfija.push(new Double(ultimoToken.getValor()));
                return;

            case CIERRA:
                while((topOp = operadoresTop()) != ABRE && topOp != EOL)
                    binaryOp(topOp);
                if(topOp == ABRE)
                    pilaOperadores.pop();
                else
                    System.err.println("Falta un ABRE");
                break;

            default:    // Operador
                while(tablaPrecedencia[lastType].simboloEntrada <= tablaPrecedencia[topOp = operadoresTop()].cimaPila)
                    binaryOp(topOp);
                if(lastType != EOL)
                    pilaOperadores.push(new Integer(lastType));
                break;
        }
    }

    private double getTop() {
        if (pilaPosfija.isEmpty()) {
            System.err.println("Falta un Operador");
            return 0;
        }
        return posfijaTopAndPop();
    }
    private void binaryOp(int topOp) {
        double rhs = getTop();
        double lhs = getTop();

        if( topOp == ABRE ) {
            System.err.println("Parentesis desbalanceado");
            pilaOperadores.pop();
            return;
        }

        if(topOp == EXP)
            pilaPosfija.push(new Double(Math.pow(lhs, rhs)));
        else if(topOp == SUMA)
            pilaPosfija.push(new Double(lhs + rhs));
        else if(topOp == RESTA)
            pilaPosfija.push(new Double(lhs - rhs));
        else if(topOp == MULT)
            pilaPosfija.push(new Double(lhs * rhs));
        else if(topOp == DIV)
            if(rhs != 0 )
                pilaPosfija.push(new Double(lhs / rhs));
            else {
                System.err.println("Division por cero");
                pilaPosfija.push(new Double(lhs));
            }
        pilaOperadores.pop();
    }
    private double posfijaTopAndPop() {
        return ((Double)(pilaPosfija.pop())).doubleValue();
    }
    private int operadoresTop() {
        return ((Integer)(pilaOperadores.peek())).intValue();
    }
}