CREATE INDEX idx_unloading_city ON doc.test_table ((data->>'unloading_city'));
CREATE INDEX idx_loading_city ON doc.test_table ((data->>'loading_city'));
CREATE INDEX idx_first_id ON doc.test_table ((data->>'first_id'));
CREATE INDEX idx_secondary_id ON doc.test_table ((data->>'secondary_id'));