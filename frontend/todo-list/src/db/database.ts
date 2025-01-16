import {Client} from 'pg';
import {dbConfig} from "../secrets/db_configuration";

const client = new Client(dbConfig);

export async function connectToDatabase() {
    try {
        await client.connect();
        console.log('Connected to PostgreSQL!');

        const res = await client.query('SELECT * FROM todo_list');
        console.log('Server time:', res.rows);

    } catch (err) {
        console.error('Connection error', err);
    }
}

export default client;
