import {describe, expect, vi, it, beforeEach} from "vitest";
import {createTodoItem, getTodoItems} from "../repository/todoRepository";
import todoRouter from "./todoRouter";
import request from 'supertest';
import express, {Express} from "express";

vi.mock("../repository/todoRepository")

const mockedTodos = [
    {id: 1, task: 'Task 1', description: 'Desc 1', is_finished: false},
    {id: 2, task: 'Task 2', description: 'Desc 2', is_finished: true}
];
describe("Router functions", () => {
    let app: Express;
    beforeEach(() => {
        // Create a new Express application for each test
        app = express();
        app.use(express.json());
        app.use('/todo', todoRouter);
    });
    it("should call get and returns mocked values", async () => {
        vi.mocked(getTodoItems).mockResolvedValue(mockedTodos);
        const response = await request(app).get("/todo").expect(200)
        expect(response.body).toEqual(mockedTodos)
    })

    it("should call post and returns an ID", async () => {
        vi.mocked(createTodoItem).mockResolvedValue(1);
        const response = await request(app).post("/todo/create").send(mockedTodos[0]).expect(200)
        expect(response.body).toEqual({id: 1})
    })
})