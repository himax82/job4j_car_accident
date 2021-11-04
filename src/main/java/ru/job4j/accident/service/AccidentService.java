package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public void save(Accident accident, HttpServletRequest req) {
        for (String s : req.getParameterValues("rIds")) {
            accident.addRule(accidentJdbcTemplate.findRulesById(Integer.parseInt(s)));
        }
        accidentJdbcTemplate.saveAccident(accident);
    }

    public Collection<Accident> findAll() {
        return accidentJdbcTemplate.findAll();
    }

    public Collection<AccidentType> findAllType() {
        return accidentJdbcTemplate.findAllType();
    }

    public Collection<Rule> findAllRule() {
        return accidentJdbcTemplate.findAllRule();
    }

    public Accident findById(int id) {
        return accidentJdbcTemplate.findAccidentById(id);
    }

    public Rule findRuleById(int id) {
        return accidentJdbcTemplate.findRulesById(id);
    }

}