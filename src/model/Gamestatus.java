package model;

public enum Gamestatus {

    NON_STARTED("Não iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETE("Completo");


    private String label;

    Gamestatus(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
