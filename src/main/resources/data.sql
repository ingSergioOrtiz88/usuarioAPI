insert into usuario (id,name,email,password,username)
values ('123e4567-e89b-12d3-a456-426655440000','sergio','sergio@test.com','admin','admin');

insert into roles (id,name)
values ('123e4567-e89b-12d3-a456-426655440001','ROLE_USER'),
 ('123e4567-e89b-12d3-a456-426655440002','ROLE_ADMIN');

insert into usuarios_roles (usuario_id,rol_id)
values ('123e4567-e89b-12d3-a456-426655440000','123e4567-e89b-12d3-a456-426655440001'),
 ('123e4567-e89b-12d3-a456-426655440000','123e4567-e89b-12d3-a456-426655440002');
