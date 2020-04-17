package com.Ankiety_PZ.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OdpowiedziUzytkownicyId implements Serializable {

    @Column(name = "ID_odpowiedzi")
    private Integer idOpowiedzi;

    @Column(name = "ID_uzytkownika")
    private Integer idUzytkownika;


    public OdpowiedziUzytkownicyId() {
    }

    public OdpowiedziUzytkownicyId(Integer idOpowiedzi, Integer idUzytkownika) {
        this.idOpowiedzi = idOpowiedzi;
        this.idUzytkownika = idUzytkownika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OdpowiedziUzytkownicyId that = (OdpowiedziUzytkownicyId) o;
        return Objects.equals(idOpowiedzi, that.idOpowiedzi) &&
                Objects.equals(idUzytkownika, that.idUzytkownika);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOpowiedzi, idUzytkownika);
    }
}