INSERT INTO users (user_name,password,enabled,name,last_name,email) VALUES ('hampcode','$2a$10$VzsBpvJXVkXaAy29XooHZORsE6IzF.2ZZMB1drnURqbCyhWEazkdK',true,'Henry Antonio','Mendoza Puerta','hmendo81@gmail.com');
INSERT INTO users (user_name,password,enabled,name,last_name,email)VALUES ('usercode','$2a$10$R8CUVucLB2B7omTl8H0CPuSPqxquNKR2JuUAIcg.kquUT3xxnROdi',true,'User','Code','usercode@gmail.com');


INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');


INSERT INTO user_roles (user_id,role_id) VALUES (1,1);
INSERT INTO user_roles (user_id,role_id) VALUES (1,2);
INSERT INTO user_roles (user_id,role_id) VALUES (2,2);
