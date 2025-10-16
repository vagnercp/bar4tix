SET search_path = catalog, public;
CREATE OR REPLACE VIEW catalog.v_products AS
SELECT p.id, p.gtin, p.name, p.brand, p.created_at FROM catalog.products p;
