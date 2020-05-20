/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ankiety_PZ.query;

import com.Ankiety_PZ.generowaniePDF.NagrodyGenerowaniePDF;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Nagrody.
 *
 * @author KamDziok
 */
public class NagrodyQuery extends OperationInSession{

    private OperationsOnDataInEntity<Nagrody> modifyNagrody;

    public NagrodyQuery(){
        this.modifyNagrody = new OperationsOnDataInEntity<>();
    }
    
    public List<Nagrody> selectAll() throws HibernateException {
        return modifyNagrody.selectListHQL(("from Nagrody"));
    }

    public Nagrody selectByID(int id){
        return modifyNagrody.selectObjectHQL(("from Nagrody where ID = " + id));
    }

    public List<Nagrody> selectAllActive() {
        return modifyNagrody.selectListHQL(("from Nagrody as n where n.liczbaPunktow>=0"));
    }

    public Boolean addNagrody(Nagrody nagrody){
        return modifyNagrody.add(nagrody);
    }

    Boolean addNagrodyWithOutTransaction(Nagrody nagrody, Session session){
        return modifyNagrody.addWithOutTransaction(nagrody, session);
    }

    public Boolean updateNagrody(Nagrody nagrody){
        return modifyNagrody.update(nagrody);
    }

    Boolean updateNagrodyWithOutTransaction(Nagrody nagrody, Session session){
        return modifyNagrody.updateWithOutTransaction(nagrody, session);
    }

    public Boolean deleteNagrody(Nagrody nagrody){
        return modifyNagrody.delete(nagrody);
    }

    Boolean deleteNagrodyWithOutTransaction(Nagrody nagrody, Session session){
        return modifyNagrody.deleteWithOutTransaction(nagrody, session);
    }

    public Boolean deactivateNagrody(Nagrody nagrody){
        nagrody.setLiczbaPunktow(-1);
        //sprawdzic czy istnieje w uzytkownicy_nagrody
        return updateNagrody(nagrody);
    }

    public Boolean getNagrodyToUzytkownicy(Nagrody nagrody, Uzytkownicy uzytkownicy){
        boolean result = false;
        if(uzytkownicy.getLiczbaPunktow() >= nagrody.getLiczbaPunktow()) {
            try {
                session = openSession();
                transaction = beginTransaction(session);
                session.createSQLQuery("INSERT INTO `uzytkownicy_nagrody`(`ID_uzytkownika`, `ID_nagrody`) " +
                        "VALUES (:idUzytkownicy,:idNagrody)")
                        .setParameter("idUzytkownicy", uzytkownicy.getIdUzytkownika())
                        .setParameter("idNagrody", nagrody.getIdNagrody())
                        .executeUpdate();
                uzytkownicy.setLiczbaPunktow(uzytkownicy.getLiczbaPunktow() - nagrody.getLiczbaPunktow());
                new UzytkownicyQuery().updateUzytkownicyWithOutTransaction(uzytkownicy, session);
                new NagrodyGenerowaniePDF(nagrody, uzytkownicy);
                commitTransaction(transaction);
                result = true;
            } catch (Exception e) {
                rollbackTransaction(transaction);
                logException(e);
            } finally {
                closeSession(session);
            }
        }
        return result;
    }
}
