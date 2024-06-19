CREATE VIEW doc.test_view AS
SELECT
    id,
    (data->>'first_id')::bigint AS first_id,
    (data->>'secondary_id')::bigint AS secondary_id,
    data->>'loading_city' AS loading_city,
    data->>'unloading_city' AS unloading_city,
    data->>'loading_country_code' AS loading_country_code,
    data->>'unloading_country_code' AS unloading_country_code,
    data->>'type' AS type,
    data->>'status' AS status,
    data->>'source' AS source,
    create_date,
    update_date
FROM
    doc.test_table;