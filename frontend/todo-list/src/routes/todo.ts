import {Request, Response, Router} from "express";

const todoRouter = Router()

todoRouter.get("/", (req: Request, res: Response) => {
    res.send("Hello World2!")
})

export default todoRouter