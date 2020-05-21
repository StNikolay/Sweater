delete from test.public.t_post;
delete from test.public.t_user_roles;
delete from test.public.t_role;
delete from test.public.t_user;
insert into test.public.t_user(id, email, password, username)
values (1, 'exist.user@example.com', '$2a$10$QgO3nSxHKGwcWF26b.woqungJv48U19.XsH6535hj4Zg0WhOLR9UC', 'ExistUser');
insert into test.public.t_user(id, email, password, username)
values (2, 'admin@example.com', '$2a$10$ceutQiBgR5w3F3q0Z9XzfuJctAjMaSWNrhrjpa3X3rMUVu80mYsZa', 'admin');
insert into test.public.t_role(id, name)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');
insert into test.public.t_user_roles(user_id, roles_id)
VALUES (1, 1);
insert into test.public.t_user_roles(user_id, roles_id)
VALUES (2, 1);
insert into test.public.t_user_roles(user_id, roles_id)
VALUES (2, 2);