insert into giftOrganizer.accounts (first_name, last_name, email, role, active) values ('Carolyn', 'Harris', 'ccharris27@gmail.com', 'USER', true);
insert into giftOrganizer.accounts (first_name, last_name, email, role, active) values ('Tester', 'tester', 'cchtesting27@gmail.com', 'USER', true);

insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id) values ('Jodie', 'Harris', 'jodie.har@gmail.com', '12/18/1961', (select id from giftOrganizer.accounts where email = 'ccharris27@gmail.com'));
insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id) values ('Kirk', 'Harris', 'alf176@aol.com', '06/17/1957', (select id from giftOrganizer.accounts where email = 'ccharris27@gmail.com'));
insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id, group_tag, notes) values ('Louise', 'Belcher', 'lbelcher@gmail.com', '12/18/2008', (select id from giftOrganizer.accounts where email = 'cchtesting27@gmail.com'), 'Bobs Burgers', 'ask Teddy for help');
insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id, group_tag) values ('Linda', 'Belcher', 'linbelcher@aol.com', '06/18/1971', (select id from giftOrganizer.accounts where email = 'cchtesting27@gmail.com'), 'Bobs Burgers');
insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id, group_tag) values ('Bob', 'Belcher', 'bobsburgers@gmail.com', '12/18/1968', (select id from giftOrganizer.accounts where email = 'cchtesting27@gmail.com'), 'Bobs Burgers');
insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id, group_tag) values ('Tina', 'Belcher', 'tbelcher@aol.com', '06/18/2003', (select id from giftOrganizer.accounts where email = 'cchtesting27@gmail.com'), 'Bobs Burgers');
insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id, group_tag) values ('Gene', 'Belcher', 'musicman@gmail.com', '12/18/2005', (select id from giftOrganizer.accounts where email = 'cchtesting27@gmail.com'), 'Bobs Burgers');


insert into giftOrganizer.events (name, budget, user_id) values ('Christmas', 400.00, (select id from giftOrganizer.accounts where email = 'ccharris27@gmail.com'));
insert into giftOrganizer.events (name, budget, user_id) values ('Xmas', 400.00, (select id from giftOrganizer.accounts where email = 'cchtesting27@gmail.com'));

insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Jodie'), (select id from giftOrganizer.events where name = 'Christmas'));
insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Kirk'), (select id from giftOrganizer.events where name = 'Christmas'));
insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Tina'), (select id from giftOrganizer.events where name = 'Xmas'));
insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Louise'), (select id from giftOrganizer.events where name = 'Xmas'));
insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Bob'), (select id from giftOrganizer.events where name = 'Xmas'));
insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Gene'), (select id from giftOrganizer.events where name = 'Xmas'));
insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Linda'), (select id from giftOrganizer.events where name = 'Xmas'));

insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('Wine to Work Socks', (select id from giftOrganizer.recipients where first_name = 'Jodie'), 7.50, false);
insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('Jacket From Nordstrom Rack', (select id from giftOrganizer.recipients where first_name = 'Jodie'), 45.00, false);
insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('Toy Horse', (select id from giftOrganizer.recipients where first_name = 'Tina'), 25.00, false);
insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('Noise Maker', (select id from giftOrganizer.recipients where first_name = 'Gene'), 25.00, false);
insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('Musical Tickets', (select id from giftOrganizer.recipients where first_name = 'Linda'), 50.00, false);
insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('Safety Tag Remover', (select id from giftOrganizer.recipients where first_name = 'Louise'), 30.00, false);
insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('New Knife', (select id from giftOrganizer.recipients where first_name = 'Bob'), 75.00, false);

