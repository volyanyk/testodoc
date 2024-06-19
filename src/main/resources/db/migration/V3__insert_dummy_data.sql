do
$$
    begin
        for r in 1..10000
            loop
                INSERT INTO doc.test_table (id, data)
                VALUES (gen_random_uuid(),
                        jsonb_build_object(
                                'first_id', random()::bigint,
                                'secondary_id', random()::bigint,
                                'third_id', random()::bigint,
                                'loading_city', random()::text,
                                'unloading_city', 'Toronto',
                                'loading_country_code', 'US',
                                'unloading_country_code', 'CA',
                                'type', 'INTERNAL',
                                'status', 'INITIAL',
                                'source', 'EXTERNAL',
                                'custom_field_0',random(),
                                'custom_field_1',random(),
                                'custom_field_2',random(),
                                'custom_field_3',random(),
                                'custom_field_4',random(),
                                'custom_field_5',random(),
                                'custom_field_6',random(),
                                'custom_field_7',random(),
                                'custom_field_8',random(),
                                'custom_field_9',random()
                        ));

            end loop;
    end ;
$$;