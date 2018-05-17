INSERT INTO "public"."user" ("id", "name", "email", "login", "password", "enabled") VALUES (1, 'Сидоров Иван Джейсонович', 'tmp1@mail.ru' , 'worldIvan777', 'pass', true );
INSERT INTO "public"."user" ("id", "name", "email", "login", "password", "enabled") VALUES (2, 'user2', 'tmp2@mail.ru' , 'login2','pass', true);
INSERT INTO "public"."user" ("id", "name", "email", "login", "password", "enabled") VALUES (3, 'admin1',  'tmp3@mail.ru' , 'login3','pass', true);

INSERT INTO "public"."authority" ("id", "authority") VALUES (1, 'User');
INSERT INTO "public"."authority" ("id", "authority") VALUES (2, 'Admin');

INSERT INTO "public"."user_authority" ("user_id", "role_id") VALUES (1, 1);
INSERT INTO "public"."user_authority" ("user_id", "role_id") VALUES (2, 1);
INSERT INTO "public"."user_authority" ("user_id", "role_id") VALUES (3, 2);


INSERT INTO "public"."reg_exp_level" ("id", number, "description") VALUES (1, 1, 'первый уровень');
INSERT INTO "public"."reg_exp_level" ("id", number, "description") VALUES (2, 2, 'второй уровень');
INSERT INTO "public"."reg_exp_level" ("id", number, "description") VALUES (3, 3, 'третий уровень');

INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (1, 1, true, 'Три ореха', 'Давным давно в три десятом царстве потерял Иван три ореха, да не смог отыскать. Помогите Ивану отыскать пренадлежащие ему орехи.', 1, 1);
INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (2, 2, true, 'name2', '1-2', 1, 1);
INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (3, 3, true, 'name3', '1-3', 1, 1);
INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (4, 1, true, 'name4', '2-1', 2, 1);
INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (5, 2, true, 'name5', '2-2', 2, 1);
INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (6, 3, true, 'name6', '2-3', 2, 1);
INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (7, 1, true, 'name7', '3-1', 3, 1);
INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (8, 2, true, 'name8', '3-2', 3, 1);
INSERT INTO "public"."reg_exp_task" ("id", "number", "enabled", "name", "description", "regexplevel_id", "author_id") VALUES (9, 3, true, 'name9', '3-3', 3, 1);

INSERT INTO "public"."reg_exp_task_answer" ("id", "answer", "reg_exp_task_id") VALUES (1, 'sss', 1);
INSERT INTO "public"."reg_exp_task_answer" ("id", "answer", "reg_exp_task_id") VALUES (2, 'sssa', 1);
INSERT INTO "public"."reg_exp_task_answer" ("id", "answer", "reg_exp_task_id") VALUES (3, 'sss', 2);


INSERT INTO "public"."user_solvedregexplevel" ("user_id", "solvedregexplevel_id") VALUES (1, 1);
INSERT INTO "public"."user_solvedregexplevel" ("user_id", "solvedregexplevel_id") VALUES (2, 1);
INSERT INTO "public"."user_solvedregexplevel" ("user_id", "solvedregexplevel_id") VALUES (2, 2);

INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (1, 1);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (1, 2);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (1, 3);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (1, 4);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (2, 1);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (2, 2);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (2, 3);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (2, 4);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (2, 5);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (2, 6);
INSERT INTO "public"."user_solvedregexptask" ("user_id", "solvedregexptask_id") VALUES (2, 7);

INSERT INTO "public"."comment" ("id", "text", "regexptask_id", "user_id") VALUES (1, 'комментарий1', 1, 1);
INSERT INTO "public"."comment" ("id", "text", "regexptask_id", "user_id") VALUES (2, 'комментарий2', 1, 1);
INSERT INTO "public"."comment" ("id", "text", "regexptask_id", "user_id") VALUES (3, 'комментарий3', 2, 1);
INSERT INTO "public"."comment" ("id", "text", "regexptask_id", "user_id") VALUES (4, 'комментарий4', 2, 3);

INSERT INTO "public"."like" ("id", "regexptask_id", "user_id") VALUES (1, 1, 1);
INSERT INTO "public"."like" ("id", "regexptask_id", "user_id") VALUES (2, 1, 1);
INSERT INTO "public"."like" ("id", "regexptask_id", "user_id") VALUES (3, 2, 1);
INSERT INTO "public"."like" ("id", "regexptask_id", "user_id") VALUES (4, 2, 3);

INSERT INTO "public"."reg_exp_task_excluded_strings" ("regexptask_id", "excluded_strings") VALUES (1, 'exclude1');
INSERT INTO "public"."reg_exp_task_excluded_strings" ("regexptask_id", "excluded_strings") VALUES (2, 'exclude2');
INSERT INTO "public"."reg_exp_task_excluded_strings" ("regexptask_id", "excluded_strings") VALUES (3, 'exclude3');

INSERT INTO "public"."reg_exp_task_match_strings" ("regexptask_id", "match_strings") VALUES (1, 'match1');
INSERT INTO "public"."reg_exp_task_match_strings" ("regexptask_id", "match_strings") VALUES (1, 'match1.2')
INSERT INTO "public"."reg_exp_task_match_strings" ("regexptask_id", "match_strings") VALUES (1, 'match1.3')
INSERT INTO "public"."reg_exp_task_match_strings" ("regexptask_id", "match_strings") VALUES (1, 'match1.4')
INSERT INTO "public"."reg_exp_task_match_strings" ("regexptask_id", "match_strings") VALUES (1, 'match1.5')
INSERT INTO "public"."reg_exp_task_match_strings" ("regexptask_id", "match_strings") VALUES (2, 'match2');
INSERT INTO "public"."reg_exp_task_match_strings" ("regexptask_id", "match_strings") VALUES (3, 'match3');

INSERT INTO "public"."reg_exp_task_required_sub_strings" ("regexptask_id", "required_sub_strings") VALUES (1, 'requiredstring1');
INSERT INTO "public"."reg_exp_task_required_sub_strings" ("regexptask_id", "required_sub_strings") VALUES (2, 'requiredstring2');
INSERT INTO "public"."reg_exp_task_required_sub_strings" ("regexptask_id", "required_sub_strings") VALUES (3, 'requiredstring3');

INSERT INTO "public"."reg_exp_task_excluded_answers" ("regexptask_id", "excluded_answers") VALUES (1, 'excluded1');
INSERT INTO "public"."reg_exp_task_excluded_answers" ("regexptask_id", "excluded_answers") VALUES (2, 'excluded2');
INSERT INTO "public"."reg_exp_task_excluded_answers" ("regexptask_id", "excluded_answers") VALUES (1, 'excluded3');

INSERT INTO "public"."useful_links" ("id", "description", "link") VALUES (1, 'Википедия', 'https://ru.wikipedia.org');
INSERT INTO "public"."useful_links" ("id", "description", "link") VALUES (2, 'луркмор', 'http://lurkmore.to');
INSERT INTO "public"."useful_links" ("id", "description", "link") VALUES (3, 'хабр', 'https://habr.com');

INSERT INTO "public"."usefullnks_regexptask" ("userfullinks_id", "regexptask_id") VALUES (1, 1);
INSERT INTO "public"."usefullnks_regexptask" ("userfullinks_id", "regexptask_id") VALUES (2, 1);
INSERT INTO "public"."usefullnks_regexptask" ("userfullinks_id", "regexptask_id") VALUES (3, 1);