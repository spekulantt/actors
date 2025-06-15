INSERT INTO actors (name, birth_date, nationality) VALUES
                                                       ('Jozef Kroner', '1924-03-20', 'slovenská'),
                                                       ('Marián Labuda', '1944-10-28', 'slovenská'),
                                                       ('Emília Vášáryová', '1942-05-18', 'slovenská');

INSERT INTO movies (title, release_year, genre, duration_minutes) VALUES
                                                                      ('Obchod na korze', 1965, 'Dráma', 128),
                                                                      ('Vesničko má středisková', 1985, 'Komédia', 98),
                                                                      ('Pelíšky', 1999, 'Komédia / Dráma', 115);

INSERT INTO movie_actor (movie_id, actor_id, role) VALUES
                                                       (1, 1, 'Tóno Brtko'),
                                                       (2, 2, 'Karel Pávek'),
                                                       (3, 3, 'Vilma Krausová');

INSERT INTO reviews (movie_id, author, content, rating, review_date) VALUES
                                                                         (1, 'Martin', 'Silný a nadčasový film ocenený Oscarom. Jozef Kroner bol geniálny.', 5.0, CURRENT_DATE),
                                                                         (2, 'Eva', 'Nestarnúca klasika plná humoru a láskavosti. Pán Labuda ako šofér Pávek je nezabudnuteľný.', 5.0, CURRENT_DATE),
                                                                         (3, 'Michal', 'Kultová vianočná komédia, ktorú si pozriem každý rok. Hlášky z tohto filmu pozná každý.', 4.5, CURRENT_DATE);