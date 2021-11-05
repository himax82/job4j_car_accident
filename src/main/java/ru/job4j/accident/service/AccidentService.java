package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public void save(Accident accident, String[] ids) {
        for (String s : ids) {
            accident.addRule(accidentHibernate.findRuleById(Integer.parseInt(s)));
        }
        accidentHibernate.save(accident);
    }

    public Collection<Accident> findAll() {
        return accidentHibernate.findAll();
    }

    public Collection<AccidentType> findAllType() {
        return accidentHibernate.findAllTypes();
    }

    public Collection<Rule> findAllRule() {
        return accidentHibernate.findAllRules();
    }

    public Accident findById(int id) {
        return accidentHibernate.findAccidentById(id);
    }

    public Rule findRuleById(int id) {
        return accidentHibernate.findRuleById(id);
    }

}