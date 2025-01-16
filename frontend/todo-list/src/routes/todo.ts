import {Request, Response, Router} from "express";
import {createTodoItem} from "../repository/todoRepository";
import {TodoDto} from "../dto/todo.dto";

const todoRouter = Router()

todoRouter.get("/", (req: Request, res: Response) => {
    res.send("Hello World2!")
})

todoRouter.post("/create", async (req: Request, res: Response) => {
    const response = await createTodoItem(req.body as TodoDto)
    res.json({id: response});
})

export default todoRouter