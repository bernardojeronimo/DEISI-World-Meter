package pt.ulusofona.aed.deisiworldmeter;

import java.io.File;

public class InputInvalido {
    String nome;
    int ok;
    int nOk;
    int primeiraNok;

    public InputInvalido(String nome, int ok, int nOk, int primeiraNok) {
        this.nome = nome;
        this.ok = ok;
        this.nOk = nOk;
        this.primeiraNok = primeiraNok;
    }

    @Override
    public String toString() {
        return nome + " | " + ok + " | " + nOk + " | " + primeiraNok;
    }
}
