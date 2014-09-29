-- name: get-person-by-username
-- Retrieves a person by username
select person_id, username, email, first_name, last_name from person where
    username = :username

-- name: create-person!
-- Create a person
insert into person (email, username, first_name, last_name, password)
    values (:email, :username, :firstName, :lastName, :password)
