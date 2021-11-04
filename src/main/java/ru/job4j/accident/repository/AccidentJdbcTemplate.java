package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            save(accident);
        } else {
            update(accident);
        }
    }

    private Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement
                    = connection.prepareStatement("INSERT INTO accidents(name, text, address, accident_type_id) "
                    +"VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getType().getId());
            return statement;
        }, keyHolder);
        accident.setId((int) keyHolder.getKeys().get("id"));
        createRules(accident);
        return accident;
    }

    private Accident update(Accident accident) {
        jdbc.update("update accidents set name = ?, text = ?, address = ?, accident_type_id = ?" +
                        " where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId());
        updateRules(accident);
        return accident;
    }

    private void createRules(Accident accident) {
        for (Rule r : accident.getRules()) {
            jdbc.update("INSERT INTO accidents_rules(accident_id, rule_id) "
                    + "VALUES (?, ?)", accident.getId(), r.getId());
        }
    }

    private void updateRules(Accident accident) {
        jdbc.update("DELETE FROM accidents_rules WHERE accident_id = ?",
                accident.getId());
        createRules(accident);
        }

    public List<Accident> findAll() {
        return jdbc.query("select a.id as id, a.name as name, a.text as text, a.address as address, a.accident_type_id as type_id, " +
                        "t.name as type_name from accidents as a left join types as t on a.accident_type_id = t.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText("text");
                    accident.setAddress("address");
                    accident.setType(new AccidentType(
                            rs.getInt("type_id"),
                            rs.getString("type_name")
                    ));
                    findRulesByAccidentId(accident.getId()).forEach(accident::addRule);
                    return accident;
                });
    }

    private Collection<Rule> findRulesByAccidentId(int id) {
        Object[] args = {id};
        return jdbc.query("select r.id as id, r.name as name from rules as r" +
                        " join accidents_rules as a on r.id = a.rule_id where a.accident_id = ?", args,
                (rs, row) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }

    public Accident findAccidentById(int id) {
        final Object[] args = {id};
        Accident accident = jdbc.queryForObject("select a.id as id, a.name as name, a.text as text, a.address as address, a.accident_type_id as type_id, "
                        + "t.name as type_name from accidents as a left join types as t on a.accident_type_id = t.id where a.id = ?", args,
                (rs, row) -> {
                    Accident ac = new Accident(
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address"),
                            new AccidentType(
                                    rs.getInt("type_id"),
                                    rs.getString("type_name")
                            )
                    );
                    ac.setId(rs.getInt("id"));
                    findRulesByAccidentId(ac.getId()).forEach(ac::addRule);
                    return ac;
                });
        return accident;
    }

    public Collection<AccidentType> findAllType() {
        return jdbc.query("select * from types",
                (rs, row) ->
                        new AccidentType(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
        );
    }

    public Collection<Rule> findAllRule() {
        return jdbc.query("SELECT * FROM rules",
                (rs, row) ->
                        Rule.of(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
        );
    }

    public Rule findRulesById(int id) {
        Object[] args = {id};
        return jdbc.queryForObject("select * from rules where id = ?", args,
                    (rs, row) ->
            Rule.of(rs.getInt("id"), rs.getString("name"))
        );
    }
}
