import {Client, DatabaseError} from 'pg';
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

export const handlePostgresError = (err: DatabaseError) => {
    switch (err.code) {
        case '23505':
            return {
                status: 400,
                message: 'This resource already exists.',
            };
        case '23503':
            return {
                status: 400,
                message: 'Invalid reference to another resource.',
            };
        case '22P02':
            return {
                status: 400,
                message: 'Invalid input format.',
            };
        case '23514':
            return {
                status: 400,
                message: 'A constraint was violated. Please check your input.',
            };
        default:
            return {
                status: 500,
                message: 'Database error occurred.',
            };
    }
};

export default client;
