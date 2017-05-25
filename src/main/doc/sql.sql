drop database coffeeshop

insert into users(user_name,enabled,password) values('admin@gmail.com',1,'admin@gmail.com')

insert into product(id,description,price,product_name,product_type) values(1,'product_name1',11,'coffee1','coffee');
insert into product(id,description,price,product_name,product_type) values(2,'product_name2',12,'coffee2','coffee');
insert into product(id,description,price,product_name,product_type) values(3,'product_name3',13,'coffee3','coffee');
insert into product(id,description,price,product_name,product_type) values(4,'product_name4',14,'coffee4','coffee');
insert into product(id,description,price,product_name,product_type) values(5,'product_name5',15,'coffee5','coffee');
insert into product(id,description,price,product_name,product_type) values(6,'product_name6',16,'coffee6','coffee');
insert into product(id,description,price,product_name,product_type) values(7,'BREAKFAST',17,'BREAKFAST','BREAKFAST');
insert into product(id,description,price,product_name,product_type) values(8,'LUNCH',18,'LUNCH','LUNCH');
insert into product(id,description,price,product_name,product_type) values(9,'DINNER',19,'DINNER','DINNER');

update product set product_type='DINNER' where  product_type='coffee' 