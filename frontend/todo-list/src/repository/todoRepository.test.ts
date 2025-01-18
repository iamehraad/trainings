import {describe, it, vi, expect, beforeEach} from "vitest";
import {createTodoItem, getTodoItems} from "./todoRepository";
import {TodoDto} from "../dto/todo.dto";
import client from "../db/database";
import {Pool, QueryResult} from "pg";

// Mock the database client
vi.mock('../db/database', () => ({
    default: {
        query: vi.fn() as unknown as jest.MockedFunction<Pool['query']>
    }
}))
const mockedClient = vi.mocked(client)

describe("Todo repository", () => {
    beforeEach(() => {
        vi.clearAllMocks()
    })
    const mockTodo: TodoDto = {
        description: "test",
        task: "test",
        is_finished: false
    }
    it("should create a todo", async () => {
        const mockResponse: QueryResult = {
            rows: [{id: 1}],
            command: 'INSERT',
            rowCount: 1,
            oid: 0,
            fields: []
        }
        // @ts-ignore
        mockedClient.query.mockResolvedValueOnce(mockResponse);
        const result = await createTodoItem(mockTodo);
        expect(mockedClient.query).toHaveBeenCalledWith(
            expect.stringContaining('INSERT INTO todo_list'),
            [mockTodo.task, mockTodo.description, mockTodo.is_finished]
        )
        expect(result).toEqual(1);

    })

    it("Should return todo list", async () => {
        const mockTodos: QueryResult = {
            rows: [
                { id: 1, task: 'Task 1', description: 'Desc 1', is_finished: false },
                { id: 2, task: 'Task 2', description: 'Desc 2', is_finished: true }
            ],
            command: 'SELECT',
            rowCount: 2,
            oid: 0,
            fields: []
        }
        // @ts-ignore
        mockedClient.query.mockResolvedValueOnce(mockTodos)

        const result = await getTodoItems();
        expect(mockedClient.query).toHaveBeenCalledWith(
            expect.stringContaining('SELECT * FROM todo_list')
        )
        expect(result.length).toEqual(2);
    })

    it('should throw error when database query fails', async () => {
        const mockError = new Error('Database error')
        mockedClient.query.mockRejectedValueOnce(mockError)

        await expect(getTodoItems()).rejects.toThrow('Database error')
    })
})