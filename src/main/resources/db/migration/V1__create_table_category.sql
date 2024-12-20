-- Verifica se a sequência existe antes de criá-la
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'category_id_seq') THEN
        CREATE SEQUENCE category_id_seq
            START WITH 1
            INCREMENT BY 1
            NO MINVALUE
            NO MAXVALUE
            CACHE 1;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Verifica se a tabela existe antes de criá-la
DO $$
BEGIN
       IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'category') THEN
            CREATE TABLE category (
                id INTEGER PRIMARY KEY DEFAULT nextval('category_id_seq'),
                name VARCHAR(255) NOT NULL,
                description TEXT
            );
       END IF;
END;
$$ LANGUAGE plpgsql;

-- Garante que a sequência será vinculada à tabela
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_class WHERE relname = 'category_id_seq') THEN
        ALTER SEQUENCE category_id_seq OWNED BY category.id;
    END IF;
END;
$$ LANGUAGE plpgsql;