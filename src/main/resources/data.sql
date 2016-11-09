insert into giftOrganizer.users (first_name, last_name, password, email, role, active) values ('Carolyn', 'Harris', 'welcome', 'ccharris27@gmail.com', 'USER', true);
insert into giftOrganizer.users (first_name, last_name, password, email, role, active) values ('Ethan', 'Ransdell', 'def456', 'erandsdell@email.com', 'USER', true);
insert into giftOrganizer.users (first_name, last_name, password, email, role, active) values ('Sydney', 'Haggard', 'lol123', 'shaggard@email.com', 'USER', true);

insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id) values ('Jodie', 'Harris', 'jodie.har@gmail.com', '12/18/1961', (select id from giftOrganizer.users where email = 'ccharris27@gmail.com'));
insert into giftOrganizer.recipients (first_name, last_name, email, birthday, user_id) values ('Kirk', 'Harris', 'alf176@aol.com', '06/17/1957', (select id from giftOrganizer.users where email = 'ccharris27@gmail.com'));

insert into giftOrganizer.events (name, budget, user_id) values ('Christmas', 400.00, (select id from giftOrganizer.users where email = 'ccharris27@gmail.com'));

insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Jodie'), (select id from giftOrganizer.events where name = 'Christmas'));
insert into giftOrganizer.event_recipients (recipient_id, event_id) values ((select id from giftOrganizer.recipients where first_name = 'Kirk'), (select id from giftOrganizer.events where name = 'Christmas'));

insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('Wine to Work Socks', (select id from giftOrganizer.recipients where first_name = 'Jodie'), 7.50, false);
insert into giftOrganizer.gifts (name, recipient_id, price, bought) values ('Jacket From Nordstrom Rack', (select id from giftOrganizer.recipients where first_name = 'Jodie'), 45.00, false);