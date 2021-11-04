package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void save(Accident accident, HttpServletRequest req) {
        for (String s : req.getParameterValues("rIds")) {
            accident.addRule(accidentMem.findRuleById(Integer.parseInt(s)));
        }
        accidentMem.save(accident);
    }

    public Collection<Accident> findAll() {
        return accidentMem.findAll().stream()
                .sorted(Comparator.comparing(Accident::getId))
                .collect(Collectors.toList());
    }

    public Collection<AccidentType> findAllType() {
        return accidentMem.findAllType();
    }

    public Collection<Rule> findAllRule() {
        return accidentMem.findAllRule();
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public Rule findRuleById(int id) {
        return accidentMem.findRuleById(id);
    }

}