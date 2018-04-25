package ru.job4j.musicCourt.dao;

import ru.job4j.musicCourt.dao.implement.AddressImpl;
import ru.job4j.musicCourt.domain.Address;
import ru.job4j.musicCourt.domain.Model;

public class FindUserSqlSpecification implements SqlSpecification {

    private Model model;

    public FindUserSqlSpecification(Model model) {
        this.model = model;
    }

    @Override
    public String toSqlQuery() {

        String str = "";

        if (model.getClass() == Address.class) {
            AddressImpl addressImpl = new AddressImpl();
            int id = addressImpl.getIdByOtherParametr((Address) model);

            str = String.format(
                    "select id, name, age, address_id, role_id, musictype_id, password from users where address_id=%d",
                    id
            );
        }
        return str;
    }
}
