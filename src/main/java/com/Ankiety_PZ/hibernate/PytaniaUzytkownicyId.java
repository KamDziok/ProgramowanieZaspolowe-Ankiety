package com.Ankiety_PZ.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PytaniaUzytkownicyId implements Serializable {

    @Column(name = "ID_pytania")
    private Integer idPytania;

    @Column(name = "ID_uzytkownika")
    private Integer idUzytkownika;

    public PytaniaUzytkownicyId() {
    }

    public PytaniaUzytkownicyId(Integer idPytania, Integer idUzytkownika) {
        this.idPytania = idPytania;
        this.idUzytkownika = idUzytkownika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PytaniaUzytkownicyId that = (PytaniaUzytkownicyId) o;
        return Objects.equals(idPytania, that.idPytania) &&
                Objects.equals(idUzytkownika, that.idUzytkownika);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPytania, idUzytkownika);
    }
}