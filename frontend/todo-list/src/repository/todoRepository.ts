import {TodoDto} from "../dto/todo.dto";
import client from "../db/database";

export async function createTodoItem(todoDto: TodoDto) {
    console.log("todoDto", todoDto);
    try {
        const response = await client.query(
            `INSERT INTO todo_list(task,description, is_finished)
             VALUES($1, $2, $3)
             RETURNING id
        `, [todoDto.task, todoDto.description, todoDto.is_finished])
        return response.rows[0].id;
    } catch (e) {
        console.log(e);
        throw e
    }
}

export async function getTodoItems() {
    try {
        const response = await client.query(
            `SELECT * FROM todo_list`
        )
        return response.rows;
    } catch (e) {
        console.log(e);
        throw e
    }
}