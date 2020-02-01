

INSERT INTO usr (id, first_name, last_name) VALUES (1, 'Anthony', 'Soprano');
INSERT INTO usr (id, first_name, last_name) VALUES (2, 'Carmella', 'Soprano');
INSERT INTO usr (id, first_name, last_name) VALUES (3, 'AJ', 'Soprano');

INSERT INTO event (id, begin_date_time, end_date_time, name, description, usr_id)
VALUES (1, TIMESTAMP '2020-01-15 10:00', TIMESTAMP '2020-01-15 11:00', 'meeting', 'morning meeting with Polly', 1);
  INSERT INTO event (id, begin_date_time, end_date_time, name, description, usr_id)
VALUES (2, TIMESTAMP '2020-01-15 15:00', TIMESTAMP '2020-01-15 16:00', 'meeting', 'afternoon meeting with Christoper', 1);
  INSERT INTO event (id, begin_date_time, end_date_time, name, description, usr_id)
VALUES (3, TIMESTAMP '2020-01-16 22:00', TIMESTAMP '2020-01-16 23:30', 'restaurant', 'evening with Carmella', 1);
  INSERT INTO event (id, begin_date_time, end_date_time, name, description, usr_id)
VALUES (4, TIMESTAMP '2020-01-20 9:00', TIMESTAMP '2020-01-20 11:00', 'meeting', 'morning meeting with Polly', 1);
  INSERT INTO event (id, begin_date_time, end_date_time, name, description, usr_id)
VALUES (5, TIMESTAMP '2020-01-15 9:00', TIMESTAMP '2020-01-15 11:00', 'tennis', 'morning tennis with Adriana', 2);
   INSERT INTO event (id, begin_date_time, end_date_time, name, description, usr_id)
VALUES (6, TIMESTAMP '2020-01-15 17:00', TIMESTAMP '2020-01-15 18:00', 'shopping', 'afternoon shopping', 2);

