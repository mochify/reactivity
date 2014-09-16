--name: get-person
select top 1 * from person where
    username = ?
