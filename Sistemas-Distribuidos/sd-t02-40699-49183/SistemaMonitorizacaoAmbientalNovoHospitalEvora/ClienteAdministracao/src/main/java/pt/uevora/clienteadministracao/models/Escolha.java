package pt.uevora.clienteadministracao.models;

public enum Escolha {
    SIM("Sim"),
    NAO("Não");

    private final String label;

    Escolha(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }
}
