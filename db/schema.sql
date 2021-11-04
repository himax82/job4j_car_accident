CREATE TABLE IF NOT EXISTS types(
                                             id SERIAL PRIMARY KEY,
                                             name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS accidents(
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        text TEXT NOT NULL,
                                        address VARCHAR(255) NOT NULL,
                                        accident_type_id INT REFERENCES types(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS rules(
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS accidents_rules(
                                              accident_id INT REFERENCES accidents(id) NOT NULL,
                                              rule_id INT REFERENCES rules(id) NOT NULL
);