MATCH (m:Movie) WHERE m.id IS NULL
SET m.id = randomUUID();
