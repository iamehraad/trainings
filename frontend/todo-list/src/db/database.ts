import {Pool} from 'pg';
import {dbConfig} from "./db_configuration";

// Database connection details
const client = new Pool(dbConfig);

export async function connectToDatabase() {
    try {
        await client.connect();
        console.log('Connected to PostgreSQL!');

        const res = await client.query('SELECT * FROM pg_user');
        console.log('Server time:', res.rows[0]);

    } catch (err) {
        console.error('Connection error', err);
    } finally {
        await client.end();
    }
}

export default client;
