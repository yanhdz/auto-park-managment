package mx.prueba.autopark.enums;

public enum TipoAutoEnum {
    EMPRESA(1l,"EMPRESA"),
    RESIDENTE(2l,"RESIDENTE"),
    NO_RESIDENTE(3l, "NO_RESIDENTE");


    private Long value;
    private String tipo;


    TipoAutoEnum(Long value, String tipo) {
        this.value = value;
        this.tipo=tipo;
    }

    public Long getValue() {
        return this.value;
    }

    public String getTipo() {
        return this.tipo;
    }

}
