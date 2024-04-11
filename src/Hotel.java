public class Hotel {
    private final String nome;
    private final boolean spa;

    public Hotel(String nome, boolean spa) {
        this.nome = nome;
        this.spa = spa;
    }

    public String getNome() {
        return nome;
    }

    public boolean hasSpa() {
        return spa;
    }
}
