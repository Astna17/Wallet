package org.bibliotheque.Entity;

public class Devise {
    private int idDevise;
    private String deviseName;
    private String code;

    public Devise(int idDevise, String deviseName, String code) {
        this.idDevise = idDevise;
        this.deviseName = deviseName;
        this.code = code;
    }

    public String getDeviseName() {
        return deviseName;
    }

    public void setDeviseName(String deviseName) {
        this.deviseName = deviseName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(int idDevise) {
        this.idDevise = idDevise;
    }
}
