// https://github.com/michael-simons/neo4j-migrations/issues/739
MATCH (p:Person) WHERE p.id IS NULL
SET p.id = randomUUID();
