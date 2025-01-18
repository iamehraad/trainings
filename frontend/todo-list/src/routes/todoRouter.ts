import {NextFunction, Request, Response, Router} from "express";
import {createTodoItem, getTodoItems} from "../repository/todoRepository";
import {TodoDto} from "../dto/todo.dto";
import {handlePostgresError} from "../db/database";

const todoRouter = Router()

todoRouter.get("/", async (req: Request, res: Response) => {
    const response = await getTodoItems()
    res.json(response);
})

todoRouter.post("/create", async (req: Request, res: Response, next: NextFunction) => {
    try {
        const response = await createTodoItem(req.body as TodoDto)
        res.json({id: response});
    } catch (e) {
        next(e)
    }
})

// Error handler
todoRouter.use((err: any, req: Request, res: Response, next: NextFunction) => {
    if (err.code) {
        const pgError = handlePostgresError(err);
        if (pgError) {
            res.status(pgError.status).json({message: pgError.message});
        } else {
            res.status(400).json({message: "Something went wrong!"})
        }
    }
    res.status(500).json({message: 'Internal server error'});
});

export default todoRouter