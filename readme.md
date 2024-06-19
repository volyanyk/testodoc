# created to compare the performance of the view based on a jsonb on postgresql
### Before V4 index
'Limit  (cost=1360.04..1360.05 rows=1 width=272) (actual time=13.451..13.454 rows=20 loops=1)
->  Sort  (cost=1360.04..1360.05 rows=1 width=272) (actual time=13.450..13.451 rows=20 loops=1)
Sort Key: test_table.create_date DESC
Sort Method: top-N heapsort  Memory: 29kB
->  Seq Scan on test_table  (cost=0.00..1360.03 rows=1 width=272) (actual time=0.037..12.969 rows=1600 loops=1)
"              Filter: (((data ->> 'unloading_city'::text) = ANY ('{Toronto,Montreal}'::text[])) AND (((data ->> 'loading_city'::text))::numeric > 0.35) AND (((data ->> 'first_id'::text))::bigint = 1) AND (((data ->> 'secondary_id'::text))::bigint = 1))"
Rows Removed by Filter: 8400
Planning Time: 0.238 ms
Execution Time: 13.528 ms
'

### After
'
Limit  (cost=301.78..301.78 rows=1 width=272) (actual time=5.257..5.260 rows=20 loops=1)
->  Sort  (cost=301.78..301.78 rows=1 width=272) (actual time=5.256..5.257 rows=20 loops=1)
Sort Key: test_table.create_date DESC
Sort Method: top-N heapsort  Memory: 29kB
->  Bitmap Heap Scan on test_table  (cost=9.32..301.77 rows=1 width=272) (actual time=0.391..4.940 rows=1600 loops=1)
"              Recheck Cond: ((data ->> 'unloading_city'::text) = ANY ('{Toronto,Montreal}'::text[]))"
Filter: ((((data ->> 'loading_city'::text))::numeric > 0.35) AND (((data ->> 'first_id'::text))::bigint = 1) AND (((data ->> 'secondary_id'::text))::bigint = 1))
Rows Removed by Filter: 8400
Heap Blocks: exact=910
->  Bitmap Index Scan on idx_unloading_city  (cost=0.00..9.32 rows=100 width=0) (actual time=0.273..0.273 rows=10000 loops=1)
"                    Index Cond: ((data ->> 'unloading_city'::text) = ANY ('{Toronto,Montreal}'::text[]))"
Planning Time: 0.110 ms
Execution Time: 5.335 ms

'

Recommendations for Further Optimization

    Additional Indexes:
        Partial Index for loading_city: Since filtering on loading_city::numeric is a significant part of the query, creating a partial index for this field might help.

        sql

CREATE INDEX idx_loading_city_numeric ON doc.test_table (((data->>'loading_city')::numeric));

Combined Index: An index that combines the most frequently filtered fields could improve performance further.

sql

        CREATE INDEX idx_combined ON doc.test_table (
            ((data->>'unloading_city')::text),
            ((data->>'loading_city')::numeric),
            ((data->>'first_id')::bigint),
            ((data->>'secondary_id')::bigint)
        );

    Query Optimization:
        Ensure the filters are applied in the most selective order. The database query planner usually handles this, but manually ensuring the most selective filters are applied first can sometimes yield better performance.

    View Optimization:
        If the view is frequently accessed and the underlying data does not change frequently, consider using a materialized view. This can reduce the need for recalculating the view every time it is queried.

Example of Creating Additional Indexes

sql

-- Index for numeric comparison on loading_city
CREATE INDEX idx_loading_city_numeric ON doc.test_table (((data->>'loading_city')::numeric));

-- Combined index for frequently filtered fields
CREATE INDEX idx_combined ON doc.test_table (
((data->>'unloading_city')::text),
((data->>'loading_city')::numeric),
((data->>'first_id')::bigint),
((data->>'secondary_id')::bigint)
);

Testing and Validation

After adding these indexes, validate the performance by running the query with EXPLAIN ANALYZE again to ensure that the changes have positively impacted the execution time and resource usage.
Monitoring and Maintenance

    Regularly monitor the performance of the query as the data volume grows.
    Rebuild indexes periodically to maintain their efficiency.
    Consider the cost of maintaining additional indexes, especially if the underlying table is frequently updated.

By implementing these additional indexes and optimizations, you should achieve further improvements in query performance.